package app.game.graphics;

import app.game.pawns.BasePawn;
import app.game.pawns.PawnColors;
import core.ConsoleColors;
import core.Utils;

public class Board {
	public BasePawn[][] pawns;
	
	public Board() {
		this.pawns = new BasePawn[8][8];
	}
	
	public BasePawn[][] getPawns() {
		return this.pawns;
	}
	
	public void displayBoard() {
		Utils.clearScreen();
		String currentCase;
		String currentColor;
		System.out.println("   (a  b  c  d  e  f  g  h)");
		for(int y = 0; y < this.pawns.length; y++) {
			System.out.print("("+(y+1)+")");
			for(int x = 0; x < this.pawns[0].length; x++) {
				currentCase = this.pawns[y][x] != null ? this.pawns[y][x].toString() : " ";
				currentColor = this.pawns[y][x] != null ? this.pawns[y][x].getColor() : ConsoleColors.CYAN_BACKGROUND;
				System.out.print(currentColor + " " + currentCase + " " + ConsoleColors.RESET);
			}
			System.out.println();
		}
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
}
