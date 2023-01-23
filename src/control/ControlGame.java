package control;

import java.awt.Toolkit;

import model.Coordinate;
import model.KI;
import model.Player;
import model.Status;
import view.ConsoleGUI;
import view.Game;
import view.GameHandler;
import view.GameOverHandler;
import view.Matchfield;

public class ControlGame implements GameHandler {

	private Game game;
	private Status status;
	private Player player;
	private KI enemy;
	private boolean ownTurn;
	private int run;
	private Coordinate lastChosenCoordinate;
	private boolean lastShotHit = false;

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
			this.nextAIRound();
		}

		// begin next round
		this.nextRound();
	}

	private void nextPlayerRound() {

		// show enemys matchfield without ship positions (only hitted ships)
		this.game.printPlayerRound(this.run);

		// check for win
		if (this.enemy.getMatchfield().isGameOver()) {
			this.endGame(true);
			return;
		}

		// hit evalutation
		if (this.lastShotHit) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.game.printShotEvaluation(run);

			Toolkit.getDefaultToolkit().beep();
			this.run++;

			boolean fullShipDown = this.enemy.getMatchfield().isShipDown(this.lastChosenCoordinate);
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

	private void nextAIRound() {
		boolean hit = false;

		// show enemys matchfield without ship positions (only hitted ships)
		this.game.printEnemiesRound(this.run);

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// do KI shot
		model.Matchfield matchfield = this.player.getMatchfield();
		Coordinate kiCoordinate = ((KI) this.enemy).getKICoordinate(matchfield);
		hit = this.player.getMatchfield().shoot(kiCoordinate);

		// show players matchfield with all ships and states
		this.game.printEnemiesMatchfield(run);

		// check for KI win
		if (this.player.getMatchfield().isGameOver()) {
			this.endGame(false);
			return;
		}

		// hit evalutation
		if (hit) {
			Toolkit.getDefaultToolkit().beep();
			this.game.printYourGameWasHit();
			this.run++;

			// wait after successfull shot
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			this.game.printEnemyMadeNoHit();
			this.ownTurn = true;
			this.run = 1;

			// wait on player change
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}

	}

	private void endGame(boolean winner) {
		if (this.status != Status.RUNNING) {
			return;
		}
		this.status = Status.ENDING;

		GameOverHandler gameOverHandler = new ControlGameOver();
		gameOverHandler.initControl(winner, this.enemy);
	}

	@Override
	public void showPlayersMatchfield() {
		Matchfield matchfield = new Matchfield(player.getMatchfield().getFieldsize());
		String[][] status = player.getMatchfield().getStatus(true);
		matchfield.show(status);
	}

	@Override
	public void showEnemiesMatchfield() {
		Matchfield matchfield = new Matchfield(enemy.getMatchfield().getFieldsize());
		String[][] status = enemy.getMatchfield().getStatus(true);
		matchfield.show(status);
	}

	@Override
	public boolean doMove(String input) {
		// check if coordinate has correct syntax => shoot on this position
		if (input.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
			this.lastChosenCoordinate = this.enemy.getMatchfield().getCoordinateByString(input);

			if (this.lastChosenCoordinate.hasHit()) {
				this.game.printFieldAlreadyShot();
			} else {
				this.lastShotHit = this.enemy.getMatchfield().shoot(this.lastChosenCoordinate);
				return true;
			}
		} else if (input.equalsIgnoreCase("?") || input.equalsIgnoreCase("--help") || input.equalsIgnoreCase("help")) {
			ConsoleGUI.showHelp();
		} else {
			this.game.printInvalidInput();
		}

		return false;
	}

}
