package model;

public abstract class Player {

	/**
	 * Wheter the player is ready to start the game
	 */
	private boolean ready;

	/**
	 * 
	 */
	private Matchfield matchfield;

	public Player() {
		this.ready = false;
		this.matchfield = new Matchfield();
	}

	public boolean getReady() {
		return this.ready;
	}

	public void setReady(boolean ready) {
		this.ready = ready;
	}

	public Matchfield getMatchfield() {
		return matchfield;
	}

	public void setMatchfield(Matchfield matchfield) {
		this.matchfield = matchfield;
	}

}
