package view.console;

import java.io.IOException;
import java.util.Locale;

import model.Difficulty;

public class Menu {

	/**
	 * Handler for all menu actions
	 */
	private MenuHandler menuHandler;

	public Menu(MenuHandler handler) {
		this.menuHandler = handler;
	}

	/**
	 * Shows the gamemode menu and reads the users console input
	 */
	public void showGameModeMenu() {
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||                    Waehle einen Spielmodi!                  ||");
		System.out.println("||          ___________________       _________________        ||");
		System.out.println("||         |                   |     |                 |       ||");
		System.out.println("||         | Einzelspieler [E] |     | Mehrspieler [M] |       ||");
		System.out.println("||         |___________________|     |_________________|       ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");

		while (ConsoleGUI.scanner.hasNext()) {

			String input = ConsoleGUI.scanner.next();

			if ("e".equalsIgnoreCase(input)) { // start singleplayer session

				this.menuHandler.chooseSinglePlayer();
				return;
			} else if ("m".equalsIgnoreCase(input)) { // start multiplayer session
				System.out.println("MULTIPLAYER IS NOT WORKING");
				// TODO multiplayer
			}

			ConsoleGUI.print("Ungueltige Eingabe!", "error");
		}
	}

	/**
	 * Shows the options to choose the AIs difficulty. It also reads the users
	 * console input
	 */
	public void showAIDifficultyMenu() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||               Wie stark soll Dein Gegner sein?              ||");
		System.out.println("||        _____________    ____________    ____________        ||");
		System.out.println("||       |             |  |            |  |            |       ||");
		System.out.println("||       | Einfach [E] |  | Schwer [S] |  | Extrem [X] |       ||");
		System.out.println("||       |_____________|  |____________|  |____________|       ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");

		String chosenDifficulty;
		while (ConsoleGUI.scanner.hasNext()) {
			chosenDifficulty = ConsoleGUI.scanner.next().toLowerCase(Locale.GERMAN);

			if ("e".equalsIgnoreCase(chosenDifficulty)) {
				menuHandler.setDifficulty(Difficulty.EASY);
				break;
			} else if ("s".equalsIgnoreCase(chosenDifficulty)) {
				menuHandler.setDifficulty(Difficulty.HARD);
				break;
			} else if ("x".equalsIgnoreCase(chosenDifficulty)) {
				menuHandler.setDifficulty(Difficulty.EXTREM);
				break;
			}

			ConsoleGUI.print("Ungueltige Eingabe!", "error");
			this.showAIDifficultyMenu();
		}
	}
}
