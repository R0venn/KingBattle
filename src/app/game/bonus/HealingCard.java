package app.game.bonus;

import app.game.Game;

public class HealingCard implements ICard{

	@Override
	public String getEffect() {
		return "Soigne la pièce ciblée de 50 points de vie. (1 seule utilisation)";
	}

	@Override
	public void use(Game game) {
		int health = game.getCurrentPlayer().getCurrentPawn().getHealth();
		game.getCurrentPlayer().getCurrentPawn().setHealth(health+50);
	}

}
