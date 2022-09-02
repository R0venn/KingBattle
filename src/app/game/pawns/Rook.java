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
	public Rook(int x, int y) {
		super(125, 75, new BaseWeapon(){}, "♖", x, y);
	}

}
