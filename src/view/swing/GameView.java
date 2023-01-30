package view.swing;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JPanel {

	/**
	 * Difficulty level of the humans enemy (doesn't change while playing)
	 */
	private String enemyDifficulty;
	private FrameGUI gui;
	private JPanel playgroundPanel;
	private JPanel bottomPanel;

	/**
	 * The game controller of this view
	 */
	private GameHandler gameHandler;

	public GameView(GameHandler gameHandler, String enemyDifficulty, FrameGUI gui) {
		this.gameHandler = gameHandler;
		this.enemyDifficulty = enemyDifficulty;
		this.gui = gui;

		this.setLayout(new BorderLayout(0, 0));

		this.bottomPanel = new JPanel();
		this.bottomPanel.setMinimumSize(new Dimension(100, 80));
		this.bottomPanel.setMaximumSize(new Dimension(100, 80));
		this.bottomPanel.setPreferredSize(new Dimension(100, 80));
		this.add(this.bottomPanel, BorderLayout.SOUTH);

		this.playgroundPanel = new JPanel();
		this.add(this.playgroundPanel, BorderLayout.CENTER);

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

		this.showEnemyHeadline(shotInARow);
		this.gameHandler.showEnemiesMatchfield();
		this.showShotInstruction();

		for (Component c : this.gui.getContentPane().getComponents()) {
			System.out.println(c.getClass().getCanonicalName());
		}

		// loops for own shot
//		while (ConsoleGUI.scanner.hasNext()) {
//
//			String input = ConsoleGUI.scanner.next();
//			boolean moveSuccessfull = this.gameHandler.doMove(input);
//
//			if (moveSuccessfull) {
//				break;
//			}
//		}
		this.gui.getContentPane().add(this);
		this.gui.repaint();
	}

	/**
	 * Shows the enemies round information
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesRound(int shotInARow) {

	}

	/**
	 * Shows the enemies matchfield
	 * 
	 * @param shotInARow - the amount of shots in a row
	 */
	public void showEnemiesMatchfield(int shotInARow) {

	}

	/**
	 * Prints global game information
	 */
	public void showHead() {

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

	/**
	 * Shows an error message
	 */
	public void printFieldAlreadyShot() {

	}

	/**
	 * Shows a message wheter a full ship was hit
	 * 
	 * @param fullShipDown - if the full ship was hit. Conditions the output
	 */
	public void showShotResultMessage(boolean fullShipDown) {

	}

	/**
	 * Shows a instruction for the player how to play
	 */
	public void showShotInstruction() {

	}

	/**
	 * Shows a message to wait
	 */
	public void showWaitingForSecondPlayer() {

	}

	/**
	 * Shows a message that a second player has connected
	 */
	public void showSecondPlayerConnected() {

	}

	/**
	 * Shows a message that no ship was hit and it's the enemies turn
	 */
	public void showNoShipHit() {

	}

	/**
	 * Shows an error message becuase of wrong input data
	 */
	public void showInvalidInput() {

	}

	/**
	 * Shows a message that it's now enemies turn
	 */
	private void showEnemiesTurn() {
		FrameGUI.print("Dein Gegner ist am Zug!", "waiting");
	}

	/**
	 * Shows a message with the result of the enemies shot
	 * 
	 * @param wasHit - if a ship was hit. Changes the output
	 */
	public void enemyShotEvaluation(boolean wasHit) {
		if (wasHit) {
			FrameGUI.print("Eines Deiner Schiffe wurde getroffen! Dein Gegner ist noch einmal am Zug!");
		} else {
			FrameGUI.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");
		}
	}

}
