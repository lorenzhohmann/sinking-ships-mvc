package view.console;

public interface ShipPositioningHandler {

	public void initControl();

	public void placeEnemiesShipsRandomly();

	public void placePlayersShipsRandomly();

	public void showPlayersMatchfield();

	public void resetPlayersShips();

	public boolean setShipPositionsManual(String positionString, int length);

	public void startGame();

	public int handlePositioningInput(String input);

}
