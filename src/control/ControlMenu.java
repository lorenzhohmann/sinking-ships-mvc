package control;

import model.KI;
import model.Status;
import view.Difficulty;
import view.Menu;
import view.MenuHandler;
import view.ShipPositioningHandler;

public class ControlMenu implements MenuHandler {

	private Menu menu;
	private Status status;
	private KI enemy;

	@Override
	public void initControl() {
		this.menu = new Menu(this);

		this.enemy = new KI();
		this.showGameModeMenu();
	}

	private void showGameModeMenu() {
		this.status = Status.WAITING;
		this.menu.showGameModeMenu();
	}

	private void showSettingsMenu() {

		menu.showSettings();

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
