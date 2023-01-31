package view.console;

public class GameMessages {

	/**
	 * Difficulty level of the humans enemy (doesn't change while playing)
	 */
	private String enemyDifficulty;

	public GameMessages(String enemyDifficulty) {
		this.enemyDifficulty = enemyDifficulty;
	}

	public void printFieldAlreadyShot() {
		ConsoleGUI.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)", "error");
	}

	/**
	 * Shows a message whether a full ship was hit
	 * 
	 * @param fullShipDown - if the full ship was hit. Conditions the output
	 */
	public void showShotResultMessage(boolean fullShipDown) {
		if (fullShipDown) {
			ConsoleGUI.print("Du hast ein komplettes Schiff versenkt! Du darfst noch einmal schiessen!");
		} else {
			ConsoleGUI.print("Schiffsteil getroffen! Du darfst noch einmal schiessen!");
		}
	}

	public void showWaitingForSecondPlayer() {
		ConsoleGUI.print("Warte auf zweiten Spieler...");
	}

	public void showSecondPlayerConnected() {
		ConsoleGUI.print("Ein zweiter Spieler ist dem Spiel beigetreten!");
	}

	public void showNoShipHit() {
		ConsoleGUI.print("Kein Schiff getroffen! Dein Gegner ist am Zug!");
	}

	public void showInvalidInput() {
		ConsoleGUI.print("Ungueltige Eingabe! (Bsp.: A4)", "error");
	}

	/**
	 * Shows a message with the result of the enemies shot
	 * 
	 * @param wasHit - if a ship was hit. Changes the output
	 */
	public void enemyShotEvaluation(boolean wasHit) {
		if (wasHit) {
			ConsoleGUI.print("Eines Deiner Schiffe wurde getroffen! Dein Gegner ist noch einmal am Zug!");
		} else {
			ConsoleGUI.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");
		}
	}

	/**
	 * Prints global game information
	 */
	public void showHead() {
		ConsoleGUI.print("");
		ConsoleGUI.print("");
		ConsoleGUI.print("//==============> SPIEL (Schwierigkeit: " + this.enemyDifficulty + ") <==============\\\\");
	}

}
