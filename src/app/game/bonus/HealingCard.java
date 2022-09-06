package app.game.bonus;

import app.game.Game;

public class HealingCard implements ICard{

	@Override
	public String getEffect() {
		return "Augmente les points de vie de votre pièce atuelle de 50.";
	}

	@Override
	public void use(Game game) {
		int health = game.getCurrentPlayer().getCurrentPawn().getHealth();
		game.getCurrentPlayer().getCurrentPawn().setHealth(health+50);
	}

}
