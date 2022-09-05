package app.game.pawns;
import core.ConsoleColors;

public enum PawnColors {
	WHITE(ConsoleColors.WHITE_BACKGROUND_BRIGHT),
	BLACK(ConsoleColors.BLACK_BACKGROUND);
	
	private String color;

	PawnColors(String color) {
		this.color = color;
	}

	String getColor() {
		return color;
	}
	
	public String toString() {
		return this == PawnColors.BLACK ? "noir" : "blanc";
	}
}