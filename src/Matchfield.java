import java.io.Serializable;
import java.util.ArrayList;

/**
 * This class implements the structure for generating the output of the Matchfield. The main function is to generate and Format 
 * the Design of the Matchfield and also to place the Ships automatically or manual, so that the Player/User can decide, how the 
 * own Ships are placed. The Class also implements the function to show the right symbols for the action of the Player on the 
 * generated Matchfield. Furthermore, the Class checks if the Game is over or not by counting the Coordinates which were hit.
 * 
 * @author Jonathan Schlitt, Khryssaint Dietz
 * 
 */
public class Matchfield implements Serializable {
	
	/**
	 * Implemented for serializable (data stream for multiplayer).
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * The size of the Matchfield.
	 */
	private int fieldsize;
	
	/**
	 * The ArrayList of the Coordinates.
	 */
	private ArrayList<Coordinate> coordinates;
	
	/**
	 * The players last shot as Coordinate.
	 */
	private Coordinate lastShot;
	
	/**
	 * The players last hit as Coordinate.
	 */
	private Coordinate lastHit;
	
	/**
	 * The alphabet for the matchfield layout.
	 */
	private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	/**
	 * The ship index for a specific coordinate.
	 */
	private int shipIndexCounter;
	
	
	/**
	 * The Constructor sets the height and the width of the Matchfield, initialises an ArrayList and also sets the LastShot
	 * and LastHit Variables to null. It also calls the the Mathod fillCoordinates to fill the Array with all Coordinates for the
	 * actual fieldsize.
	 */
	public Matchfield (){
		this.fieldsize = 10;
		this.coordinates = new ArrayList<Coordinate>();
		this.lastShot = null;
		this.lastHit = null;
		this.shipIndexCounter = 1;

		this.fillCoordinates();
	}
	
	//Getter Mothods for JUnit test cases
	public String getAlphabet() {
		return this.alphabet;
	}
	
	public int getShipIndexCounter() {
		return this.shipIndexCounter;
	}
	
	/**
	 * This method returns the current fieldsize.
	 * @return fieldsize of the game
	 */
	public int getFieldsize() {
		return fieldsize;
	}

	/**
	 * Setter of the fieldsize.
	 * @param fieldsize of the game
	 */
	public void setFieldsize(int fieldsize) {
		this.fieldsize = fieldsize;
	}

	/**
	 * The getter method for our ArrayList.
	 * @return the list of all coordinates
	 */
	public ArrayList<Coordinate> getCoordinates() {
		return coordinates;
	}

	/**
	 * Setter method for the ArrayList
	 * @param coordinates to set
	 */
	public void setCoordinates(ArrayList<Coordinate> coordinates) {
		this.coordinates = coordinates;
	}
	
