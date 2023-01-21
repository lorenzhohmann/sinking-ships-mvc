package view;

import java.util.Scanner;

public class ConsoleGUI {

	public static Scanner scanner = new Scanner(System.in);
	public static final int GAME_INTERRUPTION = 2000;

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

	public static void print(String msg) {
		ConsoleGUI.print(msg, "normal");
	}

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
