package app.game;

import java.util.ArrayList;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.King;
import app.game.pawns.Knight;
import app.game.pawns.PawnColors;
import app.game.players.Player;
import core.Utils;

public class GameManager {
	private Game game;
	
	public GameManager(String namePlayerOne, String namePlayerTwo) {
		this.game = new Game();
		this.game.getPlayers()[0] = new Player(namePlayerOne, PawnColors.WHITE);
		this.game.getPlayers()[1] = new Player(namePlayerTwo, PawnColors.BLACK);
		Player playerOne = this.game.getFirstPlayer();
		Player playerTwo = this.game.getSecondPlayer();
		this.game.setCurrentPlayer(playerOne);
		playerOne.addPawn(new King(3,0));
		playerTwo.addPawn(new King(3,7));
		playerOne.setCurrentPawn(playerOne.getKing());
		playerTwo.setCurrentPawn(playerTwo.getKing());
	}
	
	public Game getGame() { return this.game; }
	
	public void startGame() {
		this.popPawns();
		while(!this.onePlayerWon()) {
			while(!this.isMatchEnd()) {
				this.matchLoop();
				this.nextRound();
			}
			this.congratRoundWinner();
			this.nextMatch();
		}
	}
	
	public void matchLoop() {
		Player currentPlayer = this.game.getCurrentPlayer();
		int choice;
		do {
			this.game.getBoard().displayBoard();
			Utils.info(currentPlayer.getNickname() + " que voulez vous faire ?\n0. Passer mon tour\n1. Attaquer une cible\n2. Bouger mon "+currentPlayer.getCurrentPawn());
			do {
				choice = currentPlayer.askDigit();
			} while(choice < 0 || choice > 2);
			switch(choice) {
				case 1:
					if(currentPlayer.canAttack() && currentPlayer.getCurrentPawn().hasSomeoneInRange(game.getOpponent().getPawns())) {
						this.attackTarget(currentPlayer);
					} else {
						Utils.info("Cette unité ne peut attaquer personne");
						Utils.sleep(2);
					}
					break;
				case 2:
					this.moveTarget(currentPlayer);
					break;
			}
		} while(choice != 0 && currentPlayer.canAction(this.game.getOpponent().getPawns()));
	}
	
	public void attackTarget(Player currentPlayer) {
		ArrayList<BasePawn> availableTargets = currentPlayer.getCurrentPawn().getPawnsInAttackRange(game.getOpponent().getPawns());
		BasePawn toDisplay;
		this.game.getBoard().displayBoard(2);
		Utils.info("Quelle pièce souhaitez-vous attaquer ?");
		for(int i = 0; i < availableTargets.size(); i++) {
			toDisplay = availableTargets.get(i);
			System.out.println(i+". "+toDisplay.getModel()+" ("+ toDisplay.getHealth() +"pv)");
		}
		int targetId;
		do {
			targetId = currentPlayer.askDigit();
		} while(targetId < 0 || targetId >= availableTargets.size());
		BasePawn target = availableTargets.get(targetId);
		currentPlayer.getCurrentPawn().attack(target);
		currentPlayer.useAP();
	}
	
	public void moveTarget(Player currentPlayer) {
		this.game.getBoard().displayBoard(1);
		Utils.info("Ou souhaitez vous vous déplacer ?");
		int[] coordinates = currentPlayer.askCoordinates();
		int posX = coordinates[0];
		int posY = coordinates[1];
		BasePawn toMove = currentPlayer.getCurrentPawn();
		if(this.movePawn(toMove, posX, posY)) currentPlayer.useMP();
	}
	
	public void nextMatch() {
		this.game.swapBoards();
		// congrats winner;
		this.game.saveCurrentBoard();
	}
	
	public void nextRound() {
		this.game.getBoard().displayBoard();
		Utils.info("\nTour suivant !");
		Utils.sleep(1);
		Player nextPlayer = this.game.getCurrentPlayer() == game.getFirstPlayer() ? game.getSecondPlayer() : game.getFirstPlayer();
		this.game.setCurrentPlayer(nextPlayer);
		nextPlayer.resetPoints();
	}
	
	public void popPawns() {
		Board board = this.game.getBoard();
		ArrayList<BasePawn> playerOnePawns = this.game.getFirstPlayer().getPawns();
		ArrayList<BasePawn> playerTwoPawns = this.game.getSecondPlayer().getPawns();
		for(BasePawn pawn : playerOnePawns) {
			board.addPawn(pawn);
		}
		for(BasePawn _pawn : playerTwoPawns) {
			board.addPawn(_pawn);
		}
		this.game.getBoard().displayBoard();
	}
	
	public boolean movePawn(BasePawn pawn, int newX, int newY) {
		boolean res = false;
		if(!this.game.getBoard().isPawn(newX, newY) && pawn.canMoveTo(newX, newY)) {
			this.game.getBoard().movePawn(pawn.getX(), pawn.getY(), newX, newY);
			pawn.setX(newX);
			pawn.setY(newY);
			res = true;
		} else {
			Utils.info("Cette unité ne peut pas aller sur cette case");
			Utils.sleep(2);
		}
		return res;
	}
	
	public void congratRoundWinner() {
		Player winner = this.game.getFirstPlayer().getKing().isDead() ? this.game.getSecondPlayer() : this.game.getFirstPlayer();
		winner.winGameMatch();
		Utils.sleep(3);
	}
	
	public boolean isMatchEnd() {
		return this.game.getFirstPlayer().getKing().isDead() || this.game.getSecondPlayer().getKing().isDead();
	}
	
	public boolean onePlayerWon() {
		return this.game.getFirstPlayer().getScore() == 3 || this.game.getSecondPlayer().getScore() == 3;		
	}
}
