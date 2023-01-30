package view.console;

import java.awt.Toolkit;

public class GameOver {

	/**
	 * Shows the ending screen with a message for the player
	 * 
	 * @param playerIsWinner - whether the player or AI is winner. True means, that
	 *                       a congratulations message appears. False means, that a
	 *                       game over message appears.
	 */
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
