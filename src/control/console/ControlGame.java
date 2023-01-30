package control.console;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import model.AI;
import model.Coordinate;
import model.Difficulty;
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

	@Override
	public void initControl(Player player, Player enemy) {
		this.player = player;
		this.enemy = enemy;
		this.ownTurn = true;
		this.shotsInARow = 1;

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
		if (this.isGameOver(enemiesMatchfield)) {
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
		if (this.isGameOver(this.player.getMatchfield())) {
			this.endGame(false);
			return;
		}

		// do AI shot & hit evalutation
		Matchfield matchfield = this.player.getMatchfield();
		Coordinate kiCoordinate = this.chooseCoordinateByDifficulty(matchfield, ((AI) this.enemy).getDifficulty());
		boolean hit = this.shoot(this.player.getMatchfield(), kiCoordinate);
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
				this.shoot(this.enemy.getMatchfield(), enemiesMatchfield.getLastChoose());
				success = true;
			}
		} else if ("--help".equalsIgnoreCase(coordinateString) || "help".equalsIgnoreCase(coordinateString)
				|| "?".equalsIgnoreCase(coordinateString)) { // NOPMD
			// by
			// Lorenz
			// on
			// 23.01.23,
			// 15:27
			ConsoleGUI.showHelp();
		} else {
			this.game.showInvalidInput();
		}

		return success;
	}

	/**
	 * Does a shoot on a specific coordinates and evaluates the shot
	 * 
	 * @param matchfield - the matchfield on which the shot should be performed.
	 *                   Null throws an Exception.
	 * @param coordinate - The coordinate that should be fired at. Null throws an
	 *                   Exception.
	 * @return whether the shot was a ship hit
	 */
	private boolean shoot(Matchfield matchfield, Coordinate coordinate) {
		coordinate.setHit(true);
		matchfield.setLastShot(coordinate);

		// set hit to last successful hit
		if (coordinate.hasShip()) {
			matchfield.setLastHit(coordinate);
		}

		matchfield.setLastShotHit(coordinate.hasShip());
		return matchfield.isLastShotHit();
	}

	/**
	 * Checks if a game is over by checking if all ship positions were hit
	 * 
	 * @param matchfield - the matchfield that should be checked
	 * @return whether all ships on the matchfield were hit
	 */
	private boolean isGameOver(Matchfield matchfield) {
		int shipPositionCounter = 0;
		int totalHittedShipsCounter = 0;
		ArrayList<Coordinate> coordinates = matchfield.getCoordinates();

		for (Coordinate coordinate : coordinates) {
			if (coordinate.hasShip()) {
				shipPositionCounter++;

				if (coordinate.hasHit()) {
					totalHittedShipsCounter++;
				}
			}
		}
		return shipPositionCounter == totalHittedShipsCounter;
	}

	/**
	 * Main AI logic. Chooses a coordinate for the next hit depending the difficulty
	 * 
	 * @param matchfield - the matchfield from where the coordinate should be chosen
	 *                   at
	 * @param difficulty - the difficulty of the AI
	 * @return a coordinate that was chosen by the AI
	 */
	private Coordinate chooseCoordinateByDifficulty(Matchfield matchfield, Difficulty difficulty) {

		Coordinate choosenCoordinate = null;
		ArrayList<Coordinate> coordinatesWithoutHits = matchfield.getCoordinatesWithoutHits();
		if (coordinatesWithoutHits.size() == 0)
			return null;

		switch (difficulty) {
		case EASY:
			choosenCoordinate = this.getEasyAICoordinate(coordinatesWithoutHits);
			break;
		case HARD:
			choosenCoordinate = getHardAICoordinate(matchfield, coordinatesWithoutHits);
			break;
		case EXTREM:
			choosenCoordinate = getExtremAICoordinate(coordinatesWithoutHits);
			break;
		default:
			break;
		}

		return choosenCoordinate;
	}

	/**
	 * AI chooses a random coordinate from all coordinates with a ship on it
	 * 
	 * @param coordinatesWithoutHits - all coordinates that have no hits
	 * @return the coordinate chosen by the AI
	 */
	private Coordinate getExtremAICoordinate(ArrayList<Coordinate> coordinatesWithoutHits) {
		Random random = new Random();
		Coordinate choosenCoordinate;

		// add all coordinates with ships
		ArrayList<Coordinate> coordinatesWithShips = new ArrayList<Coordinate>();
		for (Coordinate coordinate : coordinatesWithoutHits) {
			if (coordinate.hasShip())
				coordinatesWithShips.add(coordinate);
		}

		Collections.shuffle(coordinatesWithoutHits);
		for (int i = 0; i < coordinatesWithoutHits.size(); i++) {
			if (i >= coordinatesWithShips.size() / 4)
				break;
			coordinatesWithShips.add(coordinatesWithoutHits.get(i));
		}

		Collections.shuffle(coordinatesWithShips);
		int randomCoordinateWithShipNumber = random.nextInt(coordinatesWithShips.size());
		choosenCoordinate = coordinatesWithShips.get(randomCoordinateWithShipNumber);
		return choosenCoordinate;
	}

	/**
	 * AI chooses a coordinate depending the last shot. If a ship was hit on last
	 * shot it tries to find the ship part positions next to the last successful
	 * shot location.
	 * 
	 * @param matchfield             - where the coordinate should be get from
	 * @param coordinatesWithoutHits - all coordinates that have no hits
	 * @return the coordinate chosen by the AI
	 */
	private Coordinate getHardAICoordinate(Matchfield matchfield, ArrayList<Coordinate> coordinatesWithoutHits) {

		Coordinate choosenCoordinate = null;
		Random random = new Random();
		int randomCoordinateIndex = random.nextInt(coordinatesWithoutHits.size());

		Coordinate top;
		Coordinate right;
		Coordinate bottom;
		Coordinate left;

		// check if last hit is still possible?
		if (matchfield.getLastHit() != null) {

			int lastHitX = matchfield.getLastHit().getX();
			int lastHitY = matchfield.getLastHit().getY();

			top = matchfield.getCoordinate(lastHitX, lastHitY - 1);
			right = matchfield.getCoordinate(lastHitX + 1, lastHitY);
			bottom = matchfield.getCoordinate(lastHitX, lastHitY + 1);
			left = matchfield.getCoordinate(lastHitX - 1, lastHitY);

			// if all fields nearby already hit
			if ((top == null || top.hasHit()) && (right == null || right.hasHit())
					&& (bottom == null || bottom.hasHit()) && (left == null || left.hasHit())) {
				matchfield.setLastHit(null);
			}

		}

		if (matchfield.getLastHit() != null) {

			int lastHitX = matchfield.getLastHit().getX();
			int lastHitY = matchfield.getLastHit().getY();

			top = matchfield.getCoordinate(lastHitX, lastHitY - 1);
			right = matchfield.getCoordinate(lastHitX + 1, lastHitY);
			bottom = matchfield.getCoordinate(lastHitX, lastHitY + 1);
			left = matchfield.getCoordinate(lastHitX - 1, lastHitY);

			// check nearby fields of last shot or hit position
			boolean hasMatch = false;
			int tries = 0; // additional termination condition
			while (!hasMatch && tries < 20) {

				int direction = random.nextInt(4);

				switch (direction) {
				case 0: // top
					if (top != null) {
						choosenCoordinate = top;
						hasMatch = true;
					}
					break;
				case 1: // right
					if (right != null) {
						choosenCoordinate = right;
						hasMatch = true;
					}
					break;
				case 2: // bottom
					if (bottom != null) {
						choosenCoordinate = bottom;
						hasMatch = true;
					}
					break;
				case 3: // left
					if (left != null) {
						choosenCoordinate = left;
						hasMatch = true;
					}
					break;
				default: // fallback => shot on random coordinate:
					choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
					hasMatch = true;
					break;
				}

				tries++;
			}

		} else { // search new coordinate with even x and y position

			// mix list randomly
			Collections.shuffle(coordinatesWithoutHits);

			// get even coordinate
			for (Coordinate coordinate : coordinatesWithoutHits) {
				if (coordinate.getX() % 2 == coordinate.getY() % 2) {
					choosenCoordinate = coordinate;
					break;
				}
			}

			// if no match => get random
			if (choosenCoordinate == null) {
				choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
			}

		}
		return choosenCoordinate;
	}

	/**
	 * Returns the easy AI coordinate by collecting a random coordinate from all
	 * available coordinates.
	 * 
	 * @param coordinatesWithoutHits - all coordinates that have no hits
	 * @return the coordinate chosen by the AI
	 */
	private Coordinate getEasyAICoordinate(ArrayList<Coordinate> coordinatesWithoutHits) {
		Random random = new Random();
		int randomCoordinateIndex = random.nextInt(coordinatesWithoutHits.size());
		return coordinatesWithoutHits.get(randomCoordinateIndex);
	}

}
