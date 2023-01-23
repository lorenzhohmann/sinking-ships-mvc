package view.swing;

public interface ShipPositioningHandler {

	void initControl();

	void placeEnemiesShipsRandomly();

	void placePlayersShipsRandomly();

	void showPlayersMatchfield();

	void resetPlayersShips();

	boolean setShipPositionsManual(String positionString, int length);

	void startGame();

	int handlePositioningInput(String input);

}
