package control;

import java.awt.Toolkit;

import model.AI;
import model.Coordinate;
import model.Matchfield;
import model.Player;
import view.console.ConsoleGUI;
import view.console.GameHandler;
import view.console.GameOverHandler;
import view.console.GameView;
import view.console.Playground;

public class ControlGame implements GameHandler {

	private GameView game;
	private Player player;
	private Player enemy;
	private boolean ownTurn;
	private int showInARow;

	@Override
	public void initControl(Player player, Player enemy) {
		this.player = player;
		this.enemy = enemy;
		this.ownTurn = true;
		this.showInARow = 1;

		this.game = new GameView(this, ((AI) enemy).getDifficulty().getName());
	}

	@Override
	public void startGame() {
		this.nextRound();
	}

	private void nextRound() {

		this.game.showHead();

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
		Matchfield enemiesMatchfield = this.enemy.getMatchfield();

		// show enemys matchfield without ship positions (only hitted ships)
		this.game.showPlayerRound(this.showInARow);

		// check for win
		if (enemiesMatchfield.isGameOver()) {
			this.endGame(true);
			return;
		}

		// hit evalutation
		if (enemiesMatchfield.isLastShotHit()) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.game.showPlayerShotEvaluation(showInARow);

			Toolkit.getDefaultToolkit().beep();
			this.showInARow++;

			boolean fullShipDown = enemiesMatchfield.isShipDown(enemiesMatchfield.getLastChoose());
			this.game.showShotResultMessage(fullShipDown);

			// wait before second shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

		} else {
			this.game.showNoShipHit();
			this.ownTurn = false;
			this.showInARow = 1;

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

		}
	}

	private void nextAIRound() {
		// show enemys matchfield without ship positions (only hitted ships)
		this.game.showEnemiesRound(this.showInARow);

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// no action.
		}

		// show players matchfield with all ships and states
		this.game.showEnemiesMatchfield(showInARow);

		// check for KI win
		if (this.player.getMatchfield().isGameOver()) {
			this.endGame(false);
			return;
		}

		// do AI shot & hit evalutation
		Matchfield matchfield = this.player.getMatchfield();
		Coordinate kiCoordinate = ((AI) this.enemy).chooseCoordinateByDifficulty(matchfield);
		boolean hit = this.player.getMatchfield().shoot(kiCoordinate);
		this.game.enemyShotEvaluation(hit);
		if (hit) {
			Toolkit.getDefaultToolkit().beep();
			this.showInARow++;

			// wait after successfull shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

		} else {
			this.ownTurn = true;
			this.showInARow = 1;

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

		}

	}

	private void endGame(boolean winner) {
		GameOverHandler gameOverHandler = new ControlGameOver();
		gameOverHandler.initControl(winner, this.enemy);
	}

	@Override
	public void showPlayersMatchfield() {
		this.createMatchfield(player, true);
	}

	@Override
	public void showEnemiesMatchfield() {
		this.createMatchfield(enemy, false);
	}

	private void createMatchfield(Player player, boolean showShips) {
		Playground matchfield = new Playground();
		String[][] status = player.getMatchfield().getStatus(showShips);
		matchfield.print(status);
	}

	@Override
	public boolean doMove(String input) {
		boolean breakLoop = false;

		// check if coordinate has correct syntax => shoot on this position
		if (input.matches("[a-j](1|2|3|4|5|6|7|8|9|10)")) {
			Matchfield enemiesMatchfield = this.enemy.getMatchfield();
			enemiesMatchfield.getCoordinateByString(input);

			if (enemiesMatchfield.getLastChoose().hasHit()) {
				this.game.printFieldAlreadyShot();
			} else {
				this.enemy.getMatchfield().shoot(enemiesMatchfield.getLastChoose());
				breakLoop = true;
			}
		} else if ("--help".equalsIgnoreCase(input) || "help".equalsIgnoreCase(input) || "?".equalsIgnoreCase(input)) { // NOPMD
																														// by
																														// Lorenz
																														// on
																														// 23.01.23,
																														// 15:27
			ConsoleGUI.showHelp();
		} else {
			this.game.showInvalidInput();
		}

		return breakLoop;
	}

}
