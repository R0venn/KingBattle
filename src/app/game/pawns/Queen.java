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
	public Queen(int health, int armor, BaseWeapon weapon, String model) {
		super(175, 25, weapon, "♕");
	}

}
