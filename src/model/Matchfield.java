package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Matchfield implements Serializable {

	/**
	 * Unique ID to identify version of the class for the Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The size of the matchfield in one direction (horizontal or vertical)
	 */
	private int fieldsize;

	/**
	 * Contains all coordinate objects of the matchfield
	 */
	private List<Coordinate> coordinates;

	/**
	 * The coordinate that was fires at last
	 */
	private Coordinate lastShot;

	/**
	 * The coordinate that was last fires AND hitted a ship
	 */
	private Coordinate lastHit;

	/**
	 * An index counter of how man ships already placed on the matchfield
	 */
	private int shipNumberCounter;

	public Matchfield() {
		this.fieldsize = 10;
		this.coordinates = new ArrayList<>();
		this.shipNumberCounter = 1;

		this.createDefaultCoordinates();
	}

	public int getShipNumberCounter() {
		return this.shipNumberCounter;
	}

	public int getFieldsize() {
		return fieldsize;
	}

	public List<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	 * Creates a 2D list with different symbols for the different states of the
	 * ship. Needed to visualize a matchfield
	 * 
	 * @param showShips - Whether the ship positions should be visible
	 * @return - A 2D array with field symbols for the individual coordinates
	 */
	public String[][] getStatusArray(boolean showShips) {
		String[][] status = new String[this.fieldsize][this.fieldsize]; // Test für Feld mit der Größe 3x
		for (int i = 0; i < status.length; i++) {
			for (int j = 0; j < status.length; j++) {
				status[i][j] = " ";
			}
		}
		// Game Logic for Output of certain Characters
		if (showShips) {
			for (Coordinate c : this.coordinates) {
				if (c.isHasShip()) {
					if (c.hasHit()) {
						if (c.isShipSunken(this)) {
							status[c.getX()][c.getY()] = FieldSymbol.FULL_SHIP_HIT.getSymbol();
						} else {
							status[c.getX()][c.getY()] = FieldSymbol.SHIP_HIT.getSymbol();
						}
					} else {
						status[c.getX()][c.getY()] = FieldSymbol.SHIP.getSymbol();
					}
				} else {
					if (c.hasHit()) {
						status[c.getX()][c.getY()] = FieldSymbol.WATER.getSymbol();
					}
				}
			}
		} else {
			for (Coordinate c : coordinates) {
				if (c.hasHit()) {
					if (c.isHasShip()) {
						if (c.isShipSunken(this)) {
							status[c.getX()][c.getY()] = FieldSymbol.FULL_SHIP_HIT.getSymbol();
						} else {
							status[c.getX()][c.getY()] = FieldSymbol.SHIP_HIT.getSymbol();
						}
					} else {
						status[c.getX()][c.getY()] = FieldSymbol.WATER.getSymbol();
					}
				}
			}
		}

		return status;
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
				Coordinate defaultCoord = Coordinate.createDefaultCoordinate(x, y);
				this.coordinates.add(defaultCoord);
			}
		}
	}

	/**
	 * Builds an object of the Statistic with data from the matchfield
	 * 
	 * @return the Statistic object the data of the matchfield
	 */
	public Statistic getStatisticsObject() {
		int hits = 0;
		int totalShots = 0;

		for (Coordinate c : this.coordinates) {
			if (c.hasHit() && c.isHasShip()) {
				totalShots++;
				hits++;
			} else if (c.hasHit()) {
				totalShots++;
			}

		}
		return new Statistic(totalShots, hits);
	}

	public Coordinate getLastShot() {
		return lastShot;
	}

	public void setLastShot(Coordinate lastShot) {
		this.lastShot = lastShot;
	}

	/**
	 * "Translates" a human-readable coordinate string into a coordinate object
	 * (e.g. A1 returns the first top left coordinate) from the matchfield
	 * 
	 * @param coordinateString - the human-readable string of the coordinate
	 * @return the coordinate on the specific human-readable position
	 */
	public Coordinate getCoordinateByString(String coordinateString) {
		String[] split = coordinateString.split("");

		int alphabetIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".indexOf(split[0].toUpperCase(Locale.GERMAN));

		int stringUnderTen = 2;
		int stringOverTen = 3;
		int numberIndex = 0;

		if (split.length == stringUnderTen) {
			numberIndex = 10 * (Integer.parseInt(split[1]) - 1);
		} else {
			if (split.length == stringOverTen) {
				numberIndex = 10 * (Integer.parseInt(split[1] + split[stringUnderTen]) - 1);
			}
		}
		int chosenCoordIndex = numberIndex + alphabetIndex;

		this.lastShot = this.coordinates.get(chosenCoordIndex);
		return this.lastShot;
	}

	/**
	 * Returns the coordinate on the matching x and y coordinate
	 * 
	 * @param x - the x value of the coordinate to get
	 * @param y - the y value of the coordinate to get
	 * @return the found coordinate object or null when x and y positions not in
	 *         matchfield
	 */
	public Coordinate getCoordinate(int x, int y) { // NOPMD
		Coordinate coord = null;
		for (int i = 0; i < this.coordinates.size(); i++) {
			if (this.coordinates.get(i).getX() == x && this.coordinates.get(i).getY() == y) {
				coord = this.coordinates.get(i);
				break;
			}
		}
		return coord;
	}

	/**
	 * Returns all coordinates that is not hitted
	 * 
	 * @return ArrayList<Coordinate> with all coordinates that have no hit state.
	 *         Empty ArrayList when there is no coordinate without a hit
	 */
	public List<Coordinate> getCoordinatesWithoutHits() {
		List<Coordinate> coordsWithoutHit = new ArrayList<>();
		for (Coordinate coordinate : this.coordinates) {
			if (!coordinate.hasHit()) {
				coordsWithoutHit.add(coordinate);
			}
		}

		return coordsWithoutHit;
	}

	/**
	 * Checks if the last shot in the matchfield has hitted a ship
	 * 
	 * @return whether the last shot made a ship hit
	 */
	public boolean didLastShotHit() {
		return this.lastShot.equals(this.lastHit);
	}

	public void increaseShipNumberCounter() {
		this.shipNumberCounter++;
	}

	/**
	 * Sets the ship number counter to default constructor value (1)
	 */
	public void resetShipNumberCounter() {
		this.shipNumberCounter = 1;
	}

}