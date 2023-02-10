package control.swing;

import java.util.List;

import model.Coordinate;
import model.Matchfield;

public class ControlShipRandomPositioning {

	/**
	 * Places ships on random positions (2 horizontal, 2 vertical)
	 * 
	 * @param matchfield - the matchfield where the ships set be set at
	 */
	protected static void placeShipsOnRandomPositions(Matchfield matchfield) {
		int totalShipsToPlace = 4;
		int randShipsPerDir = totalShipsToPlace / 2; // / 2 because of two directions (horizontal and vertical)

		ControlShipRandomPositioning.placeRandomShipsHorizontal(matchfield, randShipsPerDir);
		ControlShipRandomPositioning.placeRandomShipsVertical(matchfield, randShipsPerDir);
	}

	private static void placeRandomShipsVertical(Matchfield matchfield, int vertShipsAmount) {
		int count = 0;
		int fieldsize = matchfield.getFieldsize();
		List<Coordinate> coordinates = matchfield.getCoordinates();
		int shipNumberCounter = matchfield.getShipNumberCounter();

		while (count < vertShipsAmount) {
			int random = ControlShipRandomPositioning.getRandomIndexOfMatchfield(fieldsize);
			Coordinate firstCoordinate = null;
			Coordinate secondCoordinate = null;
			Coordinate thirdCoordinate = null;
			Coordinate fourthCoordinate = null;
			try {
				firstCoordinate = coordinates.get(random);
				secondCoordinate = coordinates.get(random + 10);
				thirdCoordinate = coordinates.get(random + 20);
				fourthCoordinate = coordinates.get(random + 30);
			} catch (IndexOutOfBoundsException e) {
				random = ControlShipRandomPositioning.getRandomIndexOfMatchfield(fieldsize);
				continue;
			}

			if (firstCoordinate != null && !firstCoordinate.isHasShip() && secondCoordinate != null
					&& !secondCoordinate.isHasShip() && thirdCoordinate != null && !thirdCoordinate.isHasShip()
					&& fourthCoordinate != null && !fourthCoordinate.isHasShip()) {
				firstCoordinate.setHasShip(true);
				secondCoordinate.setHasShip(true);
				thirdCoordinate.setHasShip(true);
				fourthCoordinate.setHasShip(true);

				firstCoordinate.setShipNumber(shipNumberCounter);
				secondCoordinate.setShipNumber(shipNumberCounter);
				thirdCoordinate.setShipNumber(shipNumberCounter);
				fourthCoordinate.setShipNumber(shipNumberCounter);

				matchfield.increaseShipNumberCounter();
				shipNumberCounter = matchfield.getShipNumberCounter();

				count++;

			} else {
				random = ControlShipRandomPositioning.getRandomIndexOfMatchfield(fieldsize);
			}
		}
	}

	private static void placeRandomShipsHorizontal(Matchfield matchfield, int horizShipsAmount) {
		int count = 0;
		int fieldsize = matchfield.getFieldsize();
		List<Coordinate> coordinates = matchfield.getCoordinates();
		int shipNumberCounter = matchfield.getShipNumberCounter();

		while (count < horizShipsAmount) {
			int random = ControlShipRandomPositioning.getRandomIndexOfMatchfield(fieldsize);
			if (random % fieldsize >= 0 && random % fieldsize != fieldsize - 3 && random % fieldsize != fieldsize - 2
					&& random % fieldsize != fieldsize - 1 && random + 4 <= 100 && !coordinates.get(random).isHasShip()
					&& !coordinates.get(random + 1).isHasShip() && !coordinates.get(random + 2).isHasShip()
					&& !coordinates.get(random + 3).isHasShip()) {

				Coordinate firstCoord = coordinates.get(random);
				Coordinate secondCoord = coordinates.get(random + 1);
				Coordinate thirdCoord = coordinates.get(random + 2);
				Coordinate fourthCoord = coordinates.get(random + 3);

				firstCoord.setHasShip(true);
				secondCoord.setHasShip(true);
				thirdCoord.setHasShip(true);
				fourthCoord.setHasShip(true);

				firstCoord.setShipNumber(shipNumberCounter);
				secondCoord.setShipNumber(shipNumberCounter);
				thirdCoord.setShipNumber(shipNumberCounter);
				fourthCoord.setShipNumber(shipNumberCounter);

				matchfield.increaseShipNumberCounter();
				shipNumberCounter = matchfield.getShipNumberCounter();

				count++;
			} else {
				random = ControlShipRandomPositioning.getRandomIndexOfMatchfield(fieldsize);
			}
		}
	}

	private static int getRandomIndexOfMatchfield(int fieldsize) {
		double random = Math.random() * 100;
		if (random > fieldsize * fieldsize) {
			random = Math.random() * 100;
		}
		return (int) random;
	}
}
