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
		String currentCase;
		String currentColor;
		System.out.println("   (a  b  c  d  e  f  g  h)");
		for(int y = 0; y < this.pawns.length; y++) {
			System.out.print("("+(y+1)+")");
			for(int x = 0; x < this.pawns[0].length; x++) {
				currentCase = this.pawns[x][y] != null ? this.pawns[x][y].toString() : " ";
				currentColor = this.pawns[x][y] != null ? this.pawns[x][y].getColor() : ConsoleColors.CYAN_BACKGROUND;
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
}
