package view.swing;

import model.Difficulty;

public interface MenuHandler {

	void initControl();

	void setDifficulty(Difficulty difficulty);

	void chooseSinglePlayer();

}