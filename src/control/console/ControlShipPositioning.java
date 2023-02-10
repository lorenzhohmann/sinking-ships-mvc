package control.console;

import java.io.IOException;
import java.util.List;

import model.Bot;
import model.Coordinate;
import model.Matchfield;
import model.Player;
import view.console.GameHandler;
import view.console.Playground;
import view.console.ShipPosReturnCode;
import view.console.ShipPositioning;
import view.console.ShipPositioningHandler;

public class ControlShipPositioning implements ShipPositioningHandler {

	private ShipPositioning shipPositioning;
	private final Player human;
	private final Player enemy;

	public ControlShipPositioning(Bot enemy) {
		this.enemy = enemy;
		this.human = new Player();
	}

	@Override
	public void initControl() {
		shipPositioning = new ShipPositioning(this);

		this.shipPositioning.showShipPositioning(false);
	}

	@Override
	public void placeEnemiesShipsRandomly() {
		ControlShipRandomPositioning.placeShipsOnRandomPositions(this.enemy.getMatchfield());
	}

	@Override
	public void placePlayersShipsRandomly() {
		ControlShipRandomPositioning.placeShipsOnRandomPositions(this.human.getMatchfield());
	}

	@Override
	public void showPlayersMatchfield() {
		Playground matchfield = new Playground();
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
		GameHandler gameHandler = new ControlGame(human, enemy);
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

	/**
	 * Places ships by a position string (e.g. A1-F4-G1-H5)
	 * 
	 * @param matchfield        - the matchfield where the ships should be placed on
	 * @param manualPositioning - the positioning string (e.g. A1 or C7). Range from
	 *                          A-Z and 0-99 depends on the matchfield size.
	 * @param maxShips          - the amount of ships that should be placed
	 * @return whether all ships could be placed successfully
	 */
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
			boolean success = ControlShipNormalPositioning.placeShip(matchfield, choosenCoordinate.getX(),
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
