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

	/**
	 * Calculates the hit rate
	 * 
	 * @return - float value containing the hit rate. Return 0 when no shots are
	 *         made
	 */
	public float getHitRate() {
		float hitRate = 0;
		if (totalShots > 0) {
			hitRate = 100 * this.hits / this.totalShots;
		}
		return hitRate;
	}

}
