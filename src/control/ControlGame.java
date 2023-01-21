package control;

import java.awt.Toolkit;

import model.Coordinate;
import model.KI;
import model.Player;
import model.Status;
import view.ConsoleGUI;
import view.Game;
import view.GameHandler;
import view.Matchfield;

public class ControlGame implements GameHandler {

	private Game game;
	private Status status;
	private Player player;
	private KI enemy;
	private boolean ownTurn;
	private int run;

	@Override
	public void initControl(Player player, KI enemy, Status status) {
		this.player = player;
		this.enemy = enemy;
		this.status = status;
		this.ownTurn = true;
		this.run = 1;

		this.game = new Game(this, enemy.getDifficulty().getName());
	}

	@Override
	public void startGame() {

		if (this.status != Status.WARMUP) {
			return;
		}

		this.status = Status.RUNNING;

		this.player.setReady(true);
		this.enemy.setReady(true);

		this.nextRound();
	}

	private void nextRound() {

		this.game.printHead();

		// show players field
		if (this.ownTurn) {
			this.nextPlayerRound();
		} else { // show enemys field (without ships
			// TODO
//			this.nextKIRound();
		}

		// begin next round
		this.nextRound();
	}

	private void nextPlayerRound() {

		boolean hit = false;
		Coordinate choosenCoordinate = null;

		// show enemys matchfield without ship positions (only hitted ships)
		hit = this.game.printPlayerRound(this.run);

		// check for win
		if (this.enemy.getMatchfield().isGameOver()) {
			this.endGame(true);
			return;
		}

		// hit evalutation
		if (hit) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.game.printShotEvaluation(run);

			Toolkit.getDefaultToolkit().beep();
			this.run++;

			boolean fullShipDown = this.enemy.getMatchfield().isShipDown(choosenCoordinate);
			this.game.printAfterShotMessage(fullShipDown);

			// wait before second shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			this.game.printNoShipHit();
			this.ownTurn = false;
			this.run = 1;

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	private void endGame(boolean winner) {

		if (this.status != Status.RUNNING)
			return;
		this.status = Status.ENDING;

		this.screen.showEndScreen(winner);
		this.enemy.getMatchfield().calculateAndPrintStatistics(this.screen);

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// exit console application
		System.exit(0);
	}

	@Override
	public void showPlayersMatchfield() {
		Matchfield matchfield = new Matchfield(player.getMatchfield().getFieldsize());
		String[][] status = player.getMatchfield().getStatus(false);
		matchfield.show(status);
	}

	@Override
	public boolean chooseCoordinate(String input) {
		Coordinate choosenCoordinate = this.enemy.getMatchfield().getCoordinateByString(input);

		if (choosenCoordinate.hasHit()) {
			this.game.printFieldAlreadyShot();
		} else {
			this.enemy.getMatchfield().shoot(choosenCoordinate);
		}

		return choosenCoordinate.hasHit();
	}

	@Override
	public void showEnemiesMatchfield() {
		Matchfield matchfield = new Matchfield(enemy.getMatchfield().getFieldsize());
		String[][] status = enemy.getMatchfield().getStatus(false);
		matchfield.show(status);
	}

}
