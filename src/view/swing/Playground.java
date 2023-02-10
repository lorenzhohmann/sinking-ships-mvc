package view.swing;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class Playground extends JPanel {

	/**
	 * Unique ID to identify version of the class for the Serializable interface
	 */
	private static final long serialVersionUID = 1L;

	private final FrameGUI gui;
	private final List<JPanel> fields;

	public Playground(FrameGUI gui) {
		super();

		this.gui = gui;
		this.fields = new ArrayList<>();

		this.setLayout(null); // absolute layout
		this.gui.setContentPane(this);
	}

	/**
	 * Shows a matchfield in the console
	 * 
	 * @param fieldsize - the size of the match
	 * @param status
	 */
	public void print(String[][] status) { // NOPMD (CognitiveComplexity) makes no sense to rewrite this method

		int width = status[0].length;
		int height = width;

		for (int x = 0; x <= width; x++) {
			for (int y = 0; y <= height; y++) {
				String naming;

				Border border = null;
				String panelName = "";

				// clear top left corner
				if (x == 0 && y == 0) {
					continue;
				}

				Color background;
				if (y == 0 || x == 0) {
					background = new Color(0f, 0f, 0f, 0.1f);
				} else {
					background = new Color(0, 0, 0, 0);
				}

				// horizontal naming
				if (y == 0) {
					naming = String.valueOf("ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(x - 1));
				} else if (x == 0) {// vertical naming
					naming = String.valueOf(y);
				} else {
					naming = status[x - 1][y - 1];
					border = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.GRAY);
					panelName = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(y - 1) + String.valueOf(x);
				}

				// create fields
				JLabel label = new JLabel(naming);
				label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				JPanel panel = new JPanel();
				panel.setName(panelName);
				panel.setBorder(border);
				panel.setBackground(background);
				panel.setBounds(x * 50, y * 50, 50, 50);
				panel.add(label);
				this.fields.add(panel);
				this.add(panel);
			}
		}

		this.gui.repaint();
	}

	public List<JPanel> getFields() {
		return fields;
	}

}
