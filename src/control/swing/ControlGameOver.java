package control.swing;

import model.Player;
import model.Statistic;
import view.swing.FrameGUI;
import view.swing.GameOver;
import view.swing.GameOverHandler;
import view.swing.StatisticsWindow;

public class ControlGameOver implements GameOverHandler {

	private Player enemy;
	private FrameGUI gui;

	public ControlGameOver(Player enemy, FrameGUI gui) {
		this.enemy = enemy;
		this.gui = gui;
	}

	@Override
	public void initControl(boolean playerIsWinner) {
		GameOver gameOver = new GameOver(this.gui);
		gameOver.showEndScreen(playerIsWinner);

		StatisticsWindow statistics = new StatisticsWindow();
		Statistic statistic = this.enemy.getMatchfield().getStatisticsObject();
		statistics.showStatistic(statistic.getTotalShots(), statistic.getHits(), statistic.getNoHits(),
				statistic.getHitRate());
	}

}
