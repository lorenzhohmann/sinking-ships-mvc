package model;

public enum Difficulty {
	EASY("Einfach"), HARD("Schwer"), EXTREM("Extrem");

	String name;

	Difficulty(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}
