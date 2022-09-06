package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;
import app.game.weapons.Shotgun;

public class King extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public King() {
		super(200, 50, new Shotgun(), "♔", 0, 0);
	}
	
	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return xDist <= 2 && yDist <= 2;
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.KING;
		this.setHealth(200);
		this.setArmor(50);
		this.setWeapon(new Shotgun());
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}

}
