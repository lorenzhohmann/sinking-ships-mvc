package view.console;

public class GameView {

	/**
	 * Difficulty level of the humans enemy (doesn't change while playing)
	 */
	private String enemyDifficulty;
	private GameHandler gameHandler;

	public GameView(GameHandler gameHandler, String enemyDifficulty) {
		this.gameHandler = gameHandler;
		this.enemyDifficulty = enemyDifficulty;
	}

	/**
	 * Displays the result of a players shot
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showPlayerShotEvaluation(int shotInARow) {
		this.showEnemyHeadline(shotInARow);
		this.gameHandler.showEnemiesMatchfield();
	}

	/**
	 * Shows the players round information and reads the users input from the
	 * console and passes it to the controller
	 * 
	 * @param shotsInARow - the amount of shots in a row
	 */
	public void showPlayerRound(int shotsInARow) {
		this.showEnemyHeadline(shotsInARow);
		this.gameHandler.showEnemiesMatchfield();
		this.showShotInstruction();

		// loops for own shot
		while (ConsoleGUI.scanner.hasNext()) {

			String input = ConsoleGUI.scanner.next();
			boolean moveSuccessfull = this.gameHandler.doMove(input);

			if (moveSuccessfull) {
				break;
			}
		}
	}

	/**
	 * Shows the enemies round information
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesRound(int shotInARow) {
		this.showEnemiesMatchfield(shotInARow);
		this.showEnemiesTurn();
	}

	public void showEnemiesMatchfield(int shotInARow) {
		this.showPlayersHeadline(shotInARow);
		this.gameHandler.showPlayersMatchfield();
	}

	/**
	 * Prints global game information
	 */
	public void showHead() {
		ConsoleGUI.print("");
		ConsoleGUI.print("");
		ConsoleGUI.print("//==============> SPIEL (Schwierigkeit: " + this.enemyDifficulty + ") <==============\\\\");
	}

	/**
	 * Shows the information with a label
	 * 
	 * @param shotsInARow - the amount of shots in a row
	 */
	private void showshotInARow(int shotsInARow) {
		ConsoleGUI.print("||                      " + shotsInARow + ". Schuss in Folge                     ||");
	}

	/**
	 * Shows the headline for enemies round
	 * 
	 * @param shotsInARow - the amount of shots in a row
	 */
	private void showEnemyHeadline(int shotsInARow) {
		ConsoleGUI.print("||                      Gegnerisches Feld                      ||");
		this.showshotInARow(shotsInARow);
	}

	/**
	 * Shows the headline for players round
	 * 
	 * @param shotsInARow - the amount of shots in a row
	 */
	private void showPlayersHeadline(int shotsInARow) {
		ConsoleGUI.print("||                         Eigenes Feld                        ||");
		this.showshotInARow(shotsInARow);
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

	public void showShotInstruction() {
		ConsoleGUI.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");
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

	private void showEnemiesTurn() {
		ConsoleGUI.print("Dein Gegner ist am Zug!", "waiting");
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

}
