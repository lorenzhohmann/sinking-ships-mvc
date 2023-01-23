package control.console;

import model.AI;
import model.Difficulty;
import view.console.Menu;
import view.console.MenuHandler;
import view.console.ShipPositioningHandler;

public class ControlMenu implements MenuHandler {

	private Menu menu;
	private AI enemy;

	@Override
	public void initControl() {
		this.menu = new Menu(this);

		this.enemy = new AI();
		this.showGameModeMenu();
	}

	private void showGameModeMenu() {
		this.menu.showGameModeMenu();
	}

	private void showSettingsMenu() {

		menu.showAIDifficultyMenu();

		ShipPositioningHandler handler = new ControlShipPositioning(enemy);
		handler.initControl();
	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		this.enemy.setDifficulty(difficulty);
	}

	@Override
	public void chooseSinglePlayer() {
		this.showSettingsMenu();
	}

}
