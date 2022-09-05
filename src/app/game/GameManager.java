package app.game;

import java.io.IOException;
import java.util.ArrayList;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.King;
import app.game.pawns.Knight;
import app.game.pawns.Pawn;
import app.game.pawns.PawnColors;
import app.game.pawns.Rook;
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
		playerOne.addPawn(new King());
		playerOne.addPawn(new Pawn());
		playerTwo.addPawn(new King());
		playerTwo.addPawn(new Pawn());
		playerOne.setCurrentPawn(playerOne.getKing());
		playerTwo.setCurrentPawn(playerTwo.getKing());
	}
	
	public Game getGame() { return this.game; }
	
	public void startGame() {
		while(!this.onePlayerWon()) {
			this.popPawns();
			while(!this.isMatchEnd()) {
				this.matchLoop();
				this.nextRound();
			}
			this.nextMatch();
		}
		this.congratMatchWinner();
	}
	
	public void matchLoop() {
		Player currentPlayer = this.game.getCurrentPlayer();
		int choice;
		ArrayList<String> menuChoices = new ArrayList<>();
		menuChoices.add("Passer mon tour");
		menuChoices.add("Bouger mon pion");
		menuChoices.add("Selectionner un autre pion");
		do {
			if(currentPlayer.canAttack() && currentPlayer.getCurrentPawn().hasSomeoneInRange(game.getOpponent().getAlivePawns())) {
				menuChoices.add("Attaquer une cible");
			}
			this.game.getBoard().displayBoard(2);
			Utils.info(currentPlayer.getNickname() + " que voulez vous faire ?");
			for(int i = 0; i < menuChoices.size(); i++) {
				System.out.println(i+". "+menuChoices.get(i)+".");
			}
			do {
				choice = currentPlayer.askDigit();
			} while(choice < 0 || (choice > menuChoices.size()-1));
			switch(choice) {
				case 1:
					this.moveTarget(currentPlayer);
					break;
				case 2:
					this.choicePawn(currentPlayer);
					break;
				case 3:
					this.attackTarget(currentPlayer);
					this.game.getBoard().updateBoard();
					this.game.getOpponent().checkCurrentPawnAlive();
					break;
			}
		} while(!this.isMatchEnd() && choice != 0 && currentPlayer.canAction(this.game.getOpponent().getPawns()));
	}
	
	public void choicePawn(Player currentPlayer) {
		this.game.getBoard().displayBoard();
		ArrayList<BasePawn> currentPlayerPawns = currentPlayer.getAlivePawns();
		for(int i = 0; i < currentPlayerPawns.size(); i++) {
			System.out.println(i+". " + currentPlayerPawns.get(i).getModel());
		}
		int nextPawnIndex;
		do {
			nextPawnIndex = currentPlayer.askDigit();
		}while(nextPawnIndex < 0 || nextPawnIndex > currentPlayerPawns.size()-1);
		currentPlayer.setCurrentPawn(currentPlayerPawns.get(nextPawnIndex));
	}
	
	public void attackTarget(Player currentPlayer) {
		ArrayList<BasePawn> availableTargets = currentPlayer.getCurrentPawn().getPawnsInAttackRange(game.getOpponent().getAlivePawns());
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
		this.congratRoundWinner();
		this.game.getBoard().resetBoard();
	}
	
	public void nextRound() {
		this.game.getBoard().displayBoard();
		Utils.debug("\nJoueur suivant !");
		Utils.sleep(2);
		Player nextPlayer = this.game.getCurrentPlayer() == game.getFirstPlayer() ? game.getSecondPlayer() : game.getFirstPlayer();
		this.game.setCurrentPlayer(nextPlayer);
		nextPlayer.resetPoints();
	}
	
	public void popPawns() {
		this.game.getFirstPlayer().resetPawns();
		this.game.getSecondPlayer().resetPawns();
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
		winner.addPawn(this.game.pickRandomPawn());
		Utils.sleep(3);
	}
	
	public void congratMatchWinner() {
		Player winner = this.game.getFirstPlayer().getScore() == Game.ROUND_TO_WIN ? this.game.getFirstPlayer() : this.game.getSecondPlayer();
		Utils.debug("Le joueur '"+winner.getNickname() + "' gagne la partie !");
		Player loser = this.game.getFirstPlayer() == winner ? this.game.getSecondPlayer() : this.game.getFirstPlayer();
		try {
			Utils.appendToLogFile(winner.getNickname() + " won a game against " + loser.getNickname() + " - "+winner.getScore() + " to " + loser.getScore());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean isMatchEnd() {
		return this.game.getFirstPlayer().getKing().isDead() || this.game.getSecondPlayer().getKing().isDead();
	}
	
	public boolean onePlayerWon() {
		return this.game.getFirstPlayer().getScore() == Game.ROUND_TO_WIN || this.game.getSecondPlayer().getScore() == Game.ROUND_TO_WIN;		
	}
}
