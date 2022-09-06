package app.game.bonus;

import app.game.Game;
import app.game.pawns.BasePawn;

public class AddAPCard implements ICard{

	@Override
	public String getEffect() {
		return "Vous octroie un PM de plus pour ce tour.";
	}

	@Override
	public void use(Game game) {
		game.getCurrentPlayer().setMP(game.getCurrentPlayer().getMP()+1);
	}
}
