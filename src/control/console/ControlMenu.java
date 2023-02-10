package control.console;

import model.Difficulty;
import model.Game;
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
		Game game = new Game();
		game.getEnemy().setDifficulty(difficulty);

		ShipPositioningHandler handler = new ControlShipPositioning(game);
		handler.initControl();
	}

	@Override
	public void chooseSinglePlayer() {
		this.showSettingsMenu();
	}

}
