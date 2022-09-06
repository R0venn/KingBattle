package core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Random;


public abstract class Utils {
	public static final int SECOND_IN_MS = 1000;
	
	public static void clearScreen() {
		System.out.print("\033[H\033[2J");  
		System.out.flush();
	}
	
	public static void clearLine() {
		int count = 1; 
		System.out.print(String.format("\033[%dA",count)); // Move up
		System.out.print("\033[2K"); // Erase line content
	}
	
	public static int randInt(int min, int max) {
		Random random = new Random();
		return random.nextInt((max+1) + min) + min;
	}
	
	public static void debug(String log) {
		System.out.println(ConsoleColors.GREEN_BACKGROUND + "[KingBattle DEBUGGER] " + log + ConsoleColors.RESET);
	}
	
	public static void purple(String message) {
		System.out.println(ConsoleColors.PURPLE+message+ConsoleColors.RESET);
	}
	
	public static void info(String info) {
		System.out.println("\n"+ConsoleColors.CYAN + info + ConsoleColors.RESET);
	}
	
	public static void error(String error) {
		System.out.println(ConsoleColors.RED_BACKGROUND + error + ConsoleColors.RESET);
	}
	
	public static void green(String message) {
		System.out.println(ConsoleColors.GREEN + message + ConsoleColors.RESET);
	}
	
	public static void red(String message) {
		System.out.println(ConsoleColors.RED_BRIGHT + message + ConsoleColors.RESET);
	}
	
	public static void sleep(int seconds) {
		try {
			Thread.sleep(seconds*Utils.SECOND_IN_MS);
		} catch (InterruptedException e) {
			Utils.debug("Couldn't sleep thread for " + seconds + "s");
		}
	}
	
	public static void appendToLogFile(String log) throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("../logs/log.txt", true));
		writer.append("[KingBattle LOGGER] "+log+"\n");
		writer.close();
	}
}