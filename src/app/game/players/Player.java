package app.game.players;

import java.util.ArrayList;
import java.util.Scanner;

import app.Utils;
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
	
	public int getScore() { return this.m_score; }
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
	
	public void winGameMatch() {
		this.m_score++;
		System.out.println("Congratulation " + this.getNickname() + " you won the match and got 1 point!");
		System.out.println("You have now " + this.getScore() + " points out of 3 :)");
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
	
	public int[] askCoordinates() {
		int[] res = new int[2];
		Scanner scanner = new Scanner(System.in);
		String userInput;
		do {
			System.out.println("Entrez des coordonées (ex : b7)");
			userInput = scanner.nextLine();
		} while(!Utils.areValidsCoordinates(userInput));
		return Utils.computeCoordinates(userInput);
	}
	
	public int askDigit() {
		String userInput;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Entrez votre choix :");
			userInput = scanner.nextLine();
		} while(!Utils.isValidDigit(userInput));
		return Integer.parseInt(userInput);
	}
}
