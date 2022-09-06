package app.game.bonus;

import java.util.Random;

import app.game.Game;
import app.game.pawns.BasePawn;

public class CriticalDmgCard implements ICard{

	@Override
	public String getEffect() {
		return "Octroie à la pièce sélectionnée 20% de dégats en plus.";
	}

	@Override
	public void use(Game game) {
		int dmg = game.getCurrentPlayer().getCurrentPawn().getWeapon().getDamage(); 
		game.getCurrentPlayer().getCurrentPawn().getWeapon().setDamage((int) (dmg*1.2));
	}
	
}
