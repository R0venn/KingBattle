package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class King extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public King(int health, int armor, BaseWeapon weapon, String model) {
		super(200, 50, weapon, "♔");
	}

}
