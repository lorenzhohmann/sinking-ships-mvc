package control.swing;

import model.Bot;
import view.swing.Difficulty;
import view.swing.FrameGUI;
import view.swing.Menu;
import view.swing.MenuHandler;
import view.swing.ShipPositioningHandler;

public class ControlMenu implements MenuHandler {

	private Menu menu;
	private final FrameGUI gui;

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
		menu.showBotDifficultyMenu();
	}

	@Override
	public void setDifficulty(Difficulty difficulty) {
		Bot enemy = new Bot();
		// needs this strange cast only because of multiple GUIs in this project.
		// Doesn't appear in real
		enemy.setDifficulty(view.console.Difficulty.valueOf(difficulty.toString()));

		ShipPositioningHandler handler = new ControlShipPositioning(enemy, this.gui);
		handler.initControl();
	}

	@Override
	public void chooseSinglePlayer() {
		this.showSettingsMenu();
	}

}
