package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;
import app.game.weapons.Sword;

public class Knight extends BasePawn{

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Knight() {
		super(150, 25, new Sword(), "♘", 0, 0);
	}
	
	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return (xDist == 2 && yDist == 1) || (yDist == 2 && xDist == 1);
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.QUEEN;
		this.setHealth(150);
		this.setArmor(25);
		this.setWeapon(new Sword());
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}

}
