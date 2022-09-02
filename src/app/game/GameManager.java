package app.game;

import java.util.ArrayList;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.players.Player;

public class GameManager {
	private Game game;
	private Player currentPlayer;
	
	public GameManager() {
		this.game = new Game();
	}
	
	public void nextRound() {
		
	}
	
	public void popPawns() {
		Board board = this.game.getBoard();
		ArrayList<BasePawn> playerOnePawns = this.game.getPlayers()[0].getPawns();
		ArrayList<BasePawn> playerTwoPawns = this.game.getPlayers()[1].getPawns();
	}
}
