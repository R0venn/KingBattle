package app.game.players;

import java.util.ArrayList;
import java.util.Scanner;

import app.Utils;
import app.game.Game;
import app.game.bonus.BonusCardFactory;
import app.game.bonus.ICard;
import app.game.pawns.BasePawn;
import app.game.pawns.PawnColors;

public class Player {
	private String m_nickname;
	private PawnColors m_color;
	private int m_score;
	private ArrayList<BasePawn> m_pawns;
	private BasePawn currentPawn;
	private int m_ap;
	private int m_mp;
	private ArrayList<ICard> m_powers;
	
	public Player(String nickname, PawnColors color) {
		this.m_nickname = nickname;
		this.m_color = color;
		this.m_score = 0;
		this.m_pawns = new ArrayList<>();
		this.m_powers = new ArrayList<>();
		this.resetPoints();
	}
	
	public int getScore() { return this.m_score; }
	public String getNickname() { return this.m_nickname; }
	public PawnColors getColor() { return this.m_color; }
	public ArrayList<BasePawn> getPawns() { return this.m_pawns; }
	public BasePawn getCurrentPawn() { return this.currentPawn; }
	public void setCurrentPawn(BasePawn pawn) { this.currentPawn = pawn; }
	public void setMP(int mp) { this.m_mp = mp; }
	public void setAP(int ap) { this.m_ap = ap; }
	public int getMP() { return this.m_mp; }
	public int getAP() { return this.m_ap; }
	public ArrayList<ICard> getPowers() { return this.m_powers; }
	public void useMP() { if(this.canMove()) this.setMP(this.getMP()-1); }
	public void useAP() { if(this.canUseAP()) this.setAP(this.getAP()-1); }
	
	public void addPower(ICard cardPower) {
		this.getPowers().add(cardPower);
		core.Utils.info(this.getNickname()+" vous avez obtenu : " + cardPower.getEffect());
		core.Utils.sleep(3);
	}
	
	public void addRandomPower() {
		this.addPower(BonusCardFactory.getRandomCard());
	}
	
	public boolean usePower(ICard cardPower, Game game) {
		boolean res = false;
		if(this.getPowers().remove(cardPower)) {
			res = true;
			cardPower.use(game);
		}
		return res;
	}
	
	public void resetPawns() {
		for(BasePawn pawn : this.getPawns()) {
			pawn.resetPawn();
		}
	}
	
	public void checkCurrentPawnAlive() {
		if(this.getCurrentPawn().isDead()) this.setCurrentPawn(this.getAlivePawns().get(0));
	}
	
	public boolean canMove() {
		return this.getMP() > 0;
	}
	
	public boolean canUseAP() { return this.getAP() > 0; }
	
	public boolean canAttack(ArrayList<BasePawn> opponentPawns) {
		return this.getAP() > 0 && this.getCurrentPawn().hasSomeoneInRange(opponentPawns);
	}
	
	public boolean isFriendlyPawn(BasePawn pawn) {
		int i = 0;
		boolean res = false;
		do {
			if(this.getAlivePawns().get(i) == pawn) res = true;
			i++;
		} while (!res && i < this.getAlivePawns().size());
		return res;
	}
	
	public ArrayList<BasePawn> getAlivePawns() {
		ArrayList<BasePawn> res = new ArrayList<>();
		for(BasePawn pawn : this.getPawns()) {
			if(!pawn.isDead()) {
				res.add(pawn);
			}
		}
		return res;
	}
	
	public boolean canAction(ArrayList<BasePawn> opponentPawns) { return this.canMove() || this.canAttack(opponentPawns); }
	
	public boolean onePawnCanAttack(ArrayList<BasePawn> opponentPawns) {
		boolean res = false;
		int i = 0;
		ArrayList<BasePawn> pPawns = this.getPawns();
		while(!res && i < pPawns.size()) {
			if(pPawns.get(i).hasSomeoneInRange(opponentPawns)) {
				res = true;
			}
			i++;
		}
		return res;
	}
	
	public void resetPoints() {
		this.m_ap = 1;
		this.m_mp = 1;
	}
	
	public boolean addPawn(BasePawn pawnToAdd) {
		pawnToAdd.setColor(this.getColor());
		return this.getPawns().add(pawnToAdd);
	}
	
	public boolean removePawn(BasePawn pawnToRemove) {
		return this.getPawns().remove(pawnToRemove);
	}
	
	public void winGameMatch() {
		this.m_score++;
		System.out.println("Bravo " + this.getNickname() + " ! Vous gagnez le match et obtenez un point supplémentaire !");
		System.out.println("Vous avez " + this.getScore() + "/3 points pour gagner la partie :)");
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
	
	public int[] askCoordinates(String message) {
		int[] res = new int[2];
		Scanner scanner = new Scanner(System.in);
		String userInput;
		do {
			System.out.println(message);
			userInput = scanner.nextLine();
		} while(!Utils.areValidsCoordinates(userInput));
		return Utils.computeCoordinates(userInput);
	}
	
	public int[] askCoordinates() {
		return this.askCoordinates("Entrez des coordonées (ex : b7)");
	}
	
	public int askDigit(String message) {
		String userInput;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println(message);
			userInput = scanner.nextLine();
		} while(!Utils.isValidDigit(userInput));
		return Integer.parseInt(userInput);
	}
	
	public int askDigit() {
		return this.askDigit("Entrez votre choix : ");
	}
}
