package control;

import model.Player;
import model.Statistic;
import view.console.ConsoleGUI;
import view.console.GameOver;
import view.console.GameOverHandler;
import view.console.StatisticsWindow;

public class ControlGameOver implements GameOverHandler {

	@Override
	public void initControl(boolean playerIsWinner, Player enemy) {
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

		ConsoleGUI.scanner.close();

		System.exit(0);
	}

}
