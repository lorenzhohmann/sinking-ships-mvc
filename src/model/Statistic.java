package model;

public class Statistic {

	private int totalShots;
	private int hits;

	public Statistic(int totalShots, int hits) {
		this.totalShots = totalShots;
		this.hits = hits;
	}

	public int getHits() {
		return hits;
	}

	public int getTotalShots() {
		return totalShots;
	}

	public int getNoHits() {
		return this.totalShots - hits;
	}

	public float getSuccessRate() {
		if (totalShots > 0) {
			return (100 * this.hits / this.totalShots);
		}
		return 0;
	}

}
