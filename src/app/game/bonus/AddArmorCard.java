package app.game.bonus;

import java.util.Random;

import app.game.Game;
import app.game.pawns.BasePawn;
import core.Utils;

public class AddArmorCard implements ICard{

	@Override
	public String getEffect() {
		return "Octroie entre 5 et 25 d'armure à la pièces sélectionnée.";
	}

	@Override
	public void use(Game game) {
		BasePawn currentPawn = game.getCurrentPlayer().getCurrentPawn();
		currentPawn.setArmor(currentPawn.getArmor()+Utils.randInt(5, 25));
	}
	
}
