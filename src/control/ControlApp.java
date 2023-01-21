package control;

import view.AnimationHandler;

public class ControlApp {

	public void startApp() {
		AnimationHandler animationHandler = new ControlAnimation();
		animationHandler.initControl();
	}

}
