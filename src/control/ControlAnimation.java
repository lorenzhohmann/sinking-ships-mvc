package control;

import java.io.IOException;

import view.console.Animation;
import view.console.MenuHandler;

public class ControlAnimation {

	public void startApp() {
		// Animation animation = new Animation(300);
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
