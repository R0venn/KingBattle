package app.game.weapons;

public abstract class BaseWeapon {
	
	/**
	 * 
	 */
	// class attributes
	private int range;
	private int damage;
	
	// constructor(s)
	public BaseWeapon(int range, int damage) {
		this.range = range;
		this.damage = damage;
	}

	// methods
	public int getRange() {
		return range;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}
}
