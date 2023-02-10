package control.console;

import model.Bot;
import model.Difficulty;
import view.console.Menu;
import view.console.MenuHandler;
import view.console.ShipPositioningHandler;

public class ControlMenu implements MenuHandler {

	private Menu menu;

	@Override
	public void initControl() {
		this.menu = new Menu(this);

		this.showGameModeMenu();
	}

	private void showGameModeMenu() {
		this.menu.showGameModeMenu();
	}

	private void showSettingsMenu() {
		menu.showAIDifficultyMenu();
	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		Bot enemy = new Bot();
		enemy.setDifficulty(difficulty);

		ShipPositioningHandler handler = new ControlShipPositioning(enemy);
		handler.initControl();
	}

	@Override
	public void chooseSinglePlayer() {
		this.showSettingsMenu();
	}

}