	/**
	 * The matchfield is created out of two Strings. One for the alphabet at the top of the matchfield and one for the
	 * numbers of the Y-coordinate including the strokes of the frames and numbers counting up in the middle of each field
	 * to generate coordinates and placeholder for symbols.
	 * 
	 * @param showShips 
	 */
	public void show(boolean showShips) {
		
		//Function for writing the matchfield on the console
		int a = this.fieldsize; //X-coordinate
		int b = this.fieldsize;  //Y-coordinate
		
		String rand = "   ";// distance from the left edge to the top row of a field
		String x = "     "; // distance from the left edge to A
		String y = "";		// string for the numbers of the Y-coordinates
		
		
		String [] [] status = new String [this.fieldsize] [this.fieldsize];	// Test für Feld mit der Größe 3x
		for(int i = 0; i< status.length; i++) {
			for (int j = 0; j < status.length; j++) {
				status [i][j] = " ";
			}
		}
		//Game Logic for Output of certain Characters
		if(showShips) {
			for (Coordinate c : coordinates) {
				if(c.hasShip()) {
					if(c.hasHit()) {
						if(this.isShipDown(c)) {
							status[c.getX()][c.getY()] = "X";
						} else {
							status[c.getX()][c.getY()] = "x";
						}
					} else {
						status[c.getX()][c.getY()] = "o";
					}
				} else {
					if(c.hasHit()) {
						status[c.getX()][c.getY()] = "~";
					}
				}
			}
		}else {
			for (Coordinate c : coordinates) {
				if(c.hasHit()) {
					if(c.hasShip()) {
						if(this.isShipDown(c)) {
							status[c.getX()][c.getY()] = "X";
						} else {
							status[c.getX()][c.getY()] = "x";
						}
					} else {
						status[c.getX()][c.getY()] = "~";
					}
				}
			}
		}

		
		//Formating the Output as String
		for (int i = 0; i < a; i++) { //alphabet of the X-coordinates
			x = x + " " + alphabet.charAt(i) +  "    " ;
		}
		System.out.println(x);
		
		
		for (int i = 0; i < b; i++) { //sting to generate the numbers of the Y-coordinates
			y = y + Integer.toString(i);
		}
		
		for (int i = 0; i < a; i++) { //top row
			rand = rand + " _____";
		}
		rand = rand + "\n";
		
		
		for (int i = 0; i < b; i++) {
			rand = rand + "   ";									// structure of the fields
																	//   _____   --> top row
			for (int j = 0; j <a; j++) { //  1/3 of a field			// 	|     |  --> 1/3
																	//  |  X  |  --> 2/3  X --> symbol if hit,...
				rand = rand + "|     ";								// 	|_____|  --> 3/3
			}
			
			rand = rand + "|" + "\n";
			
			
			if (i+1 < 10) {
			rand = rand + (i+1) + "  "; // imports the numbers of the Y-coordinate into the String of the matchfield
			}
			else {
				rand = rand + (i+1) + " "; // imports the numbers of the Y-coordinate into the String of the matchfield
			}

			
			for (int j = 0; j <a; j++) {    //2/3 of a field
				rand = rand + "|  " + status[i] [j] +  "  ";		// symbol if a ship was hit,...
			}
			rand = rand + "|" + "\n" + "   ";
			
			
			for (int j = 0; j <a; j++) {	//3/3 of a field
				rand = rand + "|_____";
			}
			rand = rand + "|" +  "\n";		//new line
		}
		
		System.out.println(rand);
	}
	
	/**
	 * This method generates random values for the current size of the matchfield as integer value.
	 * @return the random value as integer for both of the RandomShip methods.
	 */
	public int randomize() {
		double random = 0; 
		random = Math.random()*100;
		if(random > this.fieldsize*this.fieldsize) {
			random = Math.random()*100;
		}
		return (int) random;
	}
	
	/**
	 * This method implements the function to remove all the ships fron the Matchfield. For this action, there is a 
	 * foreach loop, which iterates through the whole ArrayList with the coordinates.
	 */
	public void resetShips() {
		this.shipIndexCounter = 1;
		
		for(Coordinate c : this.coordinates) {
			c.setHasShip(false);
		}
	}
	
