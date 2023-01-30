package view.swing;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ShipPositioning extends JPanel {

	/**
	 * Handler for all ship positioning actions
	 */
	private ShipPositioningHandler shipPosHandler;
	private FrameGUI gui;
	private JPanel playgroundPanel;
	private JPanel bottomPanel;

	public ShipPositioning(ShipPositioningHandler handler, FrameGUI gui) {
		this.shipPosHandler = handler;
		this.gui = gui;

		this.setLayout(new BorderLayout(0, 0));

		this.bottomPanel = new JPanel();
		this.add(this.bottomPanel, BorderLayout.SOUTH);

		this.playgroundPanel = new JPanel();
		this.add(this.playgroundPanel, BorderLayout.CENTER);

		this.gui.setSize(566, 700);
		this.gui.setLocationRelativeTo(null);
		this.gui.setContentPane(this);
	}

	/**
	 * Shows the menu to choose between the positioning type of the ships
	 */
	public void showShipPositioningMenu() {
		this.bottomPanel.removeAll();

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());

		JLabel info = new JLabel("<html>Bist du mit der Positionierung deiner Schiffe zufrieden?</html>");
		info.setFont(new Font("Arial", Font.PLAIN, 12));

		JButton random = new JButton("Zufaellig");
		random.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shipPosHandler.handlePositioningInput("z");
			}
		});

		JButton manual = new JButton("Manuell");
		manual.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shipPosHandler.handlePositioningInput("m");
			}
		});

		JButton start = new JButton("Starten");
		start.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				shipPosHandler.handlePositioningInput("s");

			}
		});

		this.bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		this.bottomPanel.setMinimumSize(new Dimension(100, 80));
		this.bottomPanel.setMaximumSize(new Dimension(100, 80));
		this.bottomPanel.setPreferredSize(new Dimension(100, 80));
		this.bottomPanel.add(info);

		buttonPanel.add(random);
		buttonPanel.add(manual);
		buttonPanel.add(start);
		this.bottomPanel.add(buttonPanel);

		this.gui.repaint();
	}

	/**
	 * 
	 * @param hasManualPosition
	 */
	public void showShipPositioning(boolean hasManualPosition) {

		if (!hasManualPosition) { // if this function call does not come from manual positioning

			// place enemys ships randomly
			this.shipPosHandler.placeEnemiesShipsRandomly();

			// place players ships randomly
			this.shipPosHandler.placePlayersShipsRandomly();
		}

		// place players ships randomly
		this.shipPosHandler.showPlayersMatchfield();

		// position of ships page
		this.showShipPositioningMenu();

		this.gui.getContentPane().add(this);
		this.gui.repaint();
	}

	/**
	 * Shows and reads the manual positioning of ships
	 */
	public void showManualShipPositioning() {

		this.showManualShipPositioningInstruction();
		this.showShipPositioning(true);
	}

	/**
	 * Shows a short instruction of how to create a ship positioning string
	 */
	private void showManualShipPositioningInstruction() {

		JPanel inputDialog = new JPanel();
		inputDialog.setLayout(new BoxLayout(inputDialog, BoxLayout.PAGE_AXIS));

		inputDialog.add(new JLabel("Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!"));
		inputDialog.add(new JLabel("Nutze dazu folgendes Format:"));
		inputDialog.add(new JLabel(
				"4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff"));
		inputDialog.add(new JLabel("Beispiel: A1-G5-F3-A8"));
		String manualPosString = JOptionPane.showInputDialog(inputDialog);

		if (manualPosString != null) {
			boolean success = this.shipPosHandler.setShipPositionsManual(manualPosString, 4);

			if (!success) {
				this.showManualShipPositioning();
			}
		}
	}

	/**
	 * Shows a message that all ships were set new
	 */
	public void showShipsNewSet() {
	}

}
