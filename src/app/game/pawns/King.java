package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class King extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public King(int x, int y, PawnColors color) {
		super(200, 50, new BaseWeapon(){} , "♔", x, y);
	}
	
	public King(int x, int y) {
		this(x,y,PawnColors.BLACK);
	}

}
