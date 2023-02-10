package view.swing;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class FieldMouseAdapter extends MouseAdapter {

	private final Component panel;
	private final GameHandler gameHandler;

	public FieldMouseAdapter(Component panel, GameHandler gameHandler) {
		super();

		this.panel = panel;
		this.gameHandler = gameHandler;
	}

	@Override
	public void mouseClicked(MouseEvent evt) {
		JPanel panel = (JPanel) this.panel;
		String alphIndex = panel.getName();
		this.gameHandler.doMove(alphIndex);
	}
}
