package app.game.pawns;

public enum PawnPosition {
	KING(4,0),
	QUEEN(3,0),
	KNIGHT(5,0),
	BISHOP(1,0),
	ROOK(0,0),
	PAWN(4,1);

	private int x;
	private int y;
	
	PawnPosition(int i, int j) {
		this.x = x;
		this.y = y;
	}

	public int getPawnPositionX() {
		return this.x;
	}
	
	public int getPawnPositionY() {
		return this.y;
	}
	
	public static int blackPawnPositionX(PawnPosition pp) {
		return pp.getPawnPositionX() + 7;
	}
	public static int blackPawnPositionY(PawnPosition pp) {
		return pp.getPawnPositionY() + 7;
	}
}
