package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Gun;

public class Pawn extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Pawn(int x, int y) {
		super(50, 0, new Gun(), "♗", x, y);
	}

	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return xDist == 1 && yDist == 0;
	}
}
