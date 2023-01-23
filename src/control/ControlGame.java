package control;

import java.awt.Toolkit;

import model.AI;
import model.Coordinate;
import model.Player;
import view.console.ConsoleGUI;
import view.console.GameHandler;
import view.console.GameOverHandler;
import view.console.GameView;
import view.console.Matchfield;

public class ControlGame implements GameHandler {

	private GameView game;
	private Player player;
	private Player enemy;
	private boolean ownTurn;
	private int showInARow;
	private Coordinate lastChosenCoordinate;
	private boolean lastShotHit = false;

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
		this.player.setReady(true);
		this.enemy.setReady(true);

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

		// show enemys matchfield without ship positions (only hitted ships)
		this.game.showPlayerRound(this.showInARow);

		// check for win
		if (this.enemy.getMatchfield().isGameOver()) {
			this.endGame(true);
			return;
		}

		// hit evalutation
		if (this.lastShotHit) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.game.showPlayerShotEvaluation(showInARow);

			Toolkit.getDefaultToolkit().beep();
			this.showInARow++;

			boolean fullShipDown = this.enemy.getMatchfield().isShipDown(this.lastChosenCoordinate);
			this.game.showShotResultMessage(fullShipDown);

			// wait before second shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			this.game.showNoShipHit();
			this.ownTurn = false;
			this.showInARow = 1;

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
		this.game.showEnemiesRound(this.showInARow);

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// do KI shot
		model.Matchfield matchfield = this.player.getMatchfield();
		Coordinate kiCoordinate = ((AI) this.enemy).chooseCoordinateByDifficulty(matchfield);
		hit = this.player.getMatchfield().shoot(kiCoordinate);

		// show players matchfield with all ships and states
		this.game.showEnemiesMatchfield(showInARow);

		// check for KI win
		if (this.player.getMatchfield().isGameOver()) {
			this.endGame(false);
			return;
		}

		// hit evalutation
		this.game.enemyShotEvaluation(hit);
		if (hit) {
			Toolkit.getDefaultToolkit().beep();
			this.showInARow++;

			// wait after successfull shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		} else {
			this.ownTurn = true;
			this.showInARow = 1;

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				e.printStackTrace();
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
		Matchfield matchfield = new Matchfield();
		String[][] status = player.getMatchfield().getStatus(showShips);
		matchfield.print(status);
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
			this.game.showInvalidInput();
		}

		return false;
	}

}
