package app.pawns;

import app.weapons.Weapon;

public class Bishop extends BasePawn{

	public Bishop(int health, int armor, Weapon weapon, char model) {
		super(75, 15, weapon, '♙');
	}

}
