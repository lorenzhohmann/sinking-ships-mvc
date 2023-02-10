package view.console;

public interface GameHandler {

	void initControl();

	void startGame();

	void showPlayersMatchfield();

	void showEnemiesMatchfield();

	/**
	 * Performs a player move and handles it coordinate input string
	 * 
	 * @param coordinateString - the human-readable coordinate the player has chosen
	 *                         (e.g. B3)
	 * @return Whether the move was successful
	 */
	boolean doMove(String coordinateString);

}
