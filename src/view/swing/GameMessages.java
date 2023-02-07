package view.swing;

public class GameMessages {

	public GameMessages(String enemyDifficulty) { // NOPMD
		// no action needed in Swing GUI
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
		// not needed in Swing GUI
	}

	/**
	 * Prints global game information
	 */
	public void showHead() {
		// not needed in Swing GUI
	}

}
