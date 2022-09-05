package app.game.graphics;

import java.util.Arrays;

import app.game.Game;
import app.game.pawns.BasePawn;
import app.game.pawns.PawnColors;
import app.game.players.Player;
import core.ConsoleColors;
import core.Utils;

public class Board {
	public BasePawn[][] pawns;
	public Game game;
	
	public Board(Game game) {
		this.pawns = new BasePawn[8][8];
		this.game = game;
	}
	
	public Board(Board board) {
		this.game = board.game;
		this.pawns = Arrays.copyOf(board.pawns, board.pawns.length);
	}
	
	public BasePawn[][] getPawns() {
		return this.pawns;
	}
	
	
	public Game getGame() {
		return this.game;
	}
	
	public void updateBoard() {
		for(int y = 0; y < this.pawns.length; y++) {
			for(int x = 0; x < this.pawns[0].length; x++) {
				if(this.pawns[y][x] != null) {
					if(this.getPawn(x, y).isDead()) {
						this.pawns[y][x] = null;
					}
				}
			}
		}
	}
	
	public void displayBoard(int mode) {
		Utils.clearScreen();
		System.out.println("   (a  b  c  d  e  f  g  h)");
		Player firstPlayer = game.getFirstPlayer();
		Player secondPlayer = game.getSecondPlayer();
		Player currentPlayer = game.getCurrentPlayer();
		BasePawn firstPlayerKing = firstPlayer.getKing();
		BasePawn secondPlayerKing = secondPlayer.getKing();
		for(int y = 0; y < this.pawns.length; y++) {
			System.out.print("("+(y+1)+")");
			for(int x = 0; x < this.pawns[0].length; x++) {
				this.displayCase(x,y,mode);
			}
			if(game != null) {
				if(y == 2){
					System.out.print(" (Manche "+ (firstPlayer.getScore()+secondPlayer.getScore())+1 + ") Le premier à " + Game.ROUND_TO_WIN + " points gagne la partie !");
				} else if(y == 3) {
					System.out.print(" "+ConsoleColors.WHITE_BACKGROUND_BRIGHT + " ♔ " + ConsoleColors.RESET + firstPlayerKing.getHealth() + " pv" + ConsoleColors.BLACK_BACKGROUND + " ♔ " + ConsoleColors.RESET + " - " + secondPlayerKing.getHealth() + " pv" + ConsoleColors.RESET);
				} else if(y == 4) {
					System.out.print(" "+firstPlayer.getNickname()+": "+firstPlayer.getScore()+" points | " + secondPlayer.getNickname() + ": "+secondPlayer.getScore()+" points.");
				} else if(y == 5) {
					System.out.print(" C'est aux " + currentPlayer.getColor() + " de jouer.");
				} else if(y == 6) {
					System.out.print(" Vous avez : " + currentPlayer.getAP() + " AP " + game.getCurrentPlayer().getMP() + " MP");
				}
			}
			System.out.println();
		}
	}
	
	public void displayBoard() {
		this.displayBoard(0);
	}
	
	public void displayCase(int x, int y, int mode) {
		String currentCaseSymbol = " ";
		String currentCaseColor = ConsoleColors.BLUE_BACKGROUND_BRIGHT;
		BasePawn currentCasePawn = this.getPawn(x, y);
		BasePawn currentPlayerPawn = this.getGame().getCurrentPlayer().getCurrentPawn();
		if(currentCasePawn != null) currentCaseSymbol = currentCasePawn.getModel();
		if(currentCasePawn != null) currentCaseColor = currentCasePawn.getColor();
		switch(mode) {
		case 1:
			if(!this.isPawn(x, y)) {				
				if(currentPlayerPawn.canMoveTo(x, y)) currentCaseColor = ConsoleColors.GREEN_BACKGROUND;
			}
		case 2:
			if(this.isPawn(x, y) && !(currentCasePawn == currentPlayerPawn) && !(this.getGame().getCurrentPlayer().isFriendlyPawn(currentCasePawn)) && currentPlayerPawn.rangeToAttack(currentCasePawn)) currentCaseColor = ConsoleColors.RED_BACKGROUND_BRIGHT;
		}
		System.out.print(currentCaseColor + " " + currentCaseSymbol+ " " + ConsoleColors.RESET);
	}
	
	public boolean addPawn(BasePawn pawnToAdd) {
		boolean res = false;
		if(this.pawns[pawnToAdd.getY()][pawnToAdd.getX()] == null) {
			res = true;
			this.pawns[pawnToAdd.getY()][pawnToAdd.getX()] = pawnToAdd;
		}
		return res;
	}
	
	public BasePawn getPawn(int x, int y) {
		BasePawn res = null;
		if(this.posInBound(x, y)) {
			res = this.pawns[y][x];
		}
		return res;
	}
	
	public boolean isPawn(int x, int y) {
		return this.getPawn(x, y) != null;
	}	
	
	public boolean posInBound(int x, int y) {
		return (x >= 0 && x < this.getPawns().length) && (y >= 0 && y < this.getPawns()[0].length);
	}
	
	public void movePawn(int oldX, int oldY, int newX, int newY) {
		this.pawns[newY][newX] = this.pawns[oldY][oldX];
		this.pawns[oldY][oldX] = null;
		this.displayBoard();
	}
	
	public void resetBoard() {
		for(int y = 0; y < this.pawns.length; y++) {
			for(int x = 0; x < this.pawns[0].length; x++) {
				this.pawns[y][x] = null;
			}
		}
	}
}
