package app.game.bonus;

import app.game.pawns.BasePawn;

public interface ICard {
	
	public String getEffect();
	public void use(BasePawn pawn);
	
	
}
