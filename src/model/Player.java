package model;

public class Player {

	private final Matchfield matchfield;

	/**
	 * The amount of shots in a row before it's the other players turn
	 */
	private int shotsInARow;

	public Player() {
		this.matchfield = new Matchfield();
		this.shotsInARow = 1;
	}

	public Matchfield getMatchfield() {
		return matchfield;
	}

	public int getShotsInARow() {
		return shotsInARow;
	}

	public void resetShotsInARow() {
		this.shotsInARow = 1;
	}

	public void increaseShotsInARow() {
		this.shotsInARow++;
	}

}
