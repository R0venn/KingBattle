package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;

public class Queen extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Queen() {
		super(175, 25, null, "♕", 0, 0);
	}

	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return (xDist == yDist) || ((xDist <= 5 && yDist == 0) || (xDist == 0 && yDist <= 5));
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.QUEEN;
		this.setHealth(175);
		this.setArmor(25);
		this.setWeapon(null);
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}
}
