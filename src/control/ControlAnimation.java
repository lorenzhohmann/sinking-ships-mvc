package control;

import view.console.Animation;
import view.console.MenuHandler;

public class ControlAnimation {

	public void startApp() {
		// Animation animation = new Animation(300);
		Animation animation = new Animation();
		try {
			animation.showIntro();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MenuHandler menuHandler = new ControlMenu();
		menuHandler.initControl();
	}

}
