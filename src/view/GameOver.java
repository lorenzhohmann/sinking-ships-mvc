package view;

import java.awt.Toolkit;

public class GameOver {

	public void showEndScreen(boolean winner) {
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");

		if (winner) {
			System.out.println("||                Glueckwunsch! Sie haben gewonnen!            ||");
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
