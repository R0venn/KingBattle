package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Shotgun;

public class King extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public King(int x, int y) {
		super(200, 50, new Shotgun(), "♔", x, y);
	}
	
	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		//return xDist <= 1 && yDist <= 1;
		return ((xDist == 2 && yDist == 1) || (xDist <= 2 && yDist == 0)) || ((yDist == 2 && xDist == 1) || (yDist <= 2 && xDist == 0) && ((xDist != 1 && yDist == 0)));
		
	}

}
