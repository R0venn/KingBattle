package app.game;

import java.util.ArrayList;

import app.game.graphics.Board;
import app.game.pawns.BasePawn;
import app.game.pawns.Bishop;
import app.game.pawns.King;
import app.game.pawns.Knight;
import app.game.pawns.PawnColors;
import app.game.players.Player;
import core.Utils;

public class GameManager {
	private Game game;
	private Player currentPlayer;
	
	public GameManager(String namePlayerOne, String namePlayerTwo) {
		this.game = new Game();
		this.game.getPlayers()[0] = new Player(namePlayerOne, PawnColors.WHITE);
		this.game.getPlayers()[1] = new Player(namePlayerTwo, PawnColors.BLACK);
		Player playerOne = this.game.getFirstPlayer();
		Player playerTwo = this.game.getSecondPlayer();
		playerOne.addPawn(new King(3,0));
		playerTwo.addPawn(new King(3,7));
		this.currentPlayer = playerOne;
	}
	
	public void startGame() {
		this.popPawns();
		while(!this.isMatchEnd()) {
			int[] coordinates = this.currentPlayer.askCoordinates();
			int newX = coordinates[0];
			int newY = coordinates[1];
			BasePawn toMove = this.currentPlayer.getKing();
			this.movePawn(toMove, newX, newY);
			this.nextRound();
		}
	}
	
	public void nextMatch() {
		
	}
	
	public void nextRound() {
		this.currentPlayer = this.currentPlayer == game.getFirstPlayer() ? game.getSecondPlayer() : game.getFirstPlayer();
	}
	
	public void popPawns() {
		Board board = this.game.getBoard();
		ArrayList<BasePawn> playerOnePawns = this.game.getFirstPlayer().getPawns();
		ArrayList<BasePawn> playerTwoPawns = this.game.getSecondPlayer().getPawns();
		for(BasePawn pawns : playerOnePawns) {
			board.addPawn(pawns);
		}
		for(BasePawn _pawns : playerTwoPawns) {
			board.addPawn(_pawns);
		}
		this.game.getBoard().displayBoard();
	}
	
	public boolean movePawn(BasePawn pawn, int newX, int newY) {
		boolean res = false;
		if(!this.game.getBoard().isPawn(newX, newY)) {
			this.game.getBoard().movePawn(pawn.getX(), pawn.getY(), newX, newY);
			pawn.setX(newX);
			pawn.setY(newY);
			res = true;
		}
		return res;
	}
	
	public boolean isMatchEnd() {
		return this.game.getFirstPlayer().getKing().isDead() || this.game.getSecondPlayer().getKing().isDead();
	}
}
