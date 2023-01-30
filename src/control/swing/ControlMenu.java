package control.swing;

import model.AI;
import model.Difficulty;
import view.swing.FrameGUI;
import view.swing.Menu;
import view.swing.MenuHandler;
import view.swing.ShipPositioningHandler;

public class ControlMenu implements MenuHandler {

	private Menu menu;
	private AI enemy;
	private FrameGUI gui;

	@Override
	public void initControl(FrameGUI gui) {
		this.gui = gui;
		this.enemy = new AI();

		this.menu = new Menu(this, gui);

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
		this.enemy.setDifficulty(difficulty);

		ShipPositioningHandler handler = new ControlShipPositioning(enemy, this.gui);
		handler.initControl();
	}

	@Override
	public void chooseSinglePlayer() {
		this.showSettingsMenu();
	}

}
