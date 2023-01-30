package control.console;

import java.io.IOException;

import view.console.Animation;
import view.console.MenuHandler;

public class ControlAnimation {

	/**
	 * Start point of the application. Displays the intro and inits the menu
	 * handler.
	 */
	public void startApp() {
		Animation animation = new Animation();
		try {
			animation.showIntro();
		} catch (InterruptedException | IOException e) {
			// no action.
		}

		MenuHandler menuHandler = new ControlMenu();
		menuHandler.initControl();
	}

}
