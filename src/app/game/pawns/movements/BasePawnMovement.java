package app.game.pawns.movements;

import app.game.pawns.BasePawn;

abstract public class BasePawnMovement {
	private int x;
	private int y;
	
	public int getX() { return this.x; }
	public int getY() { return this.y; }
	
	public boolean validateMove(BasePawn pawn, int newX, int newY) {
		return true;
	}
}
