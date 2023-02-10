package view.console;

public class GameView {

	private final GameHandler gameHandler;

	public GameView(GameHandler gameHandler) {
		this.gameHandler = gameHandler;
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
		while (ConsoleGUI.SCANNER.hasNext()) {

			String input = ConsoleGUI.SCANNER.next();
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

	private void showEnemiesTurn() {
		ConsoleGUI.print("Dein Gegner ist am Zug!", "waiting");
	}

	public void showShotInstruction() {
		ConsoleGUI.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");
	}

}
