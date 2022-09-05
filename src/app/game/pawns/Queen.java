package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Queen extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Queen(int x, int y) {
		super(175, 25, null, "♕", x, y);
	}

	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return (xDist == yDist) || ((xDist <= 5 && yDist == 0) || (xDist == 0 && yDist <= 5));
	}
}
