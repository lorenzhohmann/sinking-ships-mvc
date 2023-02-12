package control.swing;

import model.Player;
import model.Statistic;
import view.swing.FrameGUI;
import view.swing.GameOver;
import view.swing.StatisticsWindow;

public class ControlGameOver {

	public ControlGameOver(Player enemy, boolean playerIsWinner, FrameGUI gui) {
		GameOver gameOver = new GameOver(gui);
		gameOver.showEndScreen(playerIsWinner);

		StatisticsWindow statistics = new StatisticsWindow();
		Statistic statistic = enemy.getMatchfield().getStatisticsObject();
		statistics.showStatistic(statistic.getTotalShots(), statistic.getHits(), statistic.getNoHits(),
				statistic.getHitRate());
	}

}
