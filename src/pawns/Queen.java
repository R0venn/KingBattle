package pawns;

import weapons.Weapon;

public class Queen extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Queen(int health, int armor, Weapon weapon, char model) {
		super(175, 25, weapon, '♕');
	}

}
