package app.game;

import java.util.ArrayList;
import java.util.Arrays;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.Knight;
import app.game.pawns.Pawn;
import app.game.pawns.PawnColors;
import app.game.pawns.Queen;
import app.game.pawns.Rook;
import app.game.players.Player;
import core.Utils;

public class Game {	
	public final static int ROUND_TO_WIN = 1;
	
	private Board board;
	private Board saveBoard;
	private Player[] players;
	private Player currentPlayer;
	
	public static ArrayList<BasePawn> availablePawns = new ArrayList<>(Arrays.asList(new Bishop(), new Knight(), new Queen(), new Rook()));
	
	public Game() {
		this.board = new Board(this);
		this.players = new Player[2];
		this.saveCurrentBoard();
	}
	
	public Board getBoard() { return this.board; }
	public Player[] getPlayers() { return this.players; }
	public Player getFirstPlayer() { return this.getPlayers()[0]; }
	public Player getSecondPlayer() { return this.getPlayers()[1]; }
	public Player getCurrentPlayer() { return this.currentPlayer; }
	public Board getSavedBoard() { return this.saveBoard; }
	public void setCurrentPlayer(Player player) { this.currentPlayer = player; }
	
	public BasePawn pickRandomPawn() {
		int random = Utils.randInt(0, Game.availablePawns.size());
		BasePawn pawn = Game.availablePawns.get(random);
		return pawn;
	}
	
	public void saveCurrentBoard() {
		this.saveBoard = new Board(this.board);
	}
	
	public void swapBoards() {
		this.board = new Board(this.getSavedBoard());
	}
	
	public Player getOpponent() {
		return this.getFirstPlayer() == this.getCurrentPlayer() ? this.getSecondPlayer() : this.getFirstPlayer();
	}
}