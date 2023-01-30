package model;

public abstract class Player {

	private Matchfield matchfield;

	public Player() {
		this.matchfield = new Matchfield();
	}

	public Matchfield getMatchfield() {
		return matchfield;
	}

}
