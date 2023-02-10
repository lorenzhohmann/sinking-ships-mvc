package model;

public class Game {
	
	private Bot enemy;
	private Player player;
	
	/**
	 * The amount of shots in a row before it's the other players turn
	 */
	private int shotsInARow;
	
	public Game() {
		this.enemy = new Bot();
		this.player = new Player();
		this.shotsInARow = 0;
	}
	
	public Bot getEnemy() {
		return enemy;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public void increaseShotsInARow() {
		this.shotsInARow++;
	}
	
	public void resetShotsInARow() {
		this.shotsInARow = 1;
	}
	
	public int getShotsInARow() {
		return shotsInARow;
	}

}
