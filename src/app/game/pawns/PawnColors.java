package app.game.pawns;
import core.ConsoleColors;

public enum PawnColors {
	
	WHITE(ConsoleColors.WHITE_BACKGROUND),
	BLACK(ConsoleColors.BLACK_BACKGROUND);

	private String color;
	
	PawnColors(String color) {
		this.color = color;
	}
	
	public String getColor() {
		return this.color;
	}
}