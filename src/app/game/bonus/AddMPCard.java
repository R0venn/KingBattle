package app.game.bonus;

import app.game.Game;
import app.game.pawns.BasePawn;

public class AddMPCard implements ICard{

	@Override
	public String getEffect() {
		return "Octoie à un pion un point de déplacement supplémentaire. (1 seule utilisation)";
	}

	@Override
	public void use(Game game) {
		game.getCurrentPlayer().setMP(game.getCurrentPlayer().getMP()+1);
	}

}
