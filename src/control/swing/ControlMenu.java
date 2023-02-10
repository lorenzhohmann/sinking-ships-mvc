package control.swing;

import model.Difficulty;
import model.Game;
import view.swing.FrameGUI;
import view.swing.Menu;
import view.swing.MenuHandler;
import view.swing.ShipPositioningHandler;

public class ControlMenu implements MenuHandler {

	private Menu menu;
	private FrameGUI gui;

	public ControlMenu(FrameGUI gui) {
		this.gui = gui;
	}

	@Override
	public void initControl() {
		this.menu = new Menu(this, this.gui);

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

		ShipPositioningHandler handler = new ControlShipPositioning(game, this.gui);
		handler.initControl();
	}

	@Override
	public void chooseSinglePlayer() {
		this.showSettingsMenu();
	}

}
