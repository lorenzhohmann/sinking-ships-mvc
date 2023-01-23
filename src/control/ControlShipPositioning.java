package control;

import java.io.IOException;

import model.AI;
import model.Human;
import model.Player;
import view.console.ConsoleGUI;
import view.console.GameHandler;
import view.console.Matchfield;
import view.console.ShipPositioning;
import view.console.ShipPositioningHandler;

public class ControlShipPositioning implements ShipPositioningHandler {

	private ShipPositioning shipPositioning;
	private Player human;
	private Player enemy;

	public ControlShipPositioning(AI enemy) {
		this.enemy = enemy;
		this.human = new Human();
	}

	@Override
	public void initControl() {
		shipPositioning = new ShipPositioning(this);

		this.showShipPositioningMenu(enemy);
	}

	private void showShipPositioningMenu(Player enemy) {
		this.shipPositioning.showShipPositioning(false);
	}

	@Override
	public void placeEnemiesShipsRandomly() {
		this.enemy.getMatchfield().placeRandomShips();
	}

	@Override
	public void placePlayersShipsRandomly() {
		this.human.getMatchfield().placeRandomShips();
	}

	@Override
	public void showPlayersMatchfield() {
		Matchfield matchfield = new Matchfield();
		String[][] status = this.human.getMatchfield().getStatus(true);
		matchfield.print(status);
	}

	@Override
	public void resetPlayersShips() {
		human.getMatchfield().resetShips();
	}

	@Override
	public boolean setShipPositionsManual(String positionString, int length) {
		return human.getMatchfield().positionShipsByString(positionString, length);
	}

	@Override
	public void startGame() {
		GameHandler gameHandler = new ControlGame();
		gameHandler.initControl(human, enemy);
		gameHandler.startGame();
	}

	@Override
	public int handlePositioningInput(String input) {
		if (input.equalsIgnoreCase("z")) { // generate new random ship positions

			// clear console
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {
			}
			ConsoleGUI.print("Deine Flotte wurde neu positioniert!");

			this.resetPlayersShips();
			this.placePlayersShipsRandomly();
			this.showPlayersMatchfield();

			this.shipPositioning.showShipPositioningMenu();

			return 1;
		} else if (input.equalsIgnoreCase("m")) {
			this.shipPositioning.showManualShipPositioning();
			return 2;

		} else if (input.equalsIgnoreCase("s")) { // start game
			this.startGame();
			return 2;
		}

		ConsoleGUI.print("Ungueltige Eingabe, waehle [S], [Z] oder [M]!", "error");
		this.shipPositioning.showShipPositioning(true);

		return 0;
	}

}
