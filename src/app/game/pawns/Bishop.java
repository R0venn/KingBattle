package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Bishop extends BasePawn{

	public Bishop(int x, int y, PawnColors color) {
		super(75, 15, new BaseWeapon(){} , "♗", x, y, color);
	}
	
	public Bishop(int x, int y) {
		this(x,y, PawnColors.BLACK);
	}

}
