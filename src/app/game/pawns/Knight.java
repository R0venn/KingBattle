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
	public Knight(int x, int y, PawnColors color) {
		super(150, 25, new BaseWeapon(){}, "♘", x, y);
	}
	
	public Knight(int x, int y) {
		this(x,y,PawnColors.BLACK);
	}

}
