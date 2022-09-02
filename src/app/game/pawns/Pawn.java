package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Gun;

public class Pawn extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Pawn(int x, int y) {
		super(50, 0, new Gun(), "♗", x, y);
	}

}
