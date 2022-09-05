package app.game.bonus;

import java.util.Random;

import app.game.Game;
import app.game.pawns.BasePawn;

public class AddArmorCard implements ICard{

	@Override
	public String getEffect() {
		return "Octroie une valeur aléatoire comprise entre 0 et 20 d'armure à la pièces sélectionnée. (Permanent)";
	}

	@Override
	public void use(Game game) {
		Random rdmValue = new Random();
		BasePawn currentPawn = game.getCurrentPlayer().getCurrentPawn();
		// ajoute à l'armure de la pièce sélectionnée une valeur aléatoire comprise entre 0 et 20.
		currentPawn.setArmor(currentPawn.getArmor()+rdmValue.nextInt(0, 20));
	}
	
}
