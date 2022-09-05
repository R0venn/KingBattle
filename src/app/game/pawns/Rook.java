package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Sniper;

public class Rook extends BasePawn {

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Rook(int x, int y) {
		super(125, 75, new Sniper(), "♖", x, y);
	}

}
