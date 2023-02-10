package control.swing;

import java.awt.Toolkit;

import model.Bot;
import model.Coordinate;
import model.Matchfield;
import model.Player;
import view.swing.FrameGUI;
import view.swing.GameHandler;
import view.swing.GameMessages;
import view.swing.GameOverHandler;
import view.swing.GameView;
import view.swing.Playground;

public class ControlGame implements GameHandler {

	private GameView gameView;
	private GameMessages messages;
	private FrameGUI gui;

	/**
	 * The player object of the human player
	 */
	private Player player;

	/**
	 * The player object of the AI
	 */
	private Player enemy;

	public ControlGame(Player player, Player enemy, FrameGUI gui) {
		this.player = player;
		this.enemy = enemy;
		this.gui = gui;
	}

	@Override
	public void initControl() {
		this.gameView = new GameView(this, this.gui);
		this.messages = new GameMessages(((Bot) this.enemy).getDifficulty().getName());
	}

	@Override
	public void startGame() {
		// start first round with player
		this.nextPlayerRound();
	}

	private void nextPlayerRound() {
		// show enemys matchfield without ship positions (only hitted ships)
		this.gameView.showPlayerRound(this.player.getShotsInARow());
	}

	private void nextAIRound() {
		// show enemys matchfield without ship positions (only hitted ships)
		this.gameView.showEnemiesRound(this.enemy.getShotsInARow());

		// show players matchfield with all ships and states
		this.gameView.showEnemiesMatchfield(this.enemy.getShotsInARow());

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// no action.
		}

		// check for KI win
		if (ControlGameActions.isGameOver(this.player.getMatchfield())) {
			this.endGame(false);
			return;
		}

		// do AI shot & hit evalutation
		Matchfield matchfield = this.player.getMatchfield();
		Coordinate kiCoordinate = ControlBot.chooseCoordinateByDifficulty(matchfield,
				((Bot) this.enemy).getDifficulty());
		boolean hit = kiCoordinate.shoot(this.player.getMatchfield());

		this.gameView.showEnemiesMatchfield(this.enemy.getShotsInARow());
		this.messages.enemyShotEvaluation(hit);

		if (hit) {
			Toolkit.getDefaultToolkit().beep();
			this.enemy.increaseShotsInARow();

			// wait after successfull shot
			try {
				Thread.sleep(FrameGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

			this.nextAIRound();
		} else {
			this.enemy.resetShotsInARow();

			// wait on player change
			try {
				Thread.sleep(FrameGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

			this.nextPlayerRound();
		}

	}

	/**
	 * Introduces the ending of the game. Initiates the GameOver controller.
	 * 
	 * @param playerIsWinner - Whether the human player has won the game
	 */
	private void endGame(boolean playerIsWinner) {
		GameOverHandler gameOverHandler = new ControlGameOver(this.enemy, this.gui);
		gameOverHandler.initControl(playerIsWinner);
	}

	@Override
	public void showPlayersMatchfield() {
		this.showMatchfield(this.player, true);
	}

	@Override
	public void showEnemiesMatchfield() {
		this.showMatchfield(this.enemy, true);
	}

	/**
	 * Initiates the visualization of the matchfield
	 * 
	 * @param player    - the player from whom the matchfield should be shown
	 * @param showShips - Whether the ships should be visible on the UI
	 */
	private void showMatchfield(Player player, boolean showShips) {
		Playground playground = new Playground(this.gui);
		String[][] status = player.getMatchfield().getStatusArray(showShips);
		playground.print(status);
	}

	@Override
	public boolean doMove(String coordinateString) {
		boolean success = false;

		// check if coordinate has correct syntax => shoot on this position
		if (coordinateString.matches("[a-jA-J](1|2|3|4|5|6|7|8|9|10)")) {
			Matchfield enemiesMatchfield = this.enemy.getMatchfield();
			enemiesMatchfield.getCoordinateByString(coordinateString);

			if (enemiesMatchfield.getLastShot().hasHit()) {
				this.messages.printFieldAlreadyShot();
			} else {
				enemiesMatchfield.getLastShot().shoot(this.enemy.getMatchfield());
				this.showEnemiesMatchfield();
				this.evaluatePlayerRound();
				success = true;
			}
		} else if ("--help".equalsIgnoreCase(coordinateString) || "help".equalsIgnoreCase(coordinateString)
				|| "?".equalsIgnoreCase(coordinateString)) {
			FrameGUI.showHelp();
		} else {
			this.messages.showInvalidInput();
		}

		return success;
	}

	private void evaluatePlayerRound() {
		Matchfield enemiesMatchfield = this.enemy.getMatchfield();

		// check for win
		if (ControlGameActions.isGameOver(enemiesMatchfield)) {
			this.endGame(true);
			return;
		}

		// hit evaluation
		if (enemiesMatchfield.didLastShotHit()) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.gameView.showPlayerShotEvaluation(this.player.getShotsInARow());

			Toolkit.getDefaultToolkit().beep();
			this.player.increaseShotsInARow();

			boolean fullShipDown = enemiesMatchfield.getLastShot().isShipSunken(enemiesMatchfield);
			this.messages.showShotResultMessage(fullShipDown);

			this.nextPlayerRound();
		} else {
			this.messages.showNoShipHit();
			this.player.resetShotsInARow();

			this.nextAIRound();
		}
	}

}
