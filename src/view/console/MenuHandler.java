package view.console;

import model.Difficulty;

public interface MenuHandler {

	void initControl();

	void setDifficulty(Difficulty difficulty);

	void chooseSinglePlayer();

}
