package view.swing;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class FrameGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
		this.setResizable(false);
	}

	public void repaint() {
		this.setVisible(true);
	}

	@Override
	public void setContentPane(Container contentPane) {
		super.setContentPane(contentPane);
		this.repaint();
	}

	/**
	 * Shows a notification on the console
	 * 
	 * @param msg   - the message to appear
	 * @param style - adds a suffix and prefix for the message (error, waiting,
	 *              highlight)
	 */
	public static void print(String msg, String style) {
		JOptionPane.showMessageDialog(null, msg);
	}

	/**
	 * Shows a notification on the console with the style 'normal'
	 * 
	 * @param msg - the message to appear
	 */
	public static void print(String msg) {
		FrameGUI.print(msg, "normal");
	}

	/**
	 * Prints the help menu to the console
	 */
	public static void showHelp() {
		FrameGUI.print("Help not available on Swing GUI");
	}

}
