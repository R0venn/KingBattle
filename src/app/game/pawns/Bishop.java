package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Bishop extends BasePawn{

	public Bishop(int x, int y) {
		super(75, 15, new BaseWeapon(){} , "♗", x, y);
	}

}
