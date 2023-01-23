package view.swing;

import model.Difficulty;

public interface MenuHandler {

	void initControl(FrameGUI gui);

	void setDifficulty(Difficulty difficulty);

	void chooseSinglePlayer();

}
