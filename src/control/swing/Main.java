package control.swing;

import view.swing.FrameGUIHandler;

public abstract class Main {

	public static void main(String[] args) {
		FrameGUIHandler frameGUIHandler = new ControlFrameGUI();
		frameGUIHandler.initControl();
	}
}
