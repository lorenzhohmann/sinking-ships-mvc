package model;

public enum FieldSymbol {

	WATER("~"), SHIP("o"), SHIP_HIT("x"), FULL_SHIP_HIT("X");

	String symbol;

	private FieldSymbol(String smybol) {
		this.symbol = smybol;
	}

	public String getSymbol() {
		return symbol;
	}

}
