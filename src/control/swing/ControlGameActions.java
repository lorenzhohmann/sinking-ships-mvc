package control.swing;

import java.util.List;

import model.Coordinate;
import model.Matchfield;

public class ControlGameActions {

	/**
	 * Checks if a game is over by checking if all ship positions were hit
	 * 
	 * @param matchfield - the matchfield that should be checked
	 * @return whether all ships on the matchfield were hit
	 */
	protected boolean isGameOver(Matchfield matchfield) {
		int shipPosCounter = 0;
		int totalShipsHit = 0;
		List<Coordinate> coordinates = matchfield.getCoordinates();

		for (Coordinate coordinate : coordinates) {
			if (coordinate.isHasShip()) {
				shipPosCounter++;

				if (coordinate.hasHit()) {
					totalShipsHit++;
				}
			}
		}
		return shipPosCounter == totalShipsHit;
	}

}