	/**
	 * This method places 2 ships vertically in the matchfield. For this action, we create 4 Coordinate Objects, which represent the
	 * coordinates where the ship is placed. Then we call the randomize function, to get a new random value for the postion of the
	 * current ship. After that, we call the getter method of the ArrayList to get the List Object at the random index. If the
	 * object is not null and there was no ship placed before, the hasShip value of the coordinate will be set to true for all
	 * the 4 coordinates which represent the ship. We also check, that the ships will not be placed from one row to the next.
	 */
	public void placeRandomShipsVertical() {
		int count = 0;
		while(count <2) {
			int random = randomize();
			Coordinate c1 = null;
			Coordinate c2 = null;
			Coordinate c3 = null;
			Coordinate c4 = null;
			try {
				c1 = coordinates.get(random);
				c2 = coordinates.get(random+10);
				c3 = coordinates.get(random+20);
				c4 = coordinates.get(random+30);
			}catch(IndexOutOfBoundsException e) {
				random = randomize();
				continue;
			}
			
			if( c1!=null && !c1.hasShip() && c2!=null && !c2.hasShip() && c3!=null && !c3.hasShip() && c4!=null && !c4.hasShip()) {
				c1.setHasShip(true);
				c2.setHasShip(true);
				c3.setHasShip(true);
				c4.setHasShip(true);
				
				c1.setShipIndex(this.shipIndexCounter);
				c2.setShipIndex(this.shipIndexCounter);
				c3.setShipIndex(this.shipIndexCounter);
				c4.setShipIndex(this.shipIndexCounter);
				this.shipIndexCounter++;
				
				count++;
				
			}else {
				random = randomize();
			}
		}
	}
	
	
	/**
	 * This method places 2 ships horizontally in the matchfield. For this action, we create 4 Coordinate Objects, which represent the
	 * coordinates where the ship is placed. Then we call the randomize function, to get a new random value for the postion of the
	 * current ship. After that, we call the getter method of the ArrayList to get the List Object at the random index. If the
	 * object is not null and there was no ship placed before, the hasShip value of the coordinate will be set to true for all
	 * the 4 coordinates which represent the ship. We also check, that the ships will not be placed from one row to the next.
	 */
	public void placeRandomShipsHorizontal() {
		int count = 0;
		while(count <2) {
			int random = randomize();
			if(random % this.fieldsize >= 0 && random % this.fieldsize != this.fieldsize-3 
				&& random % this.fieldsize != this.fieldsize-2 && random % this.fieldsize != this.fieldsize-1
				&& random+4 <= 100 && coordinates.get(random).hasShip()==false 
				&& coordinates.get(random+1).hasShip()==false && coordinates.get(random+2).hasShip()==false
				&& coordinates.get(random+3).hasShip()==false) {
				
					Coordinate c1 = coordinates.get(random);
					Coordinate c2 = coordinates.get(random+1);
					Coordinate c3 = coordinates.get(random+2);
					Coordinate c4 = coordinates.get(random+3);
					
					c1.setHasShip(true);
					c2.setHasShip(true);
					c3.setHasShip(true);
					c4.setHasShip(true);	
					
					c1.setShipIndex(this.shipIndexCounter);
					c2.setShipIndex(this.shipIndexCounter);
					c3.setShipIndex(this.shipIndexCounter);
					c4.setShipIndex(this.shipIndexCounter);
					this.shipIndexCounter++;
					
					count++;
			}else {
				random = randomize();
			}
		}
		
	}
	
	/**
	 * This method calls both RandomShip Methods, so that the ships are placed vertically and horizontally.
	 */
	public void placeRandomShips() {
		placeRandomShipsHorizontal();
		placeRandomShipsVertical();
	}
	
	/**
	 * This method gets the coordinates of the player. It changes the boolean setHasShip of the chosen field to true and
	 * continues with the next fields till the size of the ship is reached. to make sure that the ship doesn't cross
	 * the matchfield the if-loop includes needed conditions.
	 * @param y is the Y-coordinate of the matchfield
	 * @param x is the X-coordinate of the matchfield
	 * @return it returns a true if it was possible to place a ship and a false if the coordinates were already used
	 * or the ship would be outside the matchfield.
	 */
	public boolean placeShipsVertical(int y, int x) {
		y=y*this.fieldsize;
		int z=y+x;

		Coordinate c1 = null;
		Coordinate c2 = null;
		Coordinate c3 = null;
		Coordinate c4 = null;
		try {
			c1 = coordinates.get(z);
			c2 = coordinates.get(z+10);
			c3 = coordinates.get(z+20);
			c4 = coordinates.get(z+30);
		}catch(IndexOutOfBoundsException e) {
			return false;
		}
		
		if( c1!=null && !c1.hasShip() && c2!=null && !c2.hasShip() && c3!=null && !c3.hasShip() && c4!=null && !c4.hasShip()) {
			c1.setHasShip(true);
			c2.setHasShip(true);
			c3.setHasShip(true);
			c4.setHasShip(true);
			
			c1.setShipIndex(this.shipIndexCounter);
			c2.setShipIndex(this.shipIndexCounter);
			c3.setShipIndex(this.shipIndexCounter);
			c4.setShipIndex(this.shipIndexCounter);
			this.shipIndexCounter++;
			return true;
		}
		return false;
	}
	
