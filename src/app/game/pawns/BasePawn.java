package app.game.pawns;

import app.game.weapons.BaseWeapon;

public abstract class BasePawn {
	/**
	 * 
	 */
	// class attributes
	private int m_health;
	private int m_armor;
	private BaseWeapon m_weapon;
	private String m_model;
	private int m_x;
	private int m_y;
	
	
	// constrcutor(s)
	public BasePawn (int health, int armor, BaseWeapon weapon, String model, int x, int y) {
		this.m_health = health;
		this.m_armor = armor;
		this.m_weapon = weapon;
		this.m_model = model;
		this.m_x = x;
		this.m_y = y;
	}
	
	// methods 
	
	/**
	 * 
	 * @return
	 */
	public int getHealth() {
		return m_health;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setHealth(int health) {
		this.m_health = health;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getArmor() {
		return m_armor;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setArmor(int armor) {
		this.m_armor = armor;
	}
	
	/**
	 * 
	 * @return
	 */
	public BaseWeapon getWeapon() {
		return m_weapon;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setWeapon(BaseWeapon weapon) {
		this.m_weapon = weapon;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getModel() {
		return m_model;
	}
	
	/**
	 * 
	 * @return
	 */
	public void setModel(String model) {
		this.m_model = model;
	}
	
	public String toString() {
		return this.m_model;
	}
}
