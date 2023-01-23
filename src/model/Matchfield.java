package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Matchfield implements Serializable {

	private static final long serialVersionUID = 1L;
	private int fieldsize;
	private ArrayList<Coordinate> coordinates;
	private Coordinate lastShot;
	private Coordinate lastHit;
	private Coordinate lastChoose;
	private boolean lastShotHit;
	private int shipIndexCounter;

	public Matchfield() {
		this.fieldsize = 10;
		this.coordinates = new ArrayList<Coordinate>();
		this.lastShot = null;
		this.lastHit = null;
		this.lastChoose = null;
		this.lastShotHit = false;
		this.shipIndexCounter = 1;

		this.fillCoordinates();
	}

	public int getShipIndexCounter() {
		return this.shipIndexCounter;
	}

	public int getFieldsize() {
		return fieldsize;
	}

	public void setFieldsize(int fieldsize) {
		this.fieldsize = fieldsize;
	}

	public ArrayList<Coordinate> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}

	public String[][] getStatus(boolean showShips) {
		String[][] status = new String[this.fieldsize][this.fieldsize]; // Test für Feld mit der Größe 3x
		for (int i = 0; i < status.length; i++) {
			for (int j = 0; j < status.length; j++) {
				status[i][j] = " ";
			}
		}
		// Game Logic for Output of certain Characters
		if (showShips) {
			for (Coordinate c : coordinates) {
				if (c.hasShip()) {
					if (c.hasHit()) {
						if (this.isShipDown(c)) {
							status[c.getX()][c.getY()] = "X";
						} else {
							status[c.getX()][c.getY()] = "x";
						}
					} else {
						status[c.getX()][c.getY()] = "o";
					}
				} else {
					if (c.hasHit()) {
						status[c.getX()][c.getY()] = "~";
					}
				}
			}
		} else {
			for (Coordinate c : coordinates) {
				if (c.hasHit()) {
					if (c.hasShip()) {
						if (this.isShipDown(c)) {
							status[c.getX()][c.getY()] = "X";
						} else {
							status[c.getX()][c.getY()] = "x";
						}
					} else {
						status[c.getX()][c.getY()] = "~";
					}
				}
			}
		}

		return status;
	}

	public int randomize() {
		double random = 0;
		random = Math.random() * 100;
		if (random > this.fieldsize * this.fieldsize) {
			random = Math.random() * 100;
		}
		return (int) random;
	}

	public void resetShips() {
		this.shipIndexCounter = 1;

		for (Coordinate c : this.coordinates) {
			c.setHasShip(false);
		}
	}

	public void placeRandomShipsVertical() {
		int count = 0;
		while (count < 2) {
			int random = randomize();
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
				random = randomize();
				continue;
			}

			if (c1 != null && !c1.hasShip() && c2 != null && !c2.hasShip() && c3 != null && !c3.hasShip() && c4 != null
					&& !c4.hasShip()) {
				c1.setHasShip(true);
				c2.setHasShip(true);
				c3.setHasShip(true);
				c4.setHasShip(true);

				c1.setShipIndex(this.shipIndexCounter);
				c2.setShipIndex(this.shipIndexCounter);
				c3.setShipIndex(this.shipIndexCounter);
				c4.setShipIndex(this.shipIndexCounter);
				this.shipIndexCounter++;

				count++;

			} else {
				random = randomize();
			}
		}
	}

	public void placeRandomShipsHorizontal() {
		int count = 0;
		while (count < 2) {
			int random = randomize();
			if (random % this.fieldsize >= 0 && random % this.fieldsize != this.fieldsize - 3
					&& random % this.fieldsize != this.fieldsize - 2 && random % this.fieldsize != this.fieldsize - 1
					&& random + 4 <= 100 && coordinates.get(random).hasShip() == false
					&& coordinates.get(random + 1).hasShip() == false && coordinates.get(random + 2).hasShip() == false
					&& coordinates.get(random + 3).hasShip() == false) {

				Coordinate c1 = coordinates.get(random);
				Coordinate c2 = coordinates.get(random + 1);
				Coordinate c3 = coordinates.get(random + 2);
				Coordinate c4 = coordinates.get(random + 3);

				c1.setHasShip(true);
				c2.setHasShip(true);
				c3.setHasShip(true);
				c4.setHasShip(true);

				c1.setShipIndex(this.shipIndexCounter);
				c2.setShipIndex(this.shipIndexCounter);
				c3.setShipIndex(this.shipIndexCounter);
				c4.setShipIndex(this.shipIndexCounter);
				this.shipIndexCounter++;

				count++;
			} else {
				random = randomize();
			}
		}

	}

	public void placeRandomShips() {
		placeRandomShipsHorizontal();
		placeRandomShipsVertical();
	}

	public boolean placeShipsVertical(int y, int x) {
		int yMulFieldsize = y * this.fieldsize;
		int z = yMulFieldsize + x;

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

			c1.setShipIndex(this.shipIndexCounter);
			c2.setShipIndex(this.shipIndexCounter);
			c3.setShipIndex(this.shipIndexCounter);
			c4.setShipIndex(this.shipIndexCounter);
			this.shipIndexCounter++;
			return true;
		}
		return false;
	}

	public boolean placeShipsHorizontal(int y, int x) {
		int yMulTen = y * 10;
		boolean set = false;

		int z = yMulTen + x;
		if (z % this.fieldsize >= 0 && z % this.fieldsize != this.fieldsize - 3
				&& z % this.fieldsize != this.fieldsize - 2 && z % this.fieldsize != this.fieldsize - 1 && z + 4 <= 100
				&& coordinates.get(z).hasShip() == false && coordinates.get(z + 1).hasShip() == false
				&& coordinates.get(z + 2).hasShip() == false && coordinates.get(z + 3).hasShip() == false) {

			Coordinate c1 = coordinates.get(z);
			Coordinate c2 = coordinates.get(z + 1);
			Coordinate c3 = coordinates.get(z + 2);
			Coordinate c4 = coordinates.get(z + 3);

			c1.setHasShip(true);
			c2.setHasShip(true);
			c3.setHasShip(true);
			c4.setHasShip(true);

			c1.setShipIndex(this.shipIndexCounter);
			c2.setShipIndex(this.shipIndexCounter);
			c3.setShipIndex(this.shipIndexCounter);
			c4.setShipIndex(this.shipIndexCounter);
			this.shipIndexCounter++;

			set = true;
		} else {
			set = false;
		}
		return set;
	}

	public boolean placeShips(int x, int y, boolean vertical) {
		boolean set = false;

		if (vertical) {
			set = placeShipsVertical(x, y);
		} else {
			set = placeShipsHorizontal(x, y);
		}

		return set;
	}

	public boolean isGameOver() {
		int shipPositionCounter = 0;
		int totalHittedShipsCounter = 0;

		for (Coordinate coordinate : this.coordinates) {
			if (coordinate.hasShip()) {
				shipPositionCounter++;

				if (coordinate.hasHit()) {
					totalHittedShipsCounter++;
				}
			}
		}
		return shipPositionCounter == totalHittedShipsCounter;
	}

	public boolean shoot(Coordinate coordinate) {
		coordinate.setHit(true);
		this.lastShot = coordinate;

		// set hit to last successfull hit
		if (coordinate.hasShip())
			this.lastHit = coordinate;

		this.lastShotHit = coordinate.hasShip();
		return this.lastShotHit;
	}

	public Coordinate getCoordinateByString(String coordinate) {
		String[] split = coordinate.split("");

		int alphabetIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(split[0].toUpperCase());

		int numberIndex = 0;
		if (split.length == 2) {
			numberIndex = 10 * (Integer.parseInt(split[1]) - 1);
		} else if (split.length == 3) {
			numberIndex = 10 * (Integer.parseInt(split[1] + split[2]) - 1);
		}
		int chosenCoordinateIndex = numberIndex + alphabetIndex;

		this.lastChoose = this.coordinates.get(chosenCoordinateIndex);
		return this.lastChoose;
	}

	public boolean positionShipsByString(String manualPositioning, int maxShips) {
		String[] manualPositions = manualPositioning.split("-");

		// clear matchfield
		this.resetShips();

		// get coordinates by string and place ships
		int shipCounter = 1;
		for (String position : manualPositions) {

			if (shipCounter > maxShips) {
				break;
			}

			if (!position.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				return false;
			}

			Coordinate choosenCoordinate = this.getCoordinateByString(position);

			// horizontal ships for default
			boolean vertical = true;

			// vertical ships
			if (shipCounter >= 3)
				vertical = false;

			// place ships by choosen coordinate
			boolean success = this.placeShips(choosenCoordinate.getX(), choosenCoordinate.getY(), vertical);

			// error handling
			if (!success)
				return false;

			shipCounter++;
		}

		return true;
	}

	public Coordinate getLastShot() {
		return lastShot;
	}

	public Coordinate getLastHit() {
		return lastHit;
	}

	public void setLastHit(Coordinate lastHit) {
		this.lastHit = lastHit;
	}

	private void fillCoordinates() {
		for (int x = 0; x < this.fieldsize; x++) {
			for (int y = 0; y < this.fieldsize; y++) {
				Coordinate coordinate = new Coordinate(x, y, false);
				this.coordinates.add(coordinate);
			}
		}
	}

	public Coordinate getCoordinateByXAndY(int x, int y) {
		for (Coordinate coordinate : this.coordinates) {
			if (coordinate.getX() == x && coordinate.getY() == y) {
				return coordinate;
			}
		}

		return null;
	}

	public boolean isShipDown(Coordinate oneCoordinateOfShip) {

		int shipIndex = oneCoordinateOfShip.getShipIndex();
		int shipHitsWithSameIndex = 0;
		int shipsWithSameIndex = 0;

		for (Coordinate coordinate : this.coordinates) {
			if (coordinate.getShipIndex() == shipIndex) {
				shipsWithSameIndex++;

				if (coordinate.hasHit())
					shipHitsWithSameIndex++;
			}
		}

		return shipsWithSameIndex == shipHitsWithSameIndex;
	}

	public Statistic getStatistic() {
		int hits = 0;
		int totalShots = 0;

		for (Coordinate c : this.coordinates) {
			if (c.hasHit() && c.hasShip()) {
				totalShots++;
				hits++;
			} else if (c.hasHit()) {
				totalShots++;
			}

		}
		return new Statistic(totalShots, hits);
	}

	public Coordinate getLastChoose() {
		return lastChoose;
	}

	public boolean isLastShotHit() {
		return lastShotHit;
	}

}