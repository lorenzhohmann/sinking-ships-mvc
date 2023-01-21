package model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import view.Difficulty;

public class KI extends Player {

	private Difficulty difficulty;

	public KI() {
		super();
	}

	public void setDifficulty(Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public Difficulty getDifficulty() {
		return difficulty;
	}

	public Coordinate getKICoordinate(Matchfield field) {

		Coordinate choosenCoordinate = null;
		ArrayList<Coordinate> coordinatesWithoutHits = this.getCoordinatesWithoutHits(field.getCoordinates());
		if (coordinatesWithoutHits.size() == 0)
			return null;
		Random random = new Random();
		int randomCoordinateIndex = random.nextInt(coordinatesWithoutHits.size());

		switch (this.difficulty) {
		case EASY:
			choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
			break;
		case HARD:

			Coordinate top;
			Coordinate right;
			Coordinate bottom;
			Coordinate left;

			// check if last hit is still possible?
			if (field.getLastHit() != null) {

				top = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(),
						field.getLastHit().getY() - 1);
				right = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() + 1,
						field.getLastHit().getY());
				bottom = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(),
						field.getLastHit().getY() + 1);
				left = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() - 1,
						field.getLastHit().getY());

				// if all fields nearby already hit
				if ((top == null || top.hasHit()) && (right == null || right.hasHit())
						&& (bottom == null || bottom.hasHit()) && (left == null || left.hasHit())) {

					field.setLastHit(null);
				}

			}

			if (field.getLastHit() != null) {

				top = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(),
						field.getLastHit().getY() - 1);
				right = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() + 1,
						field.getLastHit().getY());
				bottom = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(),
						field.getLastHit().getY() + 1);
				left = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() - 1,
						field.getLastHit().getY());

				// check nearby fields of last shot or hit position
				boolean hasMatch = false;
				int tries = 0; // additional termination condition
				while (!hasMatch && tries < 20) {

					int direction = random.nextInt(4);

					switch (direction) {
					case 0: // top
						if (top != null) {
							choosenCoordinate = top;
							hasMatch = true;
						}
						break;
					case 1: // right
						if (right != null) {
							choosenCoordinate = right;
							hasMatch = true;
						}
						break;
					case 2: // bottom
						if (bottom != null) {
							choosenCoordinate = bottom;
							hasMatch = true;
						}
						break;
					case 3: // left
						if (left != null) {
							choosenCoordinate = left;
							hasMatch = true;
						}
						break;
					default: // fallback => shot on random coordinate:
						choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
						hasMatch = true;
						break;
					}

					tries++;
				}

			} else { // search new coordinate with even x and y position

				// mix list randomly
				Collections.shuffle(coordinatesWithoutHits);

				// get even coordinate
				for (Coordinate coordinate : coordinatesWithoutHits) {
					if (coordinate.getX() % 2 == coordinate.getY() % 2) {
						choosenCoordinate = coordinate;
						break;
					}
				}

				// if no match => get random
				if (choosenCoordinate == null) {
					choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
				}

			}

			break;
		case EXTREM:

			// add all coordinates with ships
			ArrayList<Coordinate> coordinatesWithShips = new ArrayList<Coordinate>();
			for (Coordinate coordinate : coordinatesWithoutHits) {
				if (coordinate.hasShip())
					coordinatesWithShips.add(coordinate);
			}

			Collections.shuffle(coordinatesWithoutHits);
			for (int i = 0; i < coordinatesWithoutHits.size(); i++) {
				if (i >= coordinatesWithShips.size() / 4)
					break;
				coordinatesWithShips.add(coordinatesWithoutHits.get(i));
			}

			Collections.shuffle(coordinatesWithShips);
			int randomCoordinateWithShipIndex = random.nextInt(coordinatesWithShips.size());
			choosenCoordinate = coordinatesWithShips.get(randomCoordinateWithShipIndex);

			break;
		default:
			break;
		}

		return choosenCoordinate;
	}

	public Coordinate findCoordinate(ArrayList<Coordinate> coordinates, int x, int y) {
		for (int i = 0; i < coordinates.size(); i++) {
			if (coordinates.get(i).getX() == x && coordinates.get(i).getY() == y) {
				return coordinates.get(i);
			}
		}
		return null;
	}

	public ArrayList<Coordinate> getCoordinatesWithoutHits(ArrayList<Coordinate> coordinates) {
		ArrayList<Coordinate> coordinatesWithoutHits = new ArrayList<Coordinate>();
		for (Coordinate coordinate : coordinates) {
			if (!coordinate.hasHit()) {
				coordinatesWithoutHits.add(coordinate);
			}
		}

		return coordinatesWithoutHits;
	}

}