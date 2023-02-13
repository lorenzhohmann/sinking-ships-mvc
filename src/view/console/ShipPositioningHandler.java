package view.console;

public interface ShipPositioningHandler {

	void initControl();

	void placeEnemiesShipsRandomly();

	void placePlayersShipsRandomly();

	void showPlayersMatchfield();

	void resetPlayersShips();

	boolean positionShipManual(String positionString, int length);

	void startGame();

	/**
	 * Handles the users string of a multi-ship-positioning. (e.g. A1-F3-C6-H2)
	 * 
	 * @param input - the string of the ship positions (e.g. A1-F3-C6-H2)
	 * @return a ShipPosReturnCode to handle following user flow actions
	 */
	ShipPosReturnCode handlePositioningInput(String input);

}
