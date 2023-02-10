package model;

import java.io.Serializable;

public class Coordinate implements Serializable {

	/**
	 * Unique ID to identify version of the class for the Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	private final int x;
	private final int y;

	/**
	 * Whether the coordinate is shot (NOT depending of the ship state of the
	 * coordinate)
	 */
	private boolean hit;

	/**
	 * Whether the coordinate has a ship on its position (a ship consists of more
	 * than one coordinate)
	 */
	private boolean hasShip;

	/**
	 * When the hasShip is true, than shipNumber is > 0. Multiple coordinates have
	 * the same shipNumber to identify a whole ship
	 */
	private int shipNumber;

	public Coordinate(int x, int y, boolean hasShip) {
		this.x = x;
		this.y = y;
		this.hasShip = hasShip;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getShipNumber() {
		return shipNumber;
	}

	public void setShipNumber(int shipNumber) {
		this.shipNumber = shipNumber;
	}

	public boolean hasHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean isHasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}

	public static Coordinate createDefaultCoordinate(int x, int y) {
		return new Coordinate(x, y, false);
	}

	/**
	 * Does a shoot on a specific coordinates and evaluates the shot
	 * 
	 * @param matchfield - Matchfield on which the shot should be performed. Null
	 *                   throws Exception.
	 * @param coordinate - Coordinate that should be shot. Null throws Exception.
	 * @return whether the shot was a ship hit
	 */
	public boolean shoot(Matchfield field) {
		this.hit = true;
		field.setLastShot(this);

		// set hit to last successful hit
		if (this.hasShip) {
			field.setLastHit(this);
		}

		return field.didLastShotHit();
	}

	/**
	 * 
	 * @return whether the current coordinate is the first (of the matchfield)
	 */
	public boolean isFirstCoordinate() {
		return this.x == 0 && this.y == 0;
	}

	/**
	 * Checks if the ship on the passed coordinate is sunken. A ship is sunken if
	 * all coordinates of the ship were hit
	 * 
	 * @param aCoordOfAShip - coordinate of the ship that should be checked
	 * @return whether the ship is sunken
	 */
	public boolean isShipSunken(Matchfield field) {

		int shipHitsSameNr = 0;
		int shipsSameNr = 0;

		for (Coordinate coordinate : field.getCoordinates()) {
			if (coordinate.getShipNumber() == this.shipNumber) {
				shipsSameNr++;

				if (coordinate.hasHit()) {
					shipHitsSameNr++;
				}
			}
		}

		return shipsSameNr == shipHitsSameNr;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if (obj instanceof Coordinate) {
			Coordinate coord = (Coordinate) obj;
			if (this.x == coord.getX() && this.y == coord.getY()) {
				equals = true;
			}
		} else {
			equals = false;
		}
		return equals;
	}

	@Override
	public int hashCode() {
		int result = 42;

		result += x * 23;
		result += y * 23;
		result += shipNumber * 23;

		return result;
	}

}
