package view;

import java.io.IOException;

public class Menu {

	private MenuHandler menuHandler;

	public Menu(MenuHandler handler) {
		this.menuHandler = handler;
	}

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

			if (input.equalsIgnoreCase("e")) { // start singleplayer session

				this.menuHandler.chooseSinglePlayer();
				ConsoleGUI.scanner.close();
				return;
			} else if (input.equalsIgnoreCase("m")) { // start multiplayer session

				// TODO multiplayer

			}

			ConsoleGUI.print("Ungueltige Eingabe!", "error");
		}
	}

	public void showSettings() {
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
			chosenDifficulty = ConsoleGUI.scanner.next().toLowerCase();

			if (chosenDifficulty.equalsIgnoreCase("e")) {
				menuHandler.setDifficulty(Difficulty.EASY);
				break;
			} else if (chosenDifficulty.equalsIgnoreCase("s")) {
				menuHandler.setDifficulty(Difficulty.HARD);
				break;
			} else if (chosenDifficulty.equalsIgnoreCase("x")) {
				menuHandler.setDifficulty(Difficulty.EXTREM);
				break;
			}

			ConsoleGUI.print("Ungueltige Eingabe!", "error");
			this.showSettings();
		}
	}

	public void showHelp() {
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
