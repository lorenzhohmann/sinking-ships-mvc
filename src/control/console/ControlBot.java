package control.console;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import model.Coordinate;
import model.Difficulty;
import model.Matchfield;

public class ControlBot {

	/**
	 * Main AI logic. Chooses a coordinate for the next hit depending the difficulty
	 * 
	 * @param matchfield - the matchfield from where the coordinate should be chosen
	 *                   at
	 * @param difficulty - the difficulty of the AI
	 * @return a coordinate that was chosen by the AI
	 */
	protected static Coordinate chooseCoordinateByDifficulty(Matchfield matchfield, Difficulty difficulty) {
		Coordinate chosenCoordinate = null;

		List<Coordinate> coordsWithoutHit = matchfield.getCoordinatesWithoutHits();
		if (!coordsWithoutHit.isEmpty()) {

			switch (difficulty) {
			case EASY:
				chosenCoordinate = ControlBot.getEasyAICoordinate(coordsWithoutHit);
				break;
			case HARD:
				chosenCoordinate = ControlBot.getHardAICoordinate(matchfield, coordsWithoutHit);
				break;
			case EXTREM:
				chosenCoordinate = ControlBot.getExtremAICoordinate(coordsWithoutHit);
				break;
			}
		}

		System.out.println(chosenCoordinate.getX() + ":" + chosenCoordinate.getY());

		return chosenCoordinate;
	}

	/**
	 * AI chooses a random coordinate from all coordinates with a ship on it
	 * 
	 * @param coordWithoutHit - all coordinates that have no hits
	 * @return the coordinate chosen by the AI
	 */
	protected static Coordinate getExtremAICoordinate(List<Coordinate> coordWithoutHit) {
		Random random = new Random();
		Coordinate choosenCoordinate;

		// add all coordinates with ships
		ArrayList<Coordinate> coordsWithShips = new ArrayList<>();
		for (Coordinate coordinate : coordWithoutHit) {
			if (coordinate.isHasShip()) {
				coordsWithShips.add(coordinate);
			}
		}

		Collections.shuffle(coordWithoutHit);
		for (int i = 0; i < coordWithoutHit.size(); i++) {
			if (i >= coordsWithShips.size() / 4) {
				break;
			}
			coordsWithShips.add(coordWithoutHit.get(i));
		}

		Collections.shuffle(coordsWithShips);
		int randomCoordIndex = random.nextInt(coordsWithShips.size());
		choosenCoordinate = coordsWithShips.get(randomCoordIndex);
		return choosenCoordinate;
	}

	/**
	 * Chooses a coordinate depending the last shot. If a ship was hit on last shot
	 * it tries to find the ship part positions next to the last successful shot
	 * 
	 * @param matchfield       - where the coordinate should be get from
	 * @param coordsWithoutHit - all coordinates that have no hits
	 * @return the coordinate chosen by the AI
	 */
	protected static Coordinate getHardAICoordinate(Matchfield matchfield, List<Coordinate> coordsWithoutHit) {

		Coordinate chosenCoordinate = null;
		Random random = new Random();
		int randCoordIndex = random.nextInt(coordsWithoutHit.size());

		Coordinate top;
		Coordinate right;
		Coordinate bottom;
		Coordinate left;

		// check if last hit is still possible?
		ControlBot.checkAILastHitIsPossible(matchfield);

		if (matchfield.getLastHit() != null) {

			int lastHitX = matchfield.getLastHit().getX();
			int lastHitY = matchfield.getLastHit().getY();

			top = matchfield.getCoordinate(lastHitX, lastHitY - 1);
			right = matchfield.getCoordinate(lastHitX + 1, lastHitY);
			bottom = matchfield.getCoordinate(lastHitX, lastHitY + 1);
			left = matchfield.getCoordinate(lastHitX - 1, lastHitY);

			// check nearby fields of last shot or hit position
			boolean hasMatch = false;
			int tries = 0; // additional termination condition
			while (!hasMatch && tries < 20) {

				int direction = random.nextInt(4);

				switch (direction) {
				case 0: // top
					if (top != null) {
						chosenCoordinate = top;
						hasMatch = true;
					}
					break;
				case 1: // right
					if (right != null) {
						chosenCoordinate = right;
						hasMatch = true;
					}
					break;
				case 2: // bottom
					if (bottom != null) {
						chosenCoordinate = bottom;
						hasMatch = true;
					}
					break;
				case 3: // left
					if (left != null) {
						chosenCoordinate = left;
						hasMatch = true;
					}
					break;
				default: // fallback => shot on random coordinate:
					chosenCoordinate = coordsWithoutHit.get(randCoordIndex);
					hasMatch = true;
					break;
				}

				tries++;
			}

		} else { // search new coordinate with even x and y position

			// mix list randomly
			Collections.shuffle(coordsWithoutHit);

			// get even coordinate
			for (Coordinate coordinate : coordsWithoutHit) {
				if (coordinate.getX() % 2 == coordinate.getY() % 2) {
					chosenCoordinate = coordinate;
					break;
				}
			}

			// if no match => get random
			if (chosenCoordinate == null) {
				chosenCoordinate = coordsWithoutHit.get(randCoordIndex);
			}

		}
		return chosenCoordinate;
	}

	private static void checkAILastHitIsPossible(Matchfield matchfield) {
		Coordinate top;
		Coordinate right;
		Coordinate bottom;
		Coordinate left;
		if (matchfield.getLastHit() != null) {

			int lastHitX = matchfield.getLastHit().getX();
			int lastHitY = matchfield.getLastHit().getY();

			top = matchfield.getCoordinate(lastHitX, lastHitY - 1);
			right = matchfield.getCoordinate(lastHitX + 1, lastHitY);
			bottom = matchfield.getCoordinate(lastHitX, lastHitY + 1);
			left = matchfield.getCoordinate(lastHitX - 1, lastHitY);

			// if all fields nearby already hit
			if ((top == null || top.hasHit()) && (right == null || right.hasHit())
					&& (bottom == null || bottom.hasHit()) && (left == null || left.hasHit())) {
				matchfield.setLastHit(null);
			}

		}
	}

	/**
	 * Returns the easy AI coordinate by collecting a random coordinate from all
	 * available coordinates.
	 * 
	 * @param coordsWithoutHit - all coordinates that have no hits
	 * @return the coordinate chosen by the AI
	 */
	protected static Coordinate getEasyAICoordinate(List<Coordinate> coordsWithoutHit) {
		Random random = new Random();
		int randCoordIndex = random.nextInt(coordsWithoutHit.size());
		return coordsWithoutHit.get(randCoordIndex);
	}

}
