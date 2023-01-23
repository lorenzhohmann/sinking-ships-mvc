package view.console;

public class StatisticsWindow {

	/**
	 * Shows the parameters as a statistic table
	 * 
	 * @param totalShots  - amount of all made shots
	 * @param hits        - amount of hits
	 * @param noHits      - amount of shots that were not hit
	 * @param successRate - the calculated rate (ratio hits/no hits)
	 */
	public void showStatistic(int totalShots, int hits, int noHits, double successRate) {
		System.out.println();
		System.out.println("//=============================================================\\\\");
		System.out.println("                                                               ");
		System.out.println("               	       Deine Trefferquote!         		       ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Anzahl Versuche:     ||  " + totalShots + "          ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Anzahl Treffer:      ||  " + hits + "               ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Anzahl Fehlversuche: ||  " + noHits + "              ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Trefferquote:        ||  " + successRate + "%        ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("                                                               ");
		System.out.println("\\\\=============================================================//");
	}

}
