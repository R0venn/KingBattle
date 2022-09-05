package app.game;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.Knight;
import app.game.pawns.Pawn;
import app.game.pawns.PawnColors;
import app.game.pawns.Queen;
import app.game.pawns.Rook;
import app.game.players.Player;

public class Game {	
	private Board board;
	private Player[] players;
	public static final BasePawn[] PAWNS_AVAILABLES = new BasePawn[] {new Bishop(0,0), new Knight(0,0), new Pawn(0,0), new Queen(0,0), new Rook(0,0)};
	private int gameRound;
	private Player currentPlayer;
	
	public Game() {
		this.board = new Board(this);
		this.players = new Player[2];
	}
	
	public Board getBoard() { return this.board; }
	public Player[] getPlayers() { return this.players; }
	public Player getFirstPlayer() { return this.getPlayers()[0]; }
	public Player getSecondPlayer() { return this.getPlayers()[1]; }
	public Player getCurrentPlayer() { return this.currentPlayer; }
	public void setCurrentPlayer(Player player) { this.currentPlayer = player; }
	
	public Player getOpponent() {
		return this.getFirstPlayer() == this.getCurrentPlayer() ? this.getSecondPlayer() : this.getFirstPlayer();
	}
}