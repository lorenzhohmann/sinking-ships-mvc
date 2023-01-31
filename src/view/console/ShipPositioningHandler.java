package view.console;

public interface ShipPositioningHandler {

	void initControl();

	void placeEnemiesShipsRandomly();

	void placePlayersShipsRandomly();

	void showPlayersMatchfield();

	void resetPlayersShips();

	boolean positionShipManual(String positionString, int length);

	void startGame();

	ShipPosReturnCode handlePositioningInput(String input);

}