	/** 
	 * This method gets the coordinates of the player. It changes the boolean setHasShip of the chosen field to true and
	 * continues with the fields under till the size of the ship is reached. to make sure that the ship doesn't cross
	 * the matchfield the if-loop includes needed conditions.
	 * @param y is the Y-coordinate of the matchfield
	 * @param x is the X-coordinate of the matchfield
	 * @return it returns a true if it was possible to place a ship and a false if the coordinates were already used
	 * or the ship would be outside the matchfield.
	 */
	public boolean placeShipsHorizontal(int y, int x) {
		y=y*10;
		boolean set = false;
		
		int z=y+x;
		if(z % this.fieldsize >= 0 && z % this.fieldsize != this.fieldsize-3 
			&& z % this.fieldsize != this.fieldsize-2 && z % this.fieldsize != this.fieldsize-1
			&& z+4 <= 100 && coordinates.get(z).hasShip()==false 
			&& coordinates.get(z+1).hasShip()==false && coordinates.get(z+2).hasShip()==false
			&& coordinates.get(z+3).hasShip()==false) {
			
			Coordinate c1 = coordinates.get(z);
			Coordinate c2 = coordinates.get(z+1);
			Coordinate c3 = coordinates.get(z+2);
			Coordinate c4 = coordinates.get(z+3);
			
			c1.setHasShip(true);
			c2.setHasShip(true);
			c3.setHasShip(true);
			c4.setHasShip(true);
			
			c1.setShipIndex(this.shipIndexCounter);
			c2.setShipIndex(this.shipIndexCounter);
			c3.setShipIndex(this.shipIndexCounter);
			c4.setShipIndex(this.shipIndexCounter);
			this.shipIndexCounter++;
	
			set = true;
		} else {
			set = false;
		}
		return set;
	}
	
	/**
	 * This method calls out the methods to place the ships by coordinates horizontal or vertical.
	 * @param x is the X-coordinate
	 * @param y is the Y-coordinate
	 * @param vertical shows if the ship would be placed vertical (true) or horizontal (false).
	 * @return it returns a true if it was possible to place a ship and a false if the coordinates were already used
	 * or the ship would be outside the matchfield.
	 */
	public boolean placeShips(int x, int y, boolean vertical) {
		boolean set = false;
			
		if (vertical) {
			set=placeShipsVertical(x,y);
		} else {
			set=placeShipsHorizontal(x,y);
		}
		
		return set;
	}
	
	/**
	 * This method counts all positions and all ships that are hitted.
	 * These values are compared and result in the return value.
	 * @return if a boolean of the matchfield is game over
	 */
	public boolean isGameOver() {
		int shipPositionCounter = 0;
		int totalHittedShipsCounter = 0;
		
		for(Coordinate coordinate : this.coordinates) {
			if(coordinate.hasShip()) {
				shipPositionCounter++;
				
				if(coordinate.hasHit()) {
					totalHittedShipsCounter++;
				}
			}
		}
		return shipPositionCounter == totalHittedShipsCounter;	
	}
	
	/**
	 * This method sets the hit variable of the passed coordinate to true and sets the lastShot variable and if necessary the lastHit variable to true.
	 * @param coordinate that should be shoot at.
	 * @return a boolean if the passed coordinate has a ship
	 */
	public boolean shoot(Coordinate coordinate) {
		coordinate.setHit(true);
		this.lastShot = coordinate;
		
		// set hit to last successfull hit
		if(coordinate.hasShip()) this.lastHit = coordinate;
		
		return coordinate.hasShip();
	}
	
	/**
	 * This method determines the coordinate from the the coordinates array by a input string (e.g. A3)
	 * For that it calculates the index of the coordinate in the coordinates array.
	 * @param coordinate string (e.g. A3)
	 * @return the matching coordinate
	 */
	public Coordinate getCoordinateByString(String coordinate) {
		String[] split = coordinate.split("");
		
		int alphabetIndex = this.alphabet.indexOf(split[0].toUpperCase());
		
		int numberIndex = 0;
		if(split.length == 2) {
			numberIndex = 10 * (Integer.parseInt(split[1])-1);
		} else if(split.length == 3) {
			numberIndex = 10 * (Integer.parseInt(split[1] + split[2])-1);
		}
		int chosenCoordinateIndex = numberIndex + alphabetIndex;
		
		return this.coordinates.get(chosenCoordinateIndex);
	}
	
