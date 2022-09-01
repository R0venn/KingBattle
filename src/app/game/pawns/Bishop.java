package app.game.pawns;

import app.game.weapons.BaseWeapon;

public class Bishop extends BasePawn{

	public Bishop(int health, int armor, BaseWeapon weapon, String model) {
		super(75, 15, weapon, "♙");
	}

}
