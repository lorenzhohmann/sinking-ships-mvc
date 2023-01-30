package view.swing;

import model.Player;

public interface GameOverHandler {

	void initControl(boolean playerIsWinner, Player Enemy, FrameGUI gui);

}
