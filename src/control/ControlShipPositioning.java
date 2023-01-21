package control;

import model.KI;
import model.Player;
import model.Status;
import view.GameHandler;
import view.Matchfield;
import view.ShipPositioning;
import view.ShipPositioningHandler;

public class ControlShipPositioning implements ShipPositioningHandler {

	private ShipPositioning shipPositioning;
	private Player player;
	private KI enemy;
	private Status status;

	public ControlShipPositioning(KI enemy) {
		this.enemy = enemy;
		this.player = new Player();

		this.status = Status.WAITING;
	}

	@Override
	public void initControl() {
		shipPositioning = new ShipPositioning(this);

		this.showShipPositioningMenu(enemy);
	}

	private void showShipPositioningMenu(KI enemy) {
		this.shipPositioning.showShipPositioning(false);
	}

	@Override
	public void placeEnemiesShipsRandomly() {
		this.enemy.getMatchfield().placeRandomShips();
	}

	@Override
	public void placePlayersShipsRandomly() {
		this.player.getMatchfield().placeRandomShips();
	}

	@Override
	public void showPlayersMatchfield() {
		Matchfield matchfield = new Matchfield(player.getMatchfield().getFieldsize());
		String[][] status = this.player.getMatchfield().getStatus(true);
		matchfield.show(status);
	}

	@Override
	public void resetPlayersShips() {
		player.getMatchfield().resetShips();
	}

	@Override
	public boolean isNotWaitingStatus() {
		return status != Status.WAITING;
	}

	@Override
	public void setStatusWarmup() {
		status = Status.WARMUP;
	}

	@Override
	public boolean setShipPositionsManual(String positionString, int length) {
		return player.getMatchfield().positionShipsByString(positionString, length);
	}

	@Override
	public void startGame() {
		GameHandler gameHandler = new ControlGame();
		gameHandler.initControl(player, enemy, status);
		gameHandler.startGame();
	}

}
