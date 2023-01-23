package view.console;

import model.Difficulty;

public interface MenuHandler {

	public void initControl();

	public void setDifficulty(Difficulty difficulty);

	public void chooseSinglePlayer();

}
