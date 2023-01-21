package view;

public interface ShipPositioningHandler {

	public void initControl();

	public void placeEnemiesShipsRandomly();

	public void placePlayersShipsRandomly();

	public void showPlayersMatchfield();

	public void resetPlayersShips();

	public boolean isNotWaitingStatus();

	public void setStatusWarmup();

	public boolean setShipPositionsManual(String positionString, int length);

	public void startGame();

}
