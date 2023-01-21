package control;

import view.Animation;
import view.AnimationHandler;
import view.MenuHandler;

public class ControlAnimation implements AnimationHandler {

	@Override
	public void initControl() {

		// Animation animation = new Animation(300);
		Animation animation = new Animation(0);
		try {
			animation.playAnimation();
		} catch (Exception e) {
			e.printStackTrace();
		}

		MenuHandler menuHandler = new ControlMenu();
		menuHandler.initControl();
	}

}
