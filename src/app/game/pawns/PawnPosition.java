package app.game.pawns;

import app.game.weapons.Dagger;

public enum PawnPosition {
	KING(4,0),
	QUEEN(3,0),
	KNIGHT(5,0),
	BISHOP(1,0),
	ROOK(0,0),
	PAWN(4,1);

	private int x;
	private int y;
	
	PawnPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}
	
	public int getY(PawnColors color) {
		return color == PawnColors.BLACK ? this.y + 7 : this.y;
	}
}
