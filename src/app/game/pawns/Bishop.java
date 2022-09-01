package app.game.pawns;

import app.game.weapons.Weapon;

public class Bishop extends BasePawn{

	public Bishop(int health, int armor, Weapon weapon, String model) {
		super(75, 15, weapon, "♙");
	}

}
