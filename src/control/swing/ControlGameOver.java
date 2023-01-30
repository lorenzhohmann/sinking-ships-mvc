package control.swing;

import model.Player;
import model.Statistic;
import view.swing.GameOver;
import view.swing.GameOverHandler;
import view.swing.StatisticsWindow;

public class ControlGameOver implements GameOverHandler {

	@Override
	public void initControl(boolean playerIsWinner, Player enemy) {
		GameOver gameOver = new GameOver();
		gameOver.showEndScreen(playerIsWinner);

		StatisticsWindow statistics = new StatisticsWindow();
		Statistic statistic = enemy.getMatchfield().getStatisticsObject();
		statistics.showStatistic(statistic.getTotalShots(), statistic.getHits(), statistic.getNoHits(),
				statistic.getHitRate());
	}

}
