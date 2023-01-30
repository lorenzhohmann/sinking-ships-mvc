package control.console;

import java.io.IOException;
import java.util.ArrayList;

import model.AI;
import model.Coordinate;
import model.Human;
import model.Matchfield;
import model.Player;
import view.console.GameHandler;
import view.console.Playground;
import view.console.ShipPositioning;
import view.console.ShipPositioningHandler;

public class ControlShipPositioning implements ShipPositioningHandler {

	private ShipPositioning shipPositioning;
	private Player human;
	private Player enemy;

	public ControlShipPositioning(AI enemy) {
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
		this.placeShipsOnRandomPositions(this.enemy.getMatchfield());
//		this.enemy.getMatchfield().placeRandomShips();
	}

	@Override
	public void placePlayersShipsRandomly() {
		this.placeShipsOnRandomPositions(this.human.getMatchfield());
//		this.human.getMatchfield().placeRandomShips();
	}

	@Override
	public void showPlayersMatchfield() {
		Playground matchfield = new Playground();
		String[][] status = this.human.getMatchfield().getStatus(true);
		matchfield.print(status);
	}

	@Override
	public void resetPlayersShips() {
		this.resetShips(this.human.getMatchfield());
	}

	@Override
	public boolean setShipPositionsManual(String positionString, int length) {
		return this.positionShipsByString(this.human.getMatchfield(), positionString, length);
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

		return returnCode;
	}

	private void resetShips(Matchfield matchfield) {
		matchfield.setShipIndexCounter(1);
		ArrayList<Coordinate> coordinates = matchfield.getCoordinates();

		for (Coordinate c : coordinates) {
			c.setHasShip(false);
		}
	}

	private void placeShipsOnRandomPositions(Matchfield matchfield) {
		int totalShipsToPlace = 4;
		int randomShipsPerDirection = totalShipsToPlace / 2; // / 2 because of two directions (horizontal and vertical)

		this.placeRandomShipsHorizontal(matchfield, randomShipsPerDirection);
		this.placeRandomShipsVertical(matchfield, randomShipsPerDirection);
	}

	private void placeRandomShipsVertical(Matchfield matchfield, int verticalShipsAmount) {
		int count = 0;
		int fieldsize = matchfield.getFieldsize();
		ArrayList<Coordinate> coordinates = matchfield.getCoordinates();
		int shipIndexCounter = matchfield.getShipIndexCounter();

		while (count < verticalShipsAmount) {
			int random = this.getRandomIndexOfMatchfield(fieldsize);
			Coordinate c1 = null;
			Coordinate c2 = null;
			Coordinate c3 = null;
			Coordinate c4 = null;
			try {
				c1 = coordinates.get(random);
				c2 = coordinates.get(random + 10);
				c3 = coordinates.get(random + 20);
				c4 = coordinates.get(random + 30);
			} catch (IndexOutOfBoundsException e) {
				random = this.getRandomIndexOfMatchfield(fieldsize);
				continue;
			}

			if (c1 != null && !c1.hasShip() && c2 != null && !c2.hasShip() && c3 != null && !c3.hasShip() && c4 != null
					&& !c4.hasShip()) {
				c1.setHasShip(true);
				c2.setHasShip(true);
				c3.setHasShip(true);
				c4.setHasShip(true);

				c1.setShipIndex(shipIndexCounter);
				c2.setShipIndex(shipIndexCounter);
				c3.setShipIndex(shipIndexCounter);
				c4.setShipIndex(shipIndexCounter);
				shipIndexCounter++;

				count++;

			} else {
				random = this.getRandomIndexOfMatchfield(fieldsize);
			}
		}
	}

	private void placeRandomShipsHorizontal(Matchfield matchfield, int horizontalShipsAmount) {
		int count = 0;
		int fieldsize = matchfield.getFieldsize();
		ArrayList<Coordinate> coordinates = matchfield.getCoordinates();
		int shipIndexCounter = matchfield.getShipIndexCounter();

		while (count < horizontalShipsAmount) {
			int random = this.getRandomIndexOfMatchfield(fieldsize);
			if (random % fieldsize >= 0 && random % fieldsize != fieldsize - 3 && random % fieldsize != fieldsize - 2
					&& random % fieldsize != fieldsize - 1 && random + 4 <= 100
					&& coordinates.get(random).hasShip() == false && coordinates.get(random + 1).hasShip() == false
					&& coordinates.get(random + 2).hasShip() == false
					&& coordinates.get(random + 3).hasShip() == false) {

				Coordinate c1 = coordinates.get(random);
				Coordinate c2 = coordinates.get(random + 1);
				Coordinate c3 = coordinates.get(random + 2);
				Coordinate c4 = coordinates.get(random + 3);

				c1.setHasShip(true);
				c2.setHasShip(true);
				c3.setHasShip(true);
				c4.setHasShip(true);

				c1.setShipIndex(shipIndexCounter);
				c2.setShipIndex(shipIndexCounter);
				c3.setShipIndex(shipIndexCounter);
				c4.setShipIndex(shipIndexCounter);
				matchfield.setShipIndexCounter(matchfield.getShipIndexCounter() + 1);

				count++;
			} else {
				random = this.getRandomIndexOfMatchfield(fieldsize);
			}
		}
	}

