package view.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel {

	/**
	 * Unique ID to identify version of the class for the Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Difficulty level of the humans enemy (doesn't change while playing)
	 */
	private FrameGUI gui;
	private JPanel bottomPanel;

	/**
	 * The game controller of this view
	 */
	private GameHandler gameHandler;

	public GameView(GameHandler gameHandler, String enemyDifficulty, FrameGUI gui) { // NOPMD
		super();

		this.gameHandler = gameHandler;
		this.gui = gui;

		this.setLayout(new BorderLayout(0, 0));

		this.bottomPanel = new JPanel();
		this.bottomPanel.setMinimumSize(new Dimension(100, 80));
		this.bottomPanel.setMaximumSize(new Dimension(100, 80));
		this.bottomPanel.setPreferredSize(new Dimension(100, 80));
		this.add(this.bottomPanel, BorderLayout.SOUTH);

		JPanel playgroundPanel = new JPanel();
		this.add(playgroundPanel, BorderLayout.CENTER);

		this.gui.setSize(566, 700);
		this.gui.setLocationRelativeTo(null);
		this.gui.setContentPane(this);
		this.gui.repaint();
	}

	/**
	 * Displays the result of a players shot
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showPlayerShotEvaluation(int shotInARow) {
		this.showEnemyHeadline(shotInARow);
		this.gameHandler.showEnemiesMatchfield();
	}

	/**
	 * Shows the players round information and reads the users input from the
	 * console
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showPlayerRound(int shotInARow) {
		this.bottomPanel.removeAll();

		this.showEnemyHeadline(shotInARow);
		this.gameHandler.showEnemiesMatchfield();

		for (Component comp : this.gui.getContentPane().getComponents()) {
			comp.addMouseListener(new FieldMouseAdapter(comp, this.gameHandler)); // NOPMD
		}

		this.gui.repaint();
	}

	/**
	 * Shows the enemies round information
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesRound(int shotInARow) {
		this.bottomPanel.removeAll();

		this.showEnemiesMatchfield(shotInARow);
		this.showEnemiesTurn();

		this.gui.repaint();
	}

	/**
	 * Shows the enemies matchfield
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesMatchfield(int shotInARow) {
		this.showPlayersHeadline(shotInARow);
		this.gameHandler.showPlayersMatchfield();

		this.gui.repaint();
	}

	/**
	 * Shows the information with a label
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	private void showshotInARow(int shotInARow) {
		JLabel label = new JLabel(shotInARow + ". Schuss in Folge");
		this.bottomPanel.add(label);
	}

	/**
	 * Shows the headline for enemies round
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	private void showEnemyHeadline(int shotInARow) {
		JLabel label = new JLabel("Gegnerisches Feld");
		this.bottomPanel.add(label);
		this.showshotInARow(shotInARow);
	}

	/**
	 * Shows the headline for players round
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	private void showPlayersHeadline(int shotInARow) {
		JLabel label = new JLabel("Eigenes Feld");
		this.bottomPanel.add(label);
		this.showshotInARow(shotInARow);
	}

	private void showEnemiesTurn() {
		// not needed in Swing GUI
//		FrameGUI.print("Dein Gegner ist am Zug!", "waiting");
	}

}
