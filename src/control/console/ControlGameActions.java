package control.console;

import java.util.List;

import model.Coordinate;
import model.Matchfield;

public class ControlGameActions {

	/**
	 * Does a shoot on a specific coordinates and evaluates the shot
	 * 
	 * @param matchfield - Matchfield on which the shot should be performed. Null
	 *                   throws Exception.
	 * @param coordinate - Coordinate that should be shot. Null throws Exception.
	 * @return whether the shot was a ship hit
	 */
	protected boolean shoot(Matchfield matchfield, Coordinate coordinate) {
		coordinate.setHit(true);
		matchfield.setLastShot(coordinate);

		// set hit to last successful hit
		if (coordinate.isHasShip()) {
			matchfield.setLastHit(coordinate);
		}

		matchfield.setLastShotHit(coordinate.isHasShip());
		return matchfield.isLastShotHit();
	}

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
