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

}
