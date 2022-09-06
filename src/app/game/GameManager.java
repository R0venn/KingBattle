package app.game;

import java.io.IOException;
import java.util.ArrayList;

import app.game.bonus.AddArmorCard;
import app.game.bonus.AddMPCard;
import app.game.bonus.BonusCardFactory;
import app.game.bonus.CriticalDmgCard;
import app.game.bonus.HealingCard;
import app.game.bonus.ICard;
import app.game.bonus.SwapPawnsCard;
import app.game.bonus.SwitchWeaponCard;
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
		this.setUpPlayer(playerOne);
		this.setUpPlayer(playerTwo);
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
		menuChoices.add("Selectionner un autre pion");
		int choiceAmount = menuChoices.size();
		do {
			this.game.getBoard().displayBoard(2);
			Utils.purple(currentPlayer.getNickname()+", vous controlez votre:\n" + currentPlayer.getCurrentPawn().getInfos()+"\n");
			
			for(int i = 0; i < menuChoices.size(); i++) {
				Utils.green(i+". "+menuChoices.get(i)+".");
			}
			
			if(currentPlayer.canMove()) {
				Utils.green("2. Bouger mon pion");
				choiceAmount = 2;
			} else {
				Utils.red("Vous ne pouvez pas bouger de pion");
			}
			
			if(currentPlayer.canAttack(game.getOpponent().getAlivePawns())) {
				Utils.green("3. Attaquer une cible");
				choiceAmount = 3;
			} else {
				Utils.red("Vous ne pouvez pas attaquer");
			}
			
			if(currentPlayer.getPowers().size() > 0) {
				Utils.green("4. Utiliser une carte bonus");
				choiceAmount = 4;
			} else {
				Utils.red("Vous n'avez pas de carte bonus");
			}
			
			do {
				choice = currentPlayer.askDigit("Quelle action souhaitez-vous faire ? (ex: 0)");
			} while(choice < 0 || choice > (choiceAmount));
			switch(choice) {
				case 1:
					this.choicePawn(currentPlayer);
					break;
				case 2:
					if(Utils.randInt(0, 100) > 95) currentPlayer.addRandomPower();
					if(currentPlayer.canMove()) {
						this.moveTarget(currentPlayer);
					} else {
						Utils.info("Vous n'avez plus de PM (points de mouvement) disponible.");
						Utils.sleep(2);
					}
					break;
				case 3:
					if(Utils.randInt(0, 100) > 95) currentPlayer.addRandomPower();
					if(currentPlayer.canAttack(game.getOpponent().getAlivePawns())) {
						this.attackTarget(currentPlayer);
						this.game.getBoard().updateBoard();
						this.game.getOpponent().checkCurrentPawnAlive();
					} else {
						Utils.info("Aucune cible à portée.");
						Utils.sleep(2);
					}
					break;
				case 4:
					this.choiceBonus(currentPlayer);
					break;
			}
		} while(!this.isMatchEnd() && choice != 0 && currentPlayer.canAction(this.game.getOpponent().getPawns()));
	}
	
	public void choiceBonus(Player currentPlayer) {
		this.game.getBoard().displayBoard();
		ArrayList<ICard> bonusList = currentPlayer.getPowers();
		for(int i = 0; i < bonusList.size(); i++) {
			System.out.println(i+". "+bonusList.get(i).getEffect());
		}
		int bonusIndex;
		do {
			bonusIndex = currentPlayer.askDigit("Quel bonus souhaitez vous utiliser ? (9 pour annuler)");
		}while((bonusIndex < 0 || bonusIndex > bonusList.size()-1) && bonusIndex != 9);
		if(bonusIndex != 9) currentPlayer.usePower(bonusList.get(bonusIndex), this.game);
	}
	
	public void choicePawn(Player currentPlayer) {
		this.game.getBoard().displayBoard();
		ArrayList<BasePawn> currentPlayerPawns = currentPlayer.getAlivePawns();
		for(int i = 0; i < currentPlayerPawns.size(); i++) {
			System.out.println(i+". " + currentPlayerPawns.get(i).getModel());
		}
		int nextPawnIndex;
		do {
			nextPawnIndex = currentPlayer.askDigit("Quel pion souhaitez-vous controler ? (ex: 0)");
		}while(nextPawnIndex < 0 || nextPawnIndex > currentPlayerPawns.size()-1);
		currentPlayer.setCurrentPawn(currentPlayerPawns.get(nextPawnIndex));
	}
	
	public void attackTarget(Player currentPlayer) {
		ArrayList<BasePawn> availableTargets = currentPlayer.getCurrentPawn().getPawnsInAttackRange(game.getOpponent().getAlivePawns());
		BasePawn toDisplay;
		this.game.getBoard().displayBoard(2);
		for(int i = 0; i < availableTargets.size(); i++) {
			toDisplay = availableTargets.get(i);
			System.out.println(i+". "+toDisplay.getModel()+" ("+ toDisplay.getHealth() +"pv)");
		}
		int targetId;
		do {
			targetId = currentPlayer.askDigit("Quelle pièce souhaitez-vous attaquer ? (ex: 0)");
		} while(targetId < 0 || targetId >= availableTargets.size());
		BasePawn target = availableTargets.get(targetId);
		currentPlayer.getCurrentPawn().attack(target);
		currentPlayer.useAP();
	}
	
	public void moveTarget(Player currentPlayer) {
		this.game.getBoard().displayBoard(1);
		int[] coordinates = currentPlayer.askCoordinates("Sur quelle case souhaitez-vous allez ? (ex: b5)");
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
		Player loser = this.game.getFirstPlayer() == winner ? this.game.getSecondPlayer() : this.game.getFirstPlayer();
		winner.winGameMatch();
		winner.addRandomPower();
		winner.addRandomPower();
		winner.addPawn(this.game.pickRandomPawn(winner));
		loser.addPawn(this.game.pickRandomPawn(loser));
		loser.addRandomPower();
		Utils.sleep(2);
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
	
	public void setUpPlayer(Player player) {
		player.addRandomPower();
		player.addPawn(new King());
		player.addPawn(new Pawn());
		player.setCurrentPawn(player.getKing());
	}
}
