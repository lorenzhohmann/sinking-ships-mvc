package control.console;

import model.Player;
import model.Statistic;
import view.console.GameOver;
import view.console.StatisticsWindow;

public class ControlGameOver {

	public ControlGameOver(Player enemy, boolean playerIsWinner) {
		GameOver gameOver = new GameOver();
		gameOver.showEndScreen(playerIsWinner);

		StatisticsWindow statistics = new StatisticsWindow();
		Statistic statistic = enemy.getMatchfield().getStatisticsObject();
		statistics.showStatistic(statistic.getTotalShots(), statistic.getHits(), statistic.getNoHits(),
				statistic.getHitRate());
	}
}
