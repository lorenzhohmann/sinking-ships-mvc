package control.console;

import model.Player;
import model.Statistic;
import view.console.GameOver;
import view.console.GameOverHandler;
import view.console.StatisticsWindow;

public class ControlGameOver implements GameOverHandler {

	private final Player enemy;

	public ControlGameOver(Player enemy) {
		this.enemy = enemy;
	}

	@Override
	public void initControl(boolean playerIsWinner) {
		GameOver gameOver = new GameOver();
		gameOver.showEndScreen(playerIsWinner);

		StatisticsWindow statistics = new StatisticsWindow();
		Statistic statistic = this.enemy.getMatchfield().getStatisticsObject();
		statistics.showStatistic(statistic.getTotalShots(), statistic.getHits(), statistic.getNoHits(),
				statistic.getHitRate());
	}

}
