package app.game;

import app.game.graphics.Board;
import app.game.players.Player;

public class Game {
	private Board board;
	private Player[] players;
	
	public Game() {
		this.board = new Board();
		this.players = new Player[2];
	}
	
	public Board getBoard() { return this.board; }
	public Player[] getPlayers() { return this.players; }
}