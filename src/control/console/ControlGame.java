package control.console;

import java.awt.Toolkit;

import model.Bot;
import model.Coordinate;
import model.Matchfield;
import model.Player;
import view.console.ConsoleGUI;
import view.console.GameHandler;
import view.console.GameMessages;
import view.console.GameView;
import view.console.Playground;

public class ControlGame implements GameHandler {

	private GameView gameView;
	private GameMessages messages;

	/**
	 * The player object of the human player
	 */
	private final Player player;

	/**
	 * The player object of the bot
	 */
	private final Player enemy;

	public ControlGame(Player player, Player enemy) {
		this.player = player;
		this.enemy = enemy;
	}

	@Override
	public void initControl() {
		this.gameView = new GameView(this);
		this.messages = new GameMessages(((Bot) this.enemy).getDifficulty().getName());
	}

	@Override
	public void startGame() {
		// start first round with player
		this.nextPlayerRound();
	}

	private void nextPlayerRound() {
		this.gameView.showPlayerRound(this.player.getShotsInARow());
	}

	private void nextBotRound() {
		this.gameView.showEnemiesRound(this.enemy.getShotsInARow());

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// no action.
		}

		// show players matchfield with all ships and states
		this.gameView.showEnemiesMatchfield(this.enemy.getShotsInARow());

		// check for bot win
		if (ControlGameActions.isGameOver(this.player.getMatchfield())) {
			this.endGame(false);
			return;
		}

		// do bot shot & hit evalutation
		Matchfield playerMatchfield = this.player.getMatchfield();
		Coordinate botCoordinate = ControlBot.chooseCoordinateByDifficulty(playerMatchfield,
				((Bot) this.enemy).getDifficulty());
		boolean shotWasHit = botCoordinate.shoot(this.player.getMatchfield());

		this.gameView.showEnemiesMatchfield(this.enemy.getShotsInARow());
		this.messages.enemyShotEvaluation(shotWasHit);

		if (shotWasHit) {
			Toolkit.getDefaultToolkit().beep();
			this.enemy.increaseShotsInARow();

			// wait after successfull shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

			this.nextBotRound();
		} else {
			this.enemy.resetShotsInARow();

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
		new ControlGameOver(this.enemy, playerIsWinner);
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
		Playground playground = new Playground();
		String[][] status = player.getMatchfield().getStatusArray(showShips);
		playground.print(status);
	}

	@Override
	public boolean doMove(String coordinateString) {
		boolean success = false;

		String helpCMDWithDash = "--help";
		String helpCMD = "help";
		String questionCMD = "?";

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
		} else if (helpCMDWithDash.equalsIgnoreCase(coordinateString) || helpCMD.equalsIgnoreCase(coordinateString)
				|| questionCMD.equalsIgnoreCase(coordinateString)) {
			ConsoleGUI.showHelp();
		} else {
			this.messages.showInvalidInput();
		}

		return success;
	}

	/**
	 * Should be called after the player made a shot on the enemies matchfield.
	 * Checks if the player has won the match. Furthermore it starts the next player
	 * round if the last shot was a hit or starts a bot round if the last shot was
	 * no hit.
	 */
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

			// wait before second shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

			this.nextPlayerRound();
		} else {
			this.messages.showNoShipHit();
			this.player.resetShotsInARow();

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}
			this.nextBotRound();
		}
	}

}
