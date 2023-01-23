package view;

import java.awt.Toolkit;

public class GameOver {

	public void showEndScreen(boolean playerIsWinner) {
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");

		if (playerIsWinner) {
			System.out.println("||                Glueckwunsch! Du hast gewonnen!              ||");
			System.out.println("||                                                             ||");
			System.out.println("\\\\=============================================================//");
			Toolkit.getDefaultToolkit().beep();
			return;
		}

		System.out.println("||                  Game Over! Du hast verloren!               ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}

}
