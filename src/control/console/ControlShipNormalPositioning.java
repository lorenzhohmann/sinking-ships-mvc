package control.console;

import java.util.List;

import model.Coordinate;
import model.Matchfield;

public class ControlShipNormalPositioning {

	protected boolean placeShips(Matchfield matchfield, int x, int y, boolean vertical) {

		boolean setSuccessful;

		if (vertical) {
			setSuccessful = this.placeShipVertical(matchfield, x, y);
		} else {
			setSuccessful = this.placeShipHorizontal(matchfield, x, y);
		}

		return setSuccessful;
	}

	/**
	 * Places a ship with length 4 vertically
	 * 
	 * @param matchfield - Where the ship should be set off
	 * @param y          - beginning y coord
	 * @param x          - beginning x coord
	 * @return whether the should could be set
	 */
	private boolean placeShipVertical(Matchfield matchfield, int y, int x) {
		int yMulFieldsize = y * matchfield.getFieldsize();
		int coordIndex = yMulFieldsize + x;
		boolean setSuccessful = false;

		List<Coordinate> coordinates = matchfield.getCoordinates();
		int shipNumberCounter = matchfield.getShipNumberCounter();

		Coordinate firstCoord = null;
		Coordinate secondCoord = null;
		Coordinate thirdCoord = null;
		Coordinate fourthCoord = null;
		try {
			firstCoord = coordinates.get(coordIndex);
			secondCoord = coordinates.get(coordIndex + 10);
			thirdCoord = coordinates.get(coordIndex + 20);
			fourthCoord = coordinates.get(coordIndex + 30);
		} catch (IndexOutOfBoundsException e) {
			setSuccessful = false;
		}

		if (firstCoord != null && !firstCoord.isHasShip() && secondCoord != null && !secondCoord.isHasShip()
				&& thirdCoord != null && !thirdCoord.isHasShip() && fourthCoord != null && !fourthCoord.isHasShip()) {
			firstCoord.setHasShip(true);
			secondCoord.setHasShip(true);
			thirdCoord.setHasShip(true);
			fourthCoord.setHasShip(true);

			firstCoord.setShipNumber(shipNumberCounter);
			secondCoord.setShipNumber(shipNumberCounter);
			thirdCoord.setShipNumber(shipNumberCounter);
			fourthCoord.setShipNumber(shipNumberCounter);

			matchfield.increaseShipNumberCounter();

			setSuccessful = true;
		}
		return setSuccessful;

	}

	/**
	 * Places a ship with length 4 horizontally
	 * 
	 * @param matchfield - Where the ship should be set off
	 * @param y          - beginning y coord
	 * @param x          - beginning x coord
	 * @return whether the should could be set
	 */
	private boolean placeShipHorizontal(Matchfield matchfield, int y, int x) {
		int yMulFieldsize = y * matchfield.getFieldsize();
		int coordIndex = yMulFieldsize + x;
		boolean setSuccessful;

		int fieldsize = matchfield.getFieldsize();
		List<Coordinate> coordinates = matchfield.getCoordinates();
		int shipNumberCounter = matchfield.getShipNumberCounter();

		if (coordIndex % fieldsize >= 0 && coordIndex % fieldsize != fieldsize - 3
				&& coordIndex % fieldsize != fieldsize - 2 && coordIndex % fieldsize != fieldsize - 1
				&& coordIndex + 4 <= 100 && !coordinates.get(coordIndex).isHasShip()
				&& !coordinates.get(coordIndex + 1).isHasShip() && !coordinates.get(coordIndex + 2).isHasShip()
				&& !coordinates.get(coordIndex + 3).isHasShip()) {

			Coordinate firstCoord = coordinates.get(coordIndex);
			Coordinate secondCoord = coordinates.get(coordIndex + 1);
			Coordinate thirdCoord = coordinates.get(coordIndex + 2);
			Coordinate fourthCoord = coordinates.get(coordIndex + 3);

			firstCoord.setHasShip(true);
			secondCoord.setHasShip(true);
			thirdCoord.setHasShip(true);
			fourthCoord.setHasShip(true);

			firstCoord.setShipNumber(shipNumberCounter);
			secondCoord.setShipNumber(shipNumberCounter);
			thirdCoord.setShipNumber(shipNumberCounter);
			fourthCoord.setShipNumber(shipNumberCounter);

			matchfield.increaseShipNumberCounter();

			setSuccessful = true;
		} else {
			setSuccessful = false;
		}
		return setSuccessful;
	}
}
