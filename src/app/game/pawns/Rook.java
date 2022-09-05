package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;
import app.game.weapons.Sniper;

public class Rook extends BasePawn {

	/**
	 * 
	 * @param health
	 * @param armor
	 * @param weapon
	 * @param model
	 */
	public Rook() {
		super(125, 75, new Sniper(), "♖", 0, 0);
	}
	
	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return (xDist <= 4 && yDist == 0) || (xDist == 0 && yDist <= 4);
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.ROOK;
		this.setHealth(125);
		this.setArmor(75);
		this.setWeapon(new Sniper());
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}

}
