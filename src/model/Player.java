package model;

public abstract class Player {

	/**
	 * The full matchfield of a player with all its coordinates
	 */
	private Matchfield matchfield;

	public Player() {
		this.matchfield = new Matchfield();
	}

	public Matchfield getMatchfield() {
		return matchfield;
	}

}
