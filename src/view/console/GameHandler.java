package view.console;

import model.Player;

public interface GameHandler {

	void initControl(Player player, Player enemy);

	void startGame();

	void showPlayersMatchfield();

	void showEnemiesMatchfield();

	boolean doMove(String input);

}
