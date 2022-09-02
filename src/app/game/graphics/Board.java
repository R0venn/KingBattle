package app.game.graphics;

import app.game.pawns.BasePawn;
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
		System.out.println("   (a  b  c  d  e  f  g  h)");
		for(int x = 0; x < this.pawns.length; x++) {
			System.out.print("("+(x+1)+")");
			for(int y = 0; y < this.pawns[0].length; y++) {
				currentCase = this.pawns[x][y] != null ? this.pawns[x][y].toString() : " ";
				System.out.print(ConsoleColors.CYAN_BACKGROUND + " " + currentCase + " " + ConsoleColors.RESET);
			}
			System.out.println();
		}
	}
}
