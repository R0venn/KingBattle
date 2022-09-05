package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;

public class Bishop extends BasePawn{
	
	public Bishop(int x, int y) {
		super(75, 15, new Dagger(), "♙", x, y);
	}

}
