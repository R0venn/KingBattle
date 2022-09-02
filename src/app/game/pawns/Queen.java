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
	public Queen(int x, int y) {
		super(175, 25, new BaseWeapon(){}, "♕", x, y);
	}

}
