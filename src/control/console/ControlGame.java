package control.console;

import java.awt.Toolkit;

import model.Bot;
import model.Coordinate;
import model.Game;
import model.Matchfield;
import model.Player;
import view.console.ConsoleGUI;
import view.console.GameHandler;
import view.console.GameMessages;
import view.console.GameOverHandler;
import view.console.GameView;
import view.console.Playground;

public class ControlGame implements GameHandler {

	private GameView gameView;
	private GameMessages messages;

	private Game game;

	private ControlAI aiController;
	private ControlGameActions actionController;

	public ControlGame(Game game) {
		this.game = game;

		this.aiController = new ControlAI();
		this.actionController = new ControlGameActions();
	}

	@Override
	public void initControl() {
		this.gameView = new GameView(this);
		this.messages = new GameMessages(((Bot) this.game.getEnemy()).getDifficulty().getName());
	}

	@Override
	public void startGame() {
		// start first round with player
		this.nextPlayerRound();
	}

	private void nextPlayerRound() {
		// show enemys matchfield without ship positions (only hitted ships)
		this.gameView.showPlayerRound(this.game.getShotsInARow());
	}

	private void nextAIRound() {
		// show enemys matchfield without ship positions (only hitted ships)
		this.gameView.showEnemiesRound(this.game.getShotsInARow());

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// no action.
		}

		// show players matchfield with all ships and states
		this.gameView.showEnemiesMatchfield(this.game.getShotsInARow());

		// check for KI win
		if (this.actionController.isGameOver(this.game.getPlayer().getMatchfield())) {
			this.endGame(false);
			return;
		}

		// do AI shot & hit evalutation
		Matchfield matchfield = this.game.getPlayer().getMatchfield();
		Coordinate kiCoordinate = aiController.chooseCoordinateByDifficulty(matchfield,
				((Bot) this.game.getEnemy()).getDifficulty());
		boolean hit = kiCoordinate.shoot(this.game.getPlayer().getMatchfield());

		this.gameView.showEnemiesMatchfield(this.game.getShotsInARow());
		this.messages.enemyShotEvaluation(hit);

		if (hit) {
			Toolkit.getDefaultToolkit().beep();
			this.game.increaseShotsInARow();

			// wait after successfull shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

			this.nextAIRound();
		} else {
			this.game.resetShotsInARow();

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
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
		GameOverHandler gameOverHandler = new ControlGameOver(this.game.getEnemy());
		gameOverHandler.initControl(playerIsWinner);
	}

	@Override
	public void showPlayersMatchfield() {
		this.showMatchfield(this.game.getPlayer(), true);
	}

	@Override
	public void showEnemiesMatchfield() {
		this.showMatchfield(this.game.getEnemy(), true);
	}

	/**
	 * Initiates the visualization of the matchfield
	 * 
	 * @param player    - the player from whom the matchfield should be shown
	 * @param showShips - Whether the ships should be visible on the UI
	 */
	private void showMatchfield(Player player, boolean showShips) {
		Playground playground = new Playground();
		String[][] status = player.getMatchfield().getStatusArray(showShips);
		playground.print(status);
	}

	@Override
	public boolean doMove(String coordinateString) {
		boolean success = false;

		// check if coordinate has correct syntax => shoot on this position
		if (coordinateString.matches("[a-jA-J](1|2|3|4|5|6|7|8|9|10)")) {
			Matchfield enemiesMatchfield = this.game.getEnemy().getMatchfield();
			enemiesMatchfield.getCoordinateByString(coordinateString);

			if (enemiesMatchfield.getLastShot().hasHit()) {
				this.messages.printFieldAlreadyShot();
			} else {
				enemiesMatchfield.getLastShot().shoot(this.game.getEnemy().getMatchfield());
				this.showEnemiesMatchfield();
				this.evaluatePlayerRound();
				success = true;
			}
		} else if ("--help".equalsIgnoreCase(coordinateString) || "help".equalsIgnoreCase(coordinateString)
				|| "?".equalsIgnoreCase(coordinateString)) {
			ConsoleGUI.showHelp();
		} else {
			this.messages.showInvalidInput();
		}

		return success;
	}

	private void evaluatePlayerRound() {
		Matchfield enemiesMatchfield = this.game.getEnemy().getMatchfield();

		// check for win
		if (this.actionController.isGameOver(enemiesMatchfield)) {
			this.endGame(true);
			return;
		}

		// hit evaluation
		if (enemiesMatchfield.didLastShotHit()) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.gameView.showPlayerShotEvaluation(this.game.getShotsInARow());

			Toolkit.getDefaultToolkit().beep();
			this.game.increaseShotsInARow();

			boolean fullShipDown = enemiesMatchfield.getLastShot().isShipSunken(enemiesMatchfield);
			this.messages.showShotResultMessage(fullShipDown);

			// wait before second shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

			this.nextPlayerRound();
		} else {
			this.messages.showNoShipHit();
			this.game.resetShotsInARow();

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}
			this.nextAIRound();
		}
	}

}
