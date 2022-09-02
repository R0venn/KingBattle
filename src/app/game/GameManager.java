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
	
	public GameManager() {
		this.game = new Game();
		this.game.getPlayers()[0] = new Player("John", PawnColors.WHITE);
		this.game.getPlayers()[1] = new Player("Doe", PawnColors.BLACK);
		Player playerOne = this.game.getFirstPlayer();
		Player playerTwo = this.game.getSecondPlayer();
		playerOne.addPawn(new King(3,0));
		playerTwo.addPawn(new King(3,7));
		this.game.setCurrentPlayer(playerOne);
	}
	
	public void startGame() {
		this.popPawns();
		while(!this.onePlayerWon()) {
			while(!this.isMatchEnd()) {
				Player currentPlayer = this.game.getCurrentPlayer();
				int choice;
				int[] coordinates;
				int posX;
				int posY;
				this.game.getBoard().displayBoard(this.game);
				System.out.println(currentPlayer.getNickname() + " que voulez vous faire ?\n1. Attaquer une cible\n2. Bouger mon KING");
				do {				
					choice = currentPlayer.askDigit();
				} while(choice < 1 || choice > 2);
				switch(choice) {
				case 1:
					coordinates = currentPlayer.askCoordinates();
					posX = coordinates[0];
					posY = coordinates[1];
					BasePawn target = this.game.getBoard().getPawn(posX, posY);
					int baseHealth = target.getHealth();
					if(currentPlayer.getKing().attack(posX, posY, this.game.getBoard())){
						System.out.println("WOOOO tu lui a mis " + (baseHealth - target.getHealth()) + " dégats il lui reste " + target.getHealth() + " point de vie.");
					}
					try {
						Thread.sleep(3000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					break;
				case 2:
					coordinates = currentPlayer.askCoordinates();
					posX = coordinates[0];
					posY = coordinates[1];
					BasePawn toMove = currentPlayer.getKing();
					this.movePawn(toMove, posX, posY);
					break;
				}
				this.nextRound();
			}
			this.congratRoundWinner();
			this.resetBoard();
		}
	}
	
	public void nextMatch() {
		
	}
	
	public void nextRound() {
		Player nextPlayer = this.game.getCurrentPlayer() == game.getFirstPlayer() ? game.getSecondPlayer() : game.getFirstPlayer();
		this.game.setCurrentPlayer(nextPlayer);
	}
	
	public void popPawns() {
		Board board = this.game.getBoard();
		ArrayList<BasePawn> playerOnePawns = this.game.getFirstPlayer().getPawns();
		ArrayList<BasePawn> playerTwoPawns = this.game.getSecondPlayer().getPawns();
		for(BasePawn pawns : playerOnePawns) {
			board.addPawn(pawns);
		}
		for(BasePawn _pawns : playerTwoPawns) {
			board.addPawn(_pawns);
		}
		this.game.getBoard().displayBoard(this.game);
	}
	
	public boolean movePawn(BasePawn pawn, int newX, int newY) {
		boolean res = false;
		if(!this.game.getBoard().isPawn(newX, newY)) {
			this.game.getBoard().movePawn(pawn.getX(), pawn.getY(), newX, newY);
			pawn.setX(newX);
			pawn.setY(newY);
			res = true;
		}
		return res;
	}
	
	public void congratRoundWinner() {
		Player winner = this.game.getFirstPlayer().getKing().isDead() ? this.game.getSecondPlayer() : this.game.getFirstPlayer();
		winner.winGameMatch();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void resetBoard() {
		
	}
	
	public boolean isMatchEnd() {
		return this.game.getFirstPlayer().getKing().isDead() || this.game.getSecondPlayer().getKing().isDead();
	}
	
	public boolean onePlayerWon() {
		return this.game.getFirstPlayer().getScore() == 3 || this.game.getSecondPlayer().getScore() == 3;		
	}
}
