package view.swing;

public class GameMessages {

	/**
	 * Difficulty level of the humans enemy (doesn't change while playing)
	 */
	private String enemyDifficulty;

	public GameMessages(String enemyDifficulty) {
		this.enemyDifficulty = enemyDifficulty;
	}

	public void printFieldAlreadyShot() {
		FrameGUI.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus!", "error");
	}

	/**
	 * Shows a message whether a full ship was hit
	 * 
	 * @param fullShipDown - if the full ship was hit. Conditions the output
	 */
	public void showShotResultMessage(boolean fullShipDown) {
		// not needed in Swing GUI
//		if (fullShipDown) {
//			FrameGUI.print("Du hast ein komplettes Schiff versenkt! Du darfst noch einmal schiessen!");
//		} else {
//			FrameGUI.print("Schiffsteil getroffen! Du darfst noch einmal schiessen!");
//		}
	}

	public void showWaitingForSecondPlayer() {
		FrameGUI.print("Warte auf zweiten Spieler...");
	}

	public void showSecondPlayerConnected() {
		FrameGUI.print("Ein zweiter Spieler ist dem Spiel beigetreten!");
	}

	public void showNoShipHit() {
		FrameGUI.print("Kein Schiff getroffen! Dein Gegner ist am Zug!");
	}

	public void showInvalidInput() {
		FrameGUI.print("Ungueltige Eingabe!", "error");
	}

	/**
	 * Shows a message with the result of the enemies shot
	 * 
	 * @param wasHit - if a ship was hit. Changes the output
	 */
	public void enemyShotEvaluation(boolean wasHit) {
		if (wasHit) {
			FrameGUI.print("Eines Deiner Schiffe wurde getroffen! Dein Gegner ist noch einmal am Zug!");
		} else {
			FrameGUI.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");
		}
	}

	/**
	 * Prints global game information
	 */
	public void showHead() {
		FrameGUI.print("");
		FrameGUI.print("");
		FrameGUI.print("//==============> SPIEL (Schwierigkeit: " + this.enemyDifficulty + ") <==============\\\\");
	}

}
