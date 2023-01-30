package view.swing;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Difficulty;

public class Menu extends JPanel {

	private FrameGUI gui;
	private MenuHandler handler;

	public Menu(MenuHandler handler, FrameGUI gui) {
		this.handler = handler;
		this.gui = gui;

		this.setLayout(new FlowLayout());

		this.createHeadline();
		this.createGameModeButtons(handler);

		this.gui.setContentPane(this);
	}

	private void createGameModeButtons(MenuHandler handler) {
		JButton spButton = new JButton("Einzelspieler");
		spButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.chooseSinglePlayer();
			}
		});

		JButton mpButton = new JButton("Multiplayer");
		mpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("MULTIPLAYER IS NOT WORKING");
			}
		});

		this.add(spButton);
		this.add(mpButton);
	}

	private void createHeadline() {
		JLabel headline = new JLabel("Schiffe versenken");
		headline.setBorder(BorderFactory.createMatteBorder(20, 0, 10, 0, new Color(0, 0, 0, 0)));
		headline.setFont(new Font("Arial", Font.BOLD, 24));
		this.add(headline);
	}

	/**
	 * Shows the gamemode menu and reads the users console input
	 */
	public void showGameModeMenu() {
		this.setVisible(true);
	}

	/**
	 * Shows the options to choose the AIs difficulty. It also reads the users
	 * console input
	 */
	public void showAIDifficultyMenu() {
		this.remove(1);
		this.remove(1);

		JButton easyButton = new JButton("Einfach");
		easyButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.setDifficulty(Difficulty.EASY);
			}
		});

		JButton hardButton = new JButton("Schwer");
		hardButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.setDifficulty(Difficulty.HARD);
			}
		});

		JButton extremButton = new JButton("Extrem");
		extremButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				handler.setDifficulty(Difficulty.EXTREM);
			}
		});

		this.add(easyButton);
		this.add(hardButton);
		this.add(extremButton);
		this.gui.repaint();
	}
}
