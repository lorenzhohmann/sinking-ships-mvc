package control.swing;

import view.swing.FrameGUI;
import view.swing.FrameGUIHandler;
import view.swing.MenuHandler;

public class ControlFrameGUI implements FrameGUIHandler {

	private FrameGUI gui;

	public ControlFrameGUI() {
		this.gui = new FrameGUI();
	}

	@Override
	public void initControl() {
		this.showMenu();
	}

	private void showMenu() {
		MenuHandler handler = new ControlMenu(this.gui);
		handler.initControl();
	}

}
