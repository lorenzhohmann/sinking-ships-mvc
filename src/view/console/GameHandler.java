package view.console;

import model.Player;

public interface GameHandler {

	void initControl(Player player, Player enemy);

	void startGame();

	void showPlayersMatchfield();

	void showEnemiesMatchfield();

	/**
	 * Performs a player move and handles it coordinate input string
	 * 
	 * @param coordinateString - the human-readable coordinate the player has chosen
	 *                         (e.g. B3)
	 * @return Whether the move was successfull
	 */
	boolean doMove(String coordinateString);

}
