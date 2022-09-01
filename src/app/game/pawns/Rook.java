package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Rook extends BasePawn {

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Rook(int health, int armor, BaseWeapon weapon, String model) {
		super(125, 75, weapon, "♖");
	}

}
