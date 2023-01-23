package view.swing;

public class GameView {

	/**
	 * Difficulty level of the humans enemy (doesn't change while playing)
	 */
	private String enemyDifficulty;

	/**
	 * The game controller of this view
	 */
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

	}

	/**
	 * Shows the players round information and reads the users input from the
	 * console
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showPlayerRound(int shotInARow) {

	}

	/**
	 * Shows the enemies round information
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesRound(int shotInARow) {

	}

	/**
	 * Shows the enemies matchfield
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesMatchfield(int shotInARow) {

	}

	/**
	 * Prints global game information
	 */
	public void showHead() {

	}

	/**
	 * Shows the information with a label
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	private void showshotInARow(int shotInARow) {

	}

	/**
	 * Shows the headline for enemies round
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	private void showEnemyHeadline(int shotInARow) {

	}

	/**
	 * Shows the headline for players round
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	private void showPlayersHeadline(int shotInARow) {

	}

	/**
	 * Shows an error message
	 */
	public void printFieldAlreadyShot() {

	}

	/**
	 * Shows a message wheter a full ship was hit
	 * 
	 * @param fullShipDown - if the full ship was hit. Conditions the output
	 */
	public void showShotResultMessage(boolean fullShipDown) {

	}

	/**
	 * Shows a instruction for the player how to play
	 */
	public void showShotInstruction() {

	}

	/**
	 * Shows a message to wait
	 */
	public void showWaitingForSecondPlayer() {

	}

	/**
	 * Shows a message that a second player has connected
	 */
	public void showSecondPlayerConnected() {

	}

	/**
	 * Shows a message that no ship was hit and it's the enemies turn
	 */
	public void showNoShipHit() {

	}

	/**
	 * Shows an error message becuase of wrong input data
	 */
	public void showInvalidInput() {

	}

	/**
	 * Shows a message that it's now enemies turn
	 */
	private void showEnemiesTurn() {
		FrameGUI.print("Dein Gegner ist am Zug!", "waiting");
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

}
