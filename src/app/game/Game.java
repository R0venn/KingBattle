package app.game;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.Knight;
import app.game.pawns.Pawn;
import app.game.pawns.Queen;
import app.game.pawns.Rook;
import app.game.players.Player;

public class Game {
	private Board board;
	private Player[] players;
	public static final BasePawn[] PAWNS_AVAILABLES = new BasePawn[] {new Bishop(), new Knight(), new Pawn(), new Queen(), new Rook()};
	
	public Game() {
		this.board = new Board();
		this.players = new Player[2];
	}
	
	public Board getBoard() { return this.board; }
	public Player[] getPlayers() { return this.players; }
}