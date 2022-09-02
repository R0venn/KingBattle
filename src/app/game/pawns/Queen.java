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
	public Queen(int x, int y, PawnColors color) {
		super(175, 25, new BaseWeapon(){}, "♕", x, y);
	}
	
	public Queen(int x, int y) {
		this(x,y,PawnColors.BLACK);
	}

}
