package view.console;

import model.Player;

public interface GameHandler {

	public void initControl(Player player, Player enemy);

	public void startGame();

	public void showPlayersMatchfield();

	public void showEnemiesMatchfield();

	public boolean doMove(String input);

}
