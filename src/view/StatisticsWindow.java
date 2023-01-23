package view;

public class StatisticsWindow {

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
