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
	public Knight(int x, int y) {
		super(150, 25, new BaseWeapon(){}, "♘", x, y);
	}

}
