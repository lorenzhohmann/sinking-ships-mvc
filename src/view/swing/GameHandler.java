package view.swing;

import model.Player;

public interface GameHandler {

	void initControl(Player player, Player enemy, FrameGUI gui);

	void startGame();

	void showPlayersMatchfield();

	void showEnemiesMatchfield();

	boolean doMove(String input);

}
