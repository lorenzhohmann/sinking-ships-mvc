import java.io.Serializable;

public class Coordinate implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	private boolean hit;
	private boolean hasShip;
	private int shipIndex;
	
	public Coordinate(int x, int y, boolean hasShip) {
		this.x = x;
		this.y = y;
		this.hasShip = hasShip;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getShipIndex() {
		return shipIndex;
	}
	
	public void setShipIndex(int shipIndex) {
		this.shipIndex = shipIndex;
	}

	public boolean hasHit() {
		return hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public boolean hasShip() {
		return hasShip;
	}

	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}

}
