package view.console;

import java.util.Scanner;

public class ConsoleGUI {

	/**
	 * The console reader object (needs to be global) Java can't handle multiple
	 * scanners in one program, so it needs to be global
	 */
	public static final Scanner scanner = new Scanner(System.in);

	/**
	 * Time between a game instruction and the following action (in ms)
	 */
	public static final int GAME_INTERRUPTION = 2000;

	/**
	 * Shows a notification on the console
	 * 
	 * @param msg   - the message to appear
	 * @param style - adds a suffix and prefix for the message (error, waiting,
	 *              highlight)
	 */
	public static void print(String msg, String style) {
		switch (style) {
		case "error":
			System.out.println("<error> " + msg + " <error>");
			break;
		case "waiting":
			System.out.println("<waiting> " + msg + " <waiting>");
			break;
		case "highlight":
			System.out.println("==> " + msg);
			break;
		default:
			System.out.println(msg);
			break;
		}
	}

	/**
	 * Shows a notification on the console with the style 'normal'
	 * 
	 * @param msg - the message to appear
	 */
	public static void print(String msg) {
		ConsoleGUI.print(msg, "normal");
	}

	/**
	 * Prints the help menu to the console
	 */
	public static void showHelp() {
		System.out.println();
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||                     Du brauchst Hilfe?!                     ||");
		System.out.println("||    --------------------------------------------------       ||");
		System.out.println("||    ~ ==>> Kein Treffer, du hast ins Wasser geschossen       ||");
		System.out.println("||                                                             ||");
		System.out.println("||    o ==>> Hier befindet sich dein eigenes Schiff            ||");
		System.out.println("||                                                             ||");
		System.out.println("||    x ==>> Du hast einen Teil des Schiffes getroffen         ||");
		System.out.println("||                                                             ||");
		System.out.println("||    X ==>> Schiff komplett versenkt                          ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}

}
