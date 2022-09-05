package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Sword;

public class Knight extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Knight(int x, int y) {
		super(150, 25, new Sword(), "♘", x, y);
	}

}
