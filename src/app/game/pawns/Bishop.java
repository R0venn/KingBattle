package app.game.pawns;

import app.game.weapons.BaseWeapon;
import app.game.weapons.Dagger;

public class Bishop extends BasePawn{
	
	public Bishop() {
		super(75, 15, new Dagger(), "♗", 0, 0);
	}

	public boolean canMoveTo(int x, int y) {
		int[] absoluteDist = this.getAbsoluteDistance(x, y);
		int xDist = absoluteDist[0]; int yDist = absoluteDist[1];
		return xDist == yDist;
	}
	
	public void resetPawn() {
		PawnPosition pos = PawnPosition.BISHOP;
		this.setHealth(75);
		this.setArmor(15);
		this.setWeapon(new Dagger());
		this.setX(pos.getX());
		this.setY(pos.getY(this.getBaseColor()));
	}
}
