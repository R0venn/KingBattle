package app.game.bonus;

import java.util.Random;

import app.game.pawns.BasePawn;

public class CriticalDmgCard implements ICard{

	@Override
	public String getEffect() {
		return "Octroie à la pièce sélectionnée 20% de coup critique. (Permanent)";
	}

	@Override
	public void use(BasePawn pawn) {
		int dmg = pawn.getWeapon().getDamage(); 
		pawn.getWeapon().setDamage((int) Math.abs((dmg + (dmg*0.2))));
	}


	
}
