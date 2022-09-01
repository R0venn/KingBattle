package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Knight extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Knight(int health, int armor, BaseWeapon weapon, String model) {
		super(150, 25, weapon, "♘");
	}

}
