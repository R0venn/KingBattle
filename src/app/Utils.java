package app;

abstract public class Utils {
	public static int[] computeCoordinates(String input) {
		int first = input.charAt(0) - 'a';
		int second = Integer.parseInt(input.substring(1, 2)) - 1;
		return new int[] {first, second};
	}
	
	public static boolean areValidsCoordinates(String input) {
		if(input.length() != 2) return false;
		int[] computedCoordinates = Utils.computeCoordinates(input);
		int first = computedCoordinates[0];
		int second = computedCoordinates[1];
		if(first < 0 || first > 7) return false;
		if(second < 0 || second > 7) return false;
		return true;
	}
}
