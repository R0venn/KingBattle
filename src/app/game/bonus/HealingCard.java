package app.game.bonus;

import app.game.pawns.BasePawn;

public class HealingCard implements ICard{

	@Override
	public String getEffect() {
		return "Soigne la pièce ciblée de 50 points de vie. (1 seule utilisation)";
	}

	@Override
	public void use(BasePawn pawn) {
		int health = pawn.getHealth();
		pawn.setHealth(health+50);
	}

}
