package app.game.pawns;

import java.util.ArrayList;

import app.game.graphics.Board;
import app.game.weapons.BaseWeapon;
import core.Utils;

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
	private PawnColors m_color;
	
	
	// constrcutor(s)
	public BasePawn (int health, int armor, BaseWeapon weapon, String model, int x, int y, PawnColors color) {
		this.m_health = health;
		this.m_armor = armor;
		this.m_weapon = weapon;
		this.m_model = model;
		this.m_x = x;
		this.m_y = y;
		this.m_color = color;
	}
	
	public BasePawn (int health, int armor, BaseWeapon weapon, String model, int x, int y) {
		this(health, armor, weapon, model, x, y, PawnColors.BLACK);
	}
	
	// methods 
	
	public void setColor(PawnColors color) {
		this.m_color = color;
	}
	
	public int getX() { return this.m_x; }
	public int getY() { return this.m_y; }
	
	public void setX(int newX) { this.m_x = newX; }
	public void setY(int newY) { this.m_y = newY; }
	
	public String getColor() { return this.m_color.getColor(); }
	public PawnColors getBaseColor() { return this.m_color; }
	
	public boolean isDead() {
		return this.getHealth() <= 0;
	}
	
	public String getInfos() {
		return this.getModel() + " vie: " + this.getHealth() + " armure: " + this.getArmor() + " arme: " +this.getWeapon();
	}
	
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
		this.m_health = health < 0 ? 0 : health;
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
		this.m_armor = armor < 0 ? 0 : armor;
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
		return this.m_model + " t";
	}
	
	/* Should be deprecated */
	public boolean attack(int x, int y, Board board) {
		boolean res = false;
		BasePawn pawnToAttack =  board.getPawn(x, y);
		
		if(pawnToAttack != this) {
			if(pawnToAttack != null) {
				if(this.rangeToAttack(pawnToAttack)) {
					int weaponDamage = this.getWeapon().getDamage();
					int newHealth = pawnToAttack.getHealth() - weaponDamage;
					pawnToAttack.setHealth(newHealth);
					Utils.info("Tu as touché le " + pawnToAttack.getModel() + " pour " + weaponDamage + " points de vie !\nIl lui en reste "+newHealth);
					res = true;
				} else {
					Utils.info("Cette unité est trop loin !");
				}
			} else {
				Utils.info("Il n'y a personne à attaquer :(");
			}
		} else {
			Utils.info("Tu ne peux pas t'attaquer toi même !");
		}
		Utils.sleep(2);
		return res;
	}
	
	public void doDamage(int damage) {
		int remainingArmor = this.getArmor() - damage;
		this.setArmor(remainingArmor);
		if(remainingArmor < 0) this.setHealth(this.getHealth() - Math.abs(remainingArmor));
	}
	
	public void attack(BasePawn target) {
		if(this.rangeToAttack(target)) {
			int weaponDamage = this.getWeapon().getDamage();
			target.doDamage(weaponDamage);
			Utils.info("Tu as touché le " + target.getModel() + " adverse pour " + weaponDamage + " points de vie !\nIl lui reste "+target.getHealth() +" pv et " + target.getArmor() +" d'armure.");
			Utils.sleep(3);
		}
	}
	
	public boolean rangeToAttack(BasePawn pawnToAttack) {
		boolean inRange = true;
		
		int distX = Math.abs(this.getX() - pawnToAttack.getX());
		int distY = Math.abs(this.getY() - pawnToAttack.getY());
		
		int weaponRange = this.getWeapon().getRange();
		
		if(distX > weaponRange || distY > weaponRange) inRange = false;
		
		return inRange;
	}
	
	public ArrayList<BasePawn> getPawnsInAttackRange(ArrayList<BasePawn> pawns) {
		ArrayList<BasePawn> res = new ArrayList<>();
		for(BasePawn pawn : pawns) {
			if(this.rangeToAttack(pawn)) {
				res.add(pawn);
			}
		}
		return res;
	}
	
	public boolean hasSomeoneInRange(ArrayList<BasePawn> pawns) {
		boolean res = false;
		int i = 0;
		do {
			if(this.rangeToAttack(pawns.get(i))) {
				res = true;
			}
			i++;
		} while(!res && i < pawns.size());
		return res;
	}
	
	public int[] getAbsoluteDistance(int x, int y) {
		int xDist = Math.abs(x - this.getX());
		int yDist = Math.abs(y - this.getY());
		return new int[] {yDist, xDist};
	}
	
	public abstract boolean canMoveTo(int x, int y);
	public abstract void resetPawn();
	
}
