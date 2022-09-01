package pawns;

import weapons.Weapon;

public class Rook extends BasePawn {

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Rook(int health, int armor, Weapon weapon, char model) {
		super(125, 75, weapon, '♖');
	}

}