	private int getRandomIndexOfMatchfield(int fieldsize) {
		double random = 0;
		random = Math.random() * 100;
		if (random > fieldsize * fieldsize) {
			random = Math.random() * 100;
		}
		return (int) random;
	}

	private boolean positionShipsByString(Matchfield matchfield, String manualPositioning, int maxShips) {
		String[] manualPositions = manualPositioning.split("-");

		// clear matchfield
		this.resetShips(matchfield);

		// get coordinates by string and place ships
		int shipCounter = 1;
		for (String position : manualPositions) {

			if (shipCounter > maxShips) {
				break;
			}

			if (!position.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				return false;
			}

			Coordinate choosenCoordinate = matchfield.getCoordinateByString(position);

			// horizontal ships for default
			boolean vertical = true;

			// vertical ships
			if (shipCounter >= 3)
				vertical = false;

			// place ships by choosen coordinate
			boolean success = this.placeShips(matchfield, choosenCoordinate.getX(), choosenCoordinate.getY(), vertical);

			// error handling
			if (!success)
				return false;

			shipCounter++;
		}

		return true;
	}

	private boolean placeShips(Matchfield matchfield, int x, int y, boolean vertical) {
		boolean set = false;

		if (vertical) {
			set = this.placeShipsVertical(matchfield, x, y);
		} else {
			set = this.placeShipsHorizontal(matchfield, x, y);
		}

		return set;
	}

	private boolean placeShipsVertical(Matchfield matchfield, int y, int x) {
		int yMulFieldsize = y * matchfield.getFieldsize();
		int z = yMulFieldsize + x;
		ArrayList<Coordinate> coordinates = matchfield.getCoordinates();
		int shipIndexCounter = matchfield.getShipIndexCounter();

		Coordinate c1 = null;
		Coordinate c2 = null;
		Coordinate c3 = null;
		Coordinate c4 = null;
		try {
			c1 = coordinates.get(z);
			c2 = coordinates.get(z + 10);
			c3 = coordinates.get(z + 20);
			c4 = coordinates.get(z + 30);
		} catch (IndexOutOfBoundsException e) {
			return false;
		}

		if (c1 != null && !c1.hasShip() && c2 != null && !c2.hasShip() && c3 != null && !c3.hasShip() && c4 != null
				&& !c4.hasShip()) {
			c1.setHasShip(true);
			c2.setHasShip(true);
			c3.setHasShip(true);
			c4.setHasShip(true);

			c1.setShipIndex(shipIndexCounter);
			c2.setShipIndex(shipIndexCounter);
			c3.setShipIndex(shipIndexCounter);
			c4.setShipIndex(shipIndexCounter);
			matchfield.setShipIndexCounter(matchfield.getShipIndexCounter() + 1);
			return true;
		}
		return false;
	}

	private boolean placeShipsHorizontal(Matchfield matchfield, int y, int x) {
		int yMulTen = y * 10;
		boolean set = false;
		int fieldsize = matchfield.getFieldsize();
		ArrayList<Coordinate> coordinates = matchfield.getCoordinates();
		int shipIndexCounter = matchfield.getShipIndexCounter();

		int z = yMulTen + x;
		if (z % fieldsize >= 0 && z % fieldsize != fieldsize - 3 && z % fieldsize != fieldsize - 2
				&& z % fieldsize != fieldsize - 1 && z + 4 <= 100 && coordinates.get(z).hasShip() == false
				&& coordinates.get(z + 1).hasShip() == false && coordinates.get(z + 2).hasShip() == false
				&& coordinates.get(z + 3).hasShip() == false) {

			Coordinate c1 = coordinates.get(z);
			Coordinate c2 = coordinates.get(z + 1);
			Coordinate c3 = coordinates.get(z + 2);
			Coordinate c4 = coordinates.get(z + 3);

			c1.setHasShip(true);
			c2.setHasShip(true);
			c3.setHasShip(true);
			c4.setHasShip(true);

			c1.setShipIndex(shipIndexCounter);
			c2.setShipIndex(shipIndexCounter);
			c3.setShipIndex(shipIndexCounter);
			c4.setShipIndex(shipIndexCounter);
			matchfield.setShipIndexCounter(matchfield.getShipIndexCounter() + 1);

			set = true;
		} else {
			set = false;
		}
		return set;
	}

}
