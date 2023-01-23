package control;

import model.KI;
import model.Statistic;
import view.GameOver;
import view.GameOverHandler;
import view.StatisticsWindow;

public class ControlGameOver implements GameOverHandler {

	@Override
	public void initControl(boolean playerIsWinner, KI enemy) {
		GameOver gameOver = new GameOver();
		gameOver.showEndScreen(playerIsWinner);

		StatisticsWindow statistics = new StatisticsWindow();
		Statistic statistic = enemy.getMatchfield().getStatistic();
		statistics.showStatistic(statistic.getTotalShots(), statistic.getHits(), statistic.getNoHits(),
				statistic.getSuccessRate());

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// exit console application
		System.exit(0);
	}

}
