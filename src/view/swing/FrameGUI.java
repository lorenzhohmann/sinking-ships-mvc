package view.swing;

import javax.swing.JFrame;

public class FrameGUI extends JFrame {

	/**
	 * Time between a game instruction and the following action (in ms)
	 */
	public static final int GAME_INTERRUPTION = 2000;

	public FrameGUI() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Schiffe Versenken");
		this.setSize(300, 200);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
	}

	public void repaint() {
		this.setVisible(true);
	}

	/**
	 * Shows a notification on the console
	 * 
	 * @param msg   - the message to appear
	 * @param style - adds a suffix and prefix for the message
	 */
	public static void print(String msg, String style) {

	}

	/**
	 * Shows a notification on the console with the style 'normal'
	 * 
	 * @param msg - the message to appear
	 */
	public static void print(String msg) {

	}

	/**
	 * Prints the help menu to the console
	 */
	public static void showHelp() {

	}

}
