package model;

import java.io.Serializable;

public class Coordinate implements Serializable {

	/**
	 * Unique ID to identify version of the class for the Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	private int x; // NOPMD
	private int y; // NOPMD

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

	public Coordinate(int x, int y, boolean hasShip) { // NOPMD
		this.x = x;
		this.y = y;
		this.hasShip = hasShip;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) { // NOPMD
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) { // NOPMD
		this.y = y;
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

}
