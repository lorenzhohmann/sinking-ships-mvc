package control.swing;

import java.io.IOException;
import java.util.List;

import model.AI;
import model.Coordinate;
import model.Matchfield;
import model.Player;
import view.swing.FrameGUI;
import view.swing.GameHandler;
import view.swing.Playground;
import view.swing.ShipPosReturnCode;
import view.swing.ShipPositioning;
import view.swing.ShipPositioningHandler;

public class ControlShipPositioning implements ShipPositioningHandler {

	private ShipPositioning shipPositioning;
	private Player human;
	private Player enemy;
	private FrameGUI gui;

	private ControlShipRandomPositioning randPosController;
	private ControlShipNormalPositioning posController;

	public ControlShipPositioning(AI enemy, FrameGUI gui) {
		this.enemy = enemy;
		this.human = new Player();
		this.gui = gui;

		this.randPosController = new ControlShipRandomPositioning();
		this.posController = new ControlShipNormalPositioning();
	}

	@Override
	public void initControl() {
		shipPositioning = new ShipPositioning(this, this.gui);

		this.shipPositioning.showShipPositioning(false);
	}

	@Override
	public void placeEnemiesShipsRandomly() {
		this.randPosController.placeShipsOnRandomPositions(this.enemy.getMatchfield());
	}

	@Override
	public void placePlayersShipsRandomly() {
		this.randPosController.placeShipsOnRandomPositions(this.human.getMatchfield());
	}

	@Override
	public void showPlayersMatchfield() {
		Playground matchfield = new Playground(this.gui);
		String[][] status = this.human.getMatchfield().getStatusArray(true);
		matchfield.print(status);
	}

	@Override
	public void resetPlayersShips() {
		this.resetShips(this.human.getMatchfield());
	}

	@Override
	public boolean positionShipManual(String positionString, int length) {
		return this.positionShipsByString(this.human.getMatchfield(), positionString, length);
	}

	@Override
	public void startGame() {
		GameHandler gameHandler = new ControlGame(human, enemy, this.gui);
		gameHandler.initControl();
		gameHandler.startGame();
	}

	@Override
	public ShipPosReturnCode handlePositioningInput(String input) {
		ShipPosReturnCode returnCode = ShipPosReturnCode.DEFAULT;

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

			returnCode = ShipPosReturnCode.CONTINUE;
		} else if ("m".equalsIgnoreCase(input)) {
			this.shipPositioning.showManualShipPositioning();
			returnCode = ShipPosReturnCode.BREAK;

		} else if ("s".equalsIgnoreCase(input)) { // start game
			this.startGame();
			returnCode = ShipPosReturnCode.BREAK;
		}

		return returnCode;
	}

	private void resetShips(Matchfield matchfield) {
		matchfield.resetShipNumberCounter();
		List<Coordinate> coordinates = matchfield.getCoordinates();

		for (Coordinate c : coordinates) {
			c.setHasShip(false);
		}
	}

	private boolean positionShipsByString(Matchfield matchfield, String manualPositioning, int maxShips) {
		String[] manualPositions = manualPositioning.split("-");
		boolean positionSuccesful = false;

		// clear matchfield
		this.resetShips(matchfield);

		// get coordinates by string and place ships
		int shipCounter = 1;
		for (String position : manualPositions) {

			if (shipCounter > maxShips) {
				break;
			}

			if (!position.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				positionSuccesful = false;
			}

			Coordinate choosenCoordinate = matchfield.getCoordinateByString(position);

			// horizontal ships for default
			boolean vertical = true;

			// if half of the ships were set vertical, set them horizontal from now on
			if (shipCounter >= maxShips / 2 + 1) {
				vertical = false;
			}

			// place ships by choosen coordinate
			boolean success = this.posController.placeShips(matchfield, choosenCoordinate.getX(),
					choosenCoordinate.getY(), vertical);

			// error handling
			if (!success) {
				positionSuccesful = false;
			}

			shipCounter++;
		}

		return positionSuccesful;
	}

}