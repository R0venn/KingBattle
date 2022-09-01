package app.game.graphics;

import core.ConsoleColors;
import core.Utils;

public class Board {
	public String[][] board;
	
	public Board() {
		this.board = new String[8][8];
	}
	
	public void displayBoard() {
		String currentCase;
		System.out.println("   (a  b  c  d  e  f  g  h)");
		for(int x = 0; x < this.board.length; x++) {
			System.out.print("("+(x+1)+")");
			for(int y = 0; y < this.board[0].length; y++) {
				currentCase = this.board[x][y] != null ? this.board[x][y] : "♜";
				System.out.print(ConsoleColors.CYAN_BACKGROUND + " " + currentCase + " " + ConsoleColors.RESET);
			}
			System.out.println();
		}
	}
}
