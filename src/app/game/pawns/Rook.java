package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Sniper;

public class Rook extends BasePawn {

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Rook(int x, int y) {
		super(125, 75, new Sniper(), "♖", x, y);
	}
	
	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return (xDist <= 5 && yDist == 0) || (xDist == 0 && yDist <= 5);
	}

}
