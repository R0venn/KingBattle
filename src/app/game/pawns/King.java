package app.game.pawns;

import app.game.weapons.Weapon;

public class King extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public King(int health, int armor, Weapon weapon, char model) {
		super(200, 50, weapon, '♔');
	}

}
