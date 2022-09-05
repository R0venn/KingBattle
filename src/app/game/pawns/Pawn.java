package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;
import app.game.weapons.Gun;

public class Pawn extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Pawn() {
		super(50, 0, new Gun(), "♗", 0, 0);
	}

	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return xDist == 1 && yDist == 0;
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.PAWN;
		this.setHealth(50);
		this.setArmor(0);
		this.setWeapon(new Gun());
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}
}
