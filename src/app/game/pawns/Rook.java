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
	public Rook(int x, int y, PawnColors color) {
		super(125, 75, new BaseWeapon(){}, "♖", x, y);
	}
	
	public Rook(int x, int y) {
		this(x,y,PawnColors.BLACK);
	}

}
