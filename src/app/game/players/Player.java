package app.game.players;

import java.util.ArrayList;

import app.game.pawns.BasePawn;
import app.game.pawns.PawnColors;

public class Player {
	private String m_nickname;
	private PawnColors m_color;
	private int m_score;
	private ArrayList<BasePawn> m_pawns;
	private BasePawn currentPawn;
	
	public Player(String nickname, PawnColors color) {
		this.m_nickname = nickname;
		this.m_color = color;
		this.m_score = 0;
		this.m_pawns = new ArrayList<>();
	}
	
	public String getNickname() { return this.m_nickname; }
	public PawnColors getColor() { return this.m_color; }
	public ArrayList<BasePawn> getPawns() { return this.m_pawns; }
	
	public boolean addPawn(BasePawn pawnToAdd) {
		pawnToAdd.setColor(this.getColor());
		return this.getPawns().add(pawnToAdd);
	}
	
	public boolean removePawn(BasePawn pawnToRemove) {
		return this.getPawns().remove(pawnToRemove);
	}
	
	public void winGameRound() {
		this.m_score++;
	}

	
	public BasePawn getKing() {
		boolean found = false;
		BasePawn toCheck = null;
		BasePawn res = null;
		int pawnsSize = this.getPawns().size();
		int i = 0;
		while(!found && i < pawnsSize) {
			toCheck = this.getPawns().get(i);
			if(toCheck.getModel().equals("♔")) {
				res = toCheck;
				found = true;
			}
			i++;
		}
		return res;
	}
	
	public BasePawn getPawnFromPos(int x, int y) {
		boolean found = false;
		BasePawn toCheck = null;
		BasePawn res = null;
		int pawnsSize = this.getPawns().size();
		int i = 0;
		while(!found && i < pawnsSize) {
			toCheck = this.getPawns().get(i);
			if(toCheck.getX() == x && toCheck.getY() == y) {
				res = toCheck;
				found = true;
			}
			i++;
		}
		return res;
	}
}