	/**
	 * This method reset all ship positions and sets the ships to passed custom coordinates.
	 * For this there is a loop over the split variables. With each run, the system checks whether they correspond to the normal form and sets them.
	 * The first two ships are positioned vertically, all other ships are positioned horizontally.
	 *
	 * @param manualPositioning a string (e.g. A3) where the ship should start at
	 * @param maxShips the maximum number of coordinates that should be positioned
	 * @return a boolean if the positioning was successful or not
	 */
	public boolean positionShipsByString(String manualPositioning, int maxShips) {
		String[] manualPositions = manualPositioning.split("-");
		
		// clear matchfield
		this.resetShips();
		
		// get coordinates by string and place ships
		int shipCounter = 1;
		for(String position : manualPositions) {
			
			if(shipCounter > maxShips) break;
			
			if(position.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
			
				Coordinate choosenCoordinate = this.getCoordinateByString(position);
				
				// horizontal ships for default
				boolean vertical = true;
				
				// vertical ships
				if(shipCounter >= 3) vertical = false;
				
				// place ships by choosen coordinate
				boolean success = this.placeShips(choosenCoordinate.getX(), choosenCoordinate.getY(), vertical);
				
				// error handling
				if(!success) return false;
				
				shipCounter++;
				continue;
			}
			
			return false;
		}
		
		return true;
	}
	
	/**
	 * Getter of the lastShot variable
	 */
	public Coordinate getLastShot() {
		return lastShot;
	}
	
	/**
	 * Getter of the lastHit variable
	 */
	public Coordinate getLastHit() {
		return lastHit;
	}
	
	/**
	 * Setter of lastHit variable
	 * @param lastHit includes the information on with coordinates the last hit of a ship was
	 */
	public void setLastHit(Coordinate lastHit) {
		this.lastHit = lastHit;
	}
	
	/**
	 * This method fills the ArrayList with the number of Coordinates for all the positions in the Matchfield. In our case 10*10 fields.
	 * While iterating with the two for loopes, we add the coordinates to the ArrayList, set has ship values to false and add the x and y
	 * variables to the value of the actual iteration.
	 */
	private void fillCoordinates() {
		for(int x = 0; x < this.fieldsize; x++) {
			for(int y = 0; y < this.fieldsize; y++) {
				Coordinate coordinate = new Coordinate(x, y, false);
				this.coordinates.add(coordinate);
			}
		}
	}
	
	/**
	 * return the x and y values of the Coordinates.
	 * @param x coordinate
	 * @param y coordinate
	 * @return returns the coordinate.
	 */
	public Coordinate getCoordinateByXAndY(int x, int y) {
		for(Coordinate coordinate : this.coordinates) {
			if(coordinate.getX() == x && coordinate.getY() == y) {
				return coordinate;
			}
		}
		
		return null;
	}
	
	/**
	 * This function checks if the ship on the current coordinate is completely hitted.
	 * 
	 * @param oneCoordinateOfShip any coordinate of a ship
	 * @return a boolean if the ship is completly hitted, if not => return false
	 */
	public boolean isShipDown(Coordinate oneCoordinateOfShip) {
		
		int shipIndex = oneCoordinateOfShip.getShipIndex();
		int shipHitsWithSameIndex = 0;
		int shipsWithSameIndex = 0;
		
		for(Coordinate coordinate : this.coordinates) {
			if(coordinate.getShipIndex() == shipIndex) {
				shipsWithSameIndex++;
				
				if(coordinate.hasHit()) shipHitsWithSameIndex++;
			}
		}
		
		return shipsWithSameIndex == shipHitsWithSameIndex;
	}
	
	/**
	 * This method calculates the statistics of the players shots and hits and also the success rate. 
	 * It calls the showStatistics method and prints a table with the calculated values.
	 * @param screen prints out the table with the values for each player 
	 */
	public void calculateAndPrintStatistics(Screen screen) {
		int hit = 0;
		double statistic = 0.0;
		int totalHits = 0;
		for(Coordinate c: this.coordinates) {
			if(c.hasHit() && c.hasShip()) {
				totalHits++;
				hit++;
			}
			else if(c.hasHit()) {
				totalHits++;
			}

		}
		if(totalHits > 0) statistic = (100*hit/totalHits);
		screen.showStatistic(totalHits, hit, (totalHits-hit), statistic);
	}

}