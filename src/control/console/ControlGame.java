package control.console;

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

	/**
	 * The player object of the human player
	 */
	private Player player;

	/**
	 * The player object of the AI
	 */
	private Player enemy;

	/**
	 * Wheter it's the turn of the human player
	 */
	private boolean ownTurn;

	/**
	 * The amount of shots in a row before it's the other players turn
	 */
	private int shotsInARow;

	private ControlAI aiController;
	private ControlGameActions actionController;

	@Override
	public void initControl(Player player, Player enemy) {
		this.player = player;
		this.enemy = enemy;
		this.ownTurn = true;
		this.shotsInARow = 1;
		this.aiController = new ControlAI();
		this.actionController = new ControlGameActions();

		this.game = new GameView(this, ((AI) enemy).getDifficulty().getName());
	}

	@Override
	public void startGame() {
		this.nextRound();
	}

	/**
	 * Starting the next game round. Can be called for human and for AI players
	 * because the ownTurn class field calls the correct method (nextPlayerRound()
	 * or nextAIRound())
	 */
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
		this.game.showPlayerRound(this.shotsInARow);

		// check for win
		if (this.actionController.isGameOver(enemiesMatchfield)) {
			this.endGame(true);
			return;
		}

		// hit evalutation
		if (enemiesMatchfield.isLastShotHit()) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.game.showPlayerShotEvaluation(shotsInARow);

			Toolkit.getDefaultToolkit().beep();
			this.shotsInARow++;

			boolean fullShipDown = enemiesMatchfield.isShipSunken(enemiesMatchfield.getLastChoose());
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
			this.shotsInARow = 1;

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
		this.game.showEnemiesRound(this.shotsInARow);

		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// no action.
		}

		// show players matchfield with all ships and states
		this.game.showEnemiesMatchfield(shotsInARow);

		// check for KI win
		if (this.actionController.isGameOver(this.player.getMatchfield())) {
			this.endGame(false);
			return;
		}

		// do AI shot & hit evalutation
		Matchfield matchfield = this.player.getMatchfield();
		Coordinate kiCoordinate = aiController.chooseCoordinateByDifficulty(matchfield,
				((AI) this.enemy).getDifficulty());
		boolean hit = this.actionController.shoot(this.player.getMatchfield(), kiCoordinate);
		this.game.enemyShotEvaluation(hit);
		if (hit) {
			Toolkit.getDefaultToolkit().beep();
			this.shotsInARow++;

			// wait after successfull shot
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

		} else {
			this.ownTurn = true;
			this.shotsInARow = 1;

			// wait on player change
			try {
				Thread.sleep(ConsoleGUI.GAME_INTERRUPTION);
			} catch (InterruptedException e) {
				// no action.
			}

		}

	}

	/**
	 * Introduces the ending of the game. Initiates the GameOver controller.
	 * 
	 * @param playerIsWinner - Whether the human player has won the game
	 */
	private void endGame(boolean playerIsWinner) {
		GameOverHandler gameOverHandler = new ControlGameOver();
		gameOverHandler.initControl(playerIsWinner, this.enemy);
	}

	@Override
	public void showPlayersMatchfield() {
		this.showMatchfield(this.player, true);
	}

	@Override
	public void showEnemiesMatchfield() {
		this.showMatchfield(this.enemy, false);
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
		if (coordinateString.matches("[a-j](1|2|3|4|5|6|7|8|9|10)")) {
			Matchfield enemiesMatchfield = this.enemy.getMatchfield();
			enemiesMatchfield.getCoordinateByString(coordinateString);

			if (enemiesMatchfield.getLastChoose().hasHit()) {
				this.game.printFieldAlreadyShot();
			} else {
				this.actionController.shoot(this.enemy.getMatchfield(), enemiesMatchfield.getLastChoose());
				success = true;
			}
		} else if ("--help".equalsIgnoreCase(coordinateString) || "help".equalsIgnoreCase(coordinateString) // NOPMD
				|| "?".equalsIgnoreCase(coordinateString)) { // NOPMD
			ConsoleGUI.showHelp();
		} else {
			this.game.showInvalidInput();
		}

		return success;
	}

}
