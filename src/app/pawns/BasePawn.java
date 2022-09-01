package app.pawns;

import app.weapons.Weapon;

public abstract class BasePawn {
	/**
	 * 
	 */
	// class attributes
	private int health;
	private int armor;
	private Weapon weapon;
	private char model;
	
	
	// constrcutor(s)
	public BasePawn (int health, int armor, Weapon weapon, char model) {
		this.health = health;
		this.armor = armor;
		this.weapon = weapon;
		this.model = model;
	}
	
	// methods 
	
	/**
	 * 
	 * @return
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getArmor() {
		return armor;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	/**
	 * 
	 * @return
	 */
	public Weapon getWeapon() {
		return weapon;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}
	
	/**
	 * 
	 * @return
	 */
	public char getModel() {
		return model;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setModel(char model) {
		this.model = model;
	}
	
	
}
