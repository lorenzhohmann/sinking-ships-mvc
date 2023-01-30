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

		this.createDefaultCoordinates();
	}

	public int getShipIndexCounter() {
		return this.shipIndexCounter;
	}

	public void setShipIndexCounter(int shipIndexCounter) {
		this.shipIndexCounter = shipIndexCounter;
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

	public Coordinate getLastShot() {
		return lastShot;
	}

	public void setLastShot(Coordinate lastShot) {
		this.lastShot = lastShot;
	}

	public void setLastChoose(Coordinate lastChoose) {
		this.lastChoose = lastChoose;
	}

	public Coordinate getLastHit() {
		return lastHit;
	}

	public void setLastHit(Coordinate lastHit) {
		this.lastHit = lastHit;
	}

	/**
	 * Creates a default coordinate for all positions of this matchfield
	 */
	private void createDefaultCoordinates() {
		for (int x = 0; x < this.fieldsize; x++) {
			for (int y = 0; y < this.fieldsize; y++) {
				Coordinate coordinate = new Coordinate(x, y, false);
				this.coordinates.add(coordinate);
			}
		}
	}

	public Statistic getStatisticsObject() {
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

	public void setLastShotHit(boolean lastShotHit) {
		this.lastShotHit = lastShotHit;
	}

	/**
	 * "Translates" a human-readable coordinate string into a coordinate object
	 * (e.g. A1 returns the first top left coordinate) from the matchfield
	 * 
	 * @param coordinateString - the human-readable string of the coordinate
	 *                         position
	 * @return the coordinate on the specific human-readable position from the
	 *         matchfield
	 */
	public Coordinate getCoordinateByString(String coordinateString) {
		String[] split = coordinateString.split("");

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

	/**
	 * Returns the coordinate on the matching x and y coordinate
	 * 
	 * @param x - the x value of the coordinate to get
	 * @param y - the y value of the coordinate to get
	 * @return the found coordinate object or null when x and y positions not in
	 *         matchfield
	 */
	public Coordinate getCoordinate(int x, int y) {
		for (int i = 0; i < this.coordinates.size(); i++) {
			if (this.coordinates.get(i).getX() == x && this.coordinates.get(i).getY() == y) {
				return this.coordinates.get(i);
			}
		}
		return null;
	}

	/**
	 * Returns all coordinates that is not hitted
	 * 
	 * @return ArrayList<Coordinate> with all coordinates that have no hit state.
	 *         Empty ArrayList when there is no coordinate without a hit
	 */
	public ArrayList<Coordinate> getCoordinatesWithoutHits() {
		ArrayList<Coordinate> coordinatesWithoutHits = new ArrayList<Coordinate>();
		for (Coordinate coordinate : this.coordinates) {
			if (!coordinate.hasHit()) {
				coordinatesWithoutHits.add(coordinate);
			}
		}

		return coordinatesWithoutHits;
	}

}