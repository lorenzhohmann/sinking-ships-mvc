package model;

public class Bot extends Player {

	private Difficulty difficulty;

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}
}