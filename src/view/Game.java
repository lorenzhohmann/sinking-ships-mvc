package view;

public class Game {

	private String enemyDifficulty;
	private GameHandler gameHandler;

	public Game(GameHandler gameHandler, String enemyDifficulty) {
		this.gameHandler = gameHandler;
		this.enemyDifficulty = enemyDifficulty;
	}

	public void showWaitingForSecondPlayer() {
		System.out.println("Warte auf zweiten Spieler...");
	}

	public void showSecondPlayerConnected() {
		System.out.println("Ein zweiter Spieler ist dem Spiel beigetreten!");
	}

	public void printHead() {

		ConsoleGUI.print("");
		ConsoleGUI.print("");
		ConsoleGUI.print("//==============> SPIEL (Schwierigkeit: " + this.enemyDifficulty + ") <==============\\\\");

	}

	public void printEnemyHeadline(int run) {
		ConsoleGUI.print("||                      Gegnerisches Feld                      ||");
		ConsoleGUI.print("||                      " + run + ". Schuss in Folge                     ||");
	}

	public void showShotInstruction() {
		ConsoleGUI.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");
	}

	public boolean printPlayerRound(int run) {
		boolean hasHit = false;
		this.printEnemyHeadline(run);
		this.gameHandler.showPlayersMatchfield();
		this.showShotInstruction();

		// loops for own shot
		while (ConsoleGUI.scanner.hasNext()) {

			String input = ConsoleGUI.scanner.next();

			// check if coordinate has correct syntax => shoot on this position
			if (input.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				hasHit = this.gameHandler.chooseCoordinate(input);

				// check if already hitted
				if (hasHit) {
					break;
				}
			} else if (input.equalsIgnoreCase("?") || input.equalsIgnoreCase("--help")
					|| input.equalsIgnoreCase("help")) {
				ConsoleGUI.showHelp();
			} else {
				ConsoleGUI.print("Ungueltige Eingabe! (Bsp.: A4)", "error");
			}
		}

		return hasHit;
	}

	public void printFieldAlreadyShot() {
		ConsoleGUI.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)", "error");
	}

	public void printShotEvaluation(int run) {
		ConsoleGUI.print("||                      Gegnerisches Feld                      ||");
		ConsoleGUI.print("||                      " + run + ". Schuss in Folge                     ||");
		this.gameHandler.showEnemiesMatchfield();
	}

	public void printAfterShotMessage(boolean fullShipDown) {
		if (fullShipDown) {
			ConsoleGUI.print("Du hast ein komplettes Schiff versenkt! Du darfst noch einmal schiessen!");
		} else {
			ConsoleGUI.print("Schiffsteil getroffen! Du darfst noch einmal schiessen!");
		}
	}

	public void printNoShipHit() {
		ConsoleGUI.print("Kein Schiff getroffen! Dein Gegner ist am Zug!");
	}

}
