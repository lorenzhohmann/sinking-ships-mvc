package control.swing;

import java.io.IOException;

import model.AI;
import model.Human;
import model.Player;
import view.swing.FrameGUI;
import view.swing.GameHandler;
import view.swing.Playground;
import view.swing.ShipPositioning;
import view.swing.ShipPositioningHandler;

public class ControlShipPositioning implements ShipPositioningHandler {

	private ShipPositioning shipPositioning;
	private FrameGUI gui;
	private Player human;
	private Player enemy;

	public ControlShipPositioning(AI enemy, FrameGUI gui) {
		this.gui = gui;
		this.enemy = enemy;
		this.human = new Human();
	}

	@Override
	public void initControl() {
		shipPositioning = new ShipPositioning(this);

		this.showShipPositioningMenu();
	}

	private void showShipPositioningMenu() {
		this.shipPositioning.showShipPositioning(false);
	}

	@Override
	public void placeEnemiesShipsRandomly() {
		this.enemy.getMatchfield().placeRandomShips();
	}

	@Override
	public void placePlayersShipsRandomly() {
		this.human.getMatchfield().placeRandomShips();
	}

	@Override
	public void showPlayersMatchfield() {
		Playground matchfield = new Playground();
		String[][] status = this.human.getMatchfield().getStatus(true);
		matchfield.print(status);
	}

	@Override
	public void resetPlayersShips() {
		human.getMatchfield().resetShips();
	}

	@Override
	public boolean setShipPositionsManual(String positionString, int length) {
		return human.getMatchfield().positionShipsByString(positionString, length);
	}

	@Override
	public void startGame() {
		GameHandler gameHandler = new ControlGame();
		gameHandler.initControl(human, enemy);
		gameHandler.startGame();
	}

	@Override
	public int handlePositioningInput(String input) {
		int returnCode = 0;

		if ("z".equalsIgnoreCase(input)) { // generate new random ship positions

			// clear console
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {
			}
			this.shipPositioning.showShipsNewSet();

			this.resetPlayersShips();
			this.placePlayersShipsRandomly();
			this.showPlayersMatchfield();

			this.shipPositioning.showShipPositioningMenu();

			returnCode = 1;
		} else if ("m".equalsIgnoreCase(input)) {
			this.shipPositioning.showManualShipPositioning();
			returnCode = 2;

		} else if ("s".equalsIgnoreCase(input)) { // start game
			this.startGame();
			returnCode = 2;
		}

		this.shipPositioning.showInvalidInput();
		this.shipPositioning.showShipPositioning(true);

		return returnCode;
	}

}
