package view;

import model.KI;
import model.Player;
import model.Status;

public interface GameHandler {

	public void initControl(Player player, KI enemy, Status status);

	public void startGame();

	public void showPlayersMatchfield();

	public void showEnemiesMatchfield();

	public boolean doMove(String input);

}
