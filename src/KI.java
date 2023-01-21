import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
/**
 * This class is a subclass of the Playerclass.
 * It simulates an AI-Player, with different difficulties.
 * 
 * @author Max Wenzel
 *
 */
public class KI extends Player 
{	
	/**
	 * Represents the curent difficulty of the AI
	 */
	private Difficulty difficulty;	
	
	/**
	 * Calls the contstructor of the parent class Player
	 */
	public KI() 
	{
		super();
	}
	
	/**
	 * Setter of the difficulty parameter
	 * @param difficulty the new difficulty state
	 */
	public void setDifficulty(Difficulty difficulty)  
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * Getter of the difficulty
	 * @return difficulty
	 */
	public Difficulty getDifficulty()  
	{
		return difficulty;
	}
	
	/**
	 * Determine one coordinate from the passed Matchfield, that should be returned.
	 * there are three different difficultys
	 * EASY: just a random coordinate from the Matchfield
	 * HARD: shoots systematic at every second coordinate and checks the coordinates at the top, bottom, left and right if the last shot was an hit
	 * EXTREM: knows the position of the ships and shoot with a chance of 70%-80% on one of these
	 * 
	 * @param field the Matchfield of the human Player
	 * @return the coordinate to shoot from AI-Player
	 */
	public Coordinate getKICoordinate(Matchfield field)  
	{
		
		Coordinate choosenCoordinate = null;
		ArrayList<Coordinate> coordinatesWithoutHits = this.getCoordinatesWithoutHits(field.getCoordinates());
		if(coordinatesWithoutHits.size() == 0) return null;
		Random random = new Random();
		int randomCoordinateIndex = random.nextInt(coordinatesWithoutHits.size());
		
		switch(this.difficulty) 
		{
		case EASY:
			choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
			break;
		case HARD:
			
			Coordinate top;
			Coordinate right;
			Coordinate bottom;
			Coordinate left;

			// check if last hit is still possible?
			if(field.getLastHit() != null) {
				
				top = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(), field.getLastHit().getY() - 1);
				right = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() + 1, field.getLastHit().getY());
				bottom = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(), field.getLastHit().getY() + 1);
				left = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() - 1, field.getLastHit().getY());
				
				// if all fields nearby already hit
				if((top == null || top.hasHit())
						&& (right == null || right.hasHit())
						&& (bottom == null || bottom.hasHit())
						&& (left == null || left.hasHit())) {
					
						field.setLastHit(null);
				}
				
			}
			
			if(field.getLastHit() != null) {
				
				top = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(), field.getLastHit().getY() - 1);
				right = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() + 1, field.getLastHit().getY());
				bottom = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX(), field.getLastHit().getY() + 1);
				left = this.findCoordinate(coordinatesWithoutHits, field.getLastHit().getX() - 1, field.getLastHit().getY());				
				
				// check nearby fields of last shot or hit position	
				boolean hasMatch = false;
				int tries = 0;	// additional termination condition
				while(!hasMatch && tries < 20) 
				{
				
					int direction = random.nextInt(4);
					
					switch(direction) 
					{
					case 0:	// top
						if(top != null) 
						{
							choosenCoordinate = top;
							hasMatch = true;
						}
						break;
					case 1:	// right
						if(right != null) 
						{
							choosenCoordinate = right;
							hasMatch = true;
						}
						break;
					case 2:	// bottom
						if(bottom != null) 
						{
							choosenCoordinate = bottom;
							hasMatch = true;
						}
						break;
					case 3:	// left
						if(left != null) 
						{
							choosenCoordinate = left;
							hasMatch = true;
						}
						break;
					default:	// fallback => shot on random coordinate:
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
				for(Coordinate coordinate : coordinatesWithoutHits) 
				{
					if(coordinate.getX() % 2 == coordinate.getY() % 2) 
					{
						choosenCoordinate = coordinate;
						break;
					}
				}
				
				// if no match => get random
				if(choosenCoordinate == null) {
					choosenCoordinate = coordinatesWithoutHits.get(randomCoordinateIndex);
				}
				
			}
			
			break;
		case EXTREM:

			// add all coordinates with ships
			ArrayList<Coordinate> coordinatesWithShips = new ArrayList<Coordinate>();
			for(Coordinate coordinate : coordinatesWithoutHits) {
				if(coordinate.hasShip()) coordinatesWithShips.add(coordinate);
			}
			
			Collections.shuffle(coordinatesWithoutHits);
			for(int i = 0; i < coordinatesWithoutHits.size(); i++) {
				if(i >= coordinatesWithShips.size()/4) break;
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
	
	/**
	 * Iterates the passed coordinates and compares the x and y values
	 * @param coordinates the list of all coordinates
	 * @param x the value of x to find
	 * @param y the value of y to find
	 * @return the matching coordinate, if there is no match => return null
	 */
	public Coordinate findCoordinate(ArrayList<Coordinate> coordinates, int x, int y)
	{
		for(int i = 0; i < coordinates.size(); i ++)
		{
			if(coordinates.get(i).getX() == x && coordinates.get(i).getY() == y)
			{
				return coordinates.get(i);
			}
		}
		return null;
	}	
	
	/**
	 * Iterates a list of passed coordinates to search for the coordinates without a hit
	 * @param coordinates the list of all coordinates
	 * @return all coordinates without hits
	 */
	public ArrayList<Coordinate> getCoordinatesWithoutHits(ArrayList<Coordinate> coordinates) 
	{
		ArrayList<Coordinate> coordinatesWithoutHits = new ArrayList<Coordinate>();
		for(Coordinate coordinate : coordinates) 
		{
			if(!coordinate.hasHit()) 
			{
				coordinatesWithoutHits.add(coordinate);
			}
		}
		
		return coordinatesWithoutHits;
	}
	
}