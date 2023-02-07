package view.swing;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GameOver extends JPanel {

	/**
	 * Unique ID to identify version of the class for the Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	private FrameGUI gui;

	public GameOver(FrameGUI gui) {
		super();
		this.gui = gui;
	}

	/**
	 * Shows the ending screen with a message for the player
	 * 
	 * @param playerIsWinner - whether the player or AI is winner. True means, that
	 *                       a congratulations message appears. False means, that a
	 *                       game over message appears.
	 */
	public void showEndScreen(boolean playerIsWinner) {
		this.setLayout(new BorderLayout(0, 0));

		JLabel message = new JLabel("", SwingConstants.CENTER);
		message.setFont(new Font("Arial", Font.BOLD, 35));

		if (playerIsWinner) {
			message.setText("Glueckwunsch! Du hast gewonnen!");
		} else {
			message.setText("Game Over! Du hast verloren!");
		}

		this.add(message);

		this.gui.setSize(750, 200);
		this.gui.setLocationRelativeTo(null);
		this.gui.setContentPane(this);
		this.gui.repaint();
	}

}
