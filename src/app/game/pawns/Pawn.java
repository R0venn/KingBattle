package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Pawn extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Pawn(int x, int y, PawnColors color) {
		super(50, 0, new BaseWeapon(){}, "♗", x, y);
	}
	
	public Pawn(int x, int y) {
		this(x,y,PawnColors.BLACK);
	}

}
