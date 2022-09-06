package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;
import app.game.weapons.MagicStaff;

public class Queen extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Queen() {
		super(75, 25, new MagicStaff(), "♕", 0, 0);
	}

	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return (xDist == yDist) || ((xDist <= 5 && yDist == 0) || (xDist == 0 && yDist <= 5));
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.QUEEN;
		this.setHealth(75);
		this.setArmor(25);
		this.setWeapon(null);
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}
}
