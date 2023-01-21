import java.awt.Toolkit;
import java.io.IOException;
import java.util.Scanner;

/**
 * This class is the heart of the singleplayer game. It contains the main logic and procedure of the game.
 * It has some methods to show and handle the menu actions. There are also methods that control the course of the game,
 * from user input to the evaluation and display of the game results.
 * 
 * @author Lorenz Hohmann
 */
public class GameManager {
	
	/**
	 * The human Player instance
	 */
	private Player self;
	
	/**
	 * The AI Player instance
	 */
	private Player enemy;
	
	/**
	 * A Screen instance that can display messages to the player
	 */
	private Screen screen;
	
	/**
	 * The current state of the game (e.g. waiting, running, ...)
	 */
	private Status status;
	
	/**
	 * The Scanner object to handle user console inputs
	 */
	private Scanner scanner;
	
	/**
	 * A bool if it's the users turn or not
	 */
	private boolean ownTurn;
	
	/**
	 * Counts how many shots the human player or the AI player have in a row
	 */
	private int run;
	
	/**
	 * The constructor sets the default variables and creates the human player object.
	 * 
	 * @param screen a instance of the Screen class that is created in the main method
	 */
	public GameManager(Screen screen) {
		
		this.screen = screen;
		this.scanner = new Scanner(System.in);
		this.ownTurn = true;
		this.run = 1;
		
		this.self = new Player();
	}
	
	/**
	 * Initializes the game by changing the status and creating the enemy AI. Additionally the settings menu is called up.
	 */
	public void initGame() {
		
		this.status = Status.WAITING;
		
		this.enemy = new KI();
		this.goToSettings();
	}
	
	/**
	 * Displays the difficulty menu and evaluates the user input and sets the selected value.
	 * Therefor the program reads the users console input.
	 * An error is displayed if the entry is incorrect. At the end the menu for positioning the ships is called up.
	 */
	private void goToSettings() {
		
		this.screen.showSettings();
		String chosenDifficulty;
		
		while(this.scanner.hasNext()) {
			chosenDifficulty = this.scanner.next().toLowerCase();

			if(chosenDifficulty.equalsIgnoreCase("e")) {
				((KI) this.enemy).setDifficulty(Difficulty.EASY);
				break;
			} else if(chosenDifficulty.equalsIgnoreCase("s")) {
				((KI) this.enemy).setDifficulty(Difficulty.HARD);
				break;
			}else if(chosenDifficulty.equalsIgnoreCase("x")) {
				((KI) this.enemy).setDifficulty(Difficulty.EXTREM);
				break;
			}

			this.screen.print("Ungueltige Eingabe!", "error");
			this.screen.showSettings();
		}
		
		this.goToShipPositioning(false);
	}
	
	/**
	 * This method shows the player his matchfield, on which he has the possibility to position ships automatically or manually.
	 * Therefor the program reads the users console input.
	 * An error is displayed if the entry is incorrect. The game starts if the user select "s".
	 * 
	 * @param hasManualPosition defines it the ships should be set randomly or not (important after call from goToManualShipPositioning())
	 */
	public void goToShipPositioning(boolean hasManualPosition) {
		
		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}		
		
		if(!hasManualPosition) {	// if this function call does not come from manual positioning

			// update game status
			if(this.status != Status.WAITING) return;
			this.status = Status.WARMUP;
			
			// place enemys ships randomly
			this.enemy.getMatchfield().placeRandomShips();	

			// place players ships randomly
			this.self.getMatchfield().placeRandomShips();
		}
		
		// place players ships randomly
		this.self.getMatchfield().show(true);	
		
		// position of ships page
		this.screen.showShipPositioning();	
		
		
		while(this.scanner.hasNext()) {
			
			String input = this.scanner.next(); 
			
			if(input.equalsIgnoreCase("z")) {	// generate new random ship positions
				
				// clear console
				try {
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				} catch (InterruptedException | IOException e) {
				}
				this.screen.print("Deine Flotte wurde neu positioniert!");

				this.self.getMatchfield().resetShips();
				this.self.getMatchfield().placeRandomShips();
				this.self.getMatchfield().show(true);
				
				this.screen.showShipPositioning();
				continue;
			} else if(input.equalsIgnoreCase("m")) {
				this.goToManualShipPositioning();
				break;
				
			} else if(input.equalsIgnoreCase("s")) {	// start game
				this.startGame();
				break;
			}

			this.screen.print("Ungueltige Eingabe, waehle [S], [Z] oder [M]!", "error");
			this.screen.showShipPositioning();
		}
	}
	
	/**
	 * This method allows the player to position the ships manually.
	 * Therefor the user have to type different coordinates with a specific syntax.
	 */
	private void goToManualShipPositioning() {
		
		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}
		
		this.screen.print("Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
		this.screen.print("4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
		this.screen.print("Beispiel: A1-G5-F3-A8");
		
		while(scanner.hasNext()) {
			String manualPositioningString = this.scanner.next();
			
			boolean success = this.self.getMatchfield().positionShipsByString(manualPositioningString, 4);
			
			if(!success) {
				this.screen.print("Die Positionierung kann so nicht durchgefuehrt werden!", "error");
				
				this.screen.print("Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
				this.screen.print("4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
				this.screen.print("Beispiel: A1-G5-F3-A8");
				continue;
			}
			
			break;
		}
		
		this.goToShipPositioning(true);		
	}
	
	/**
	 * This method changes the state of the game and set both players (human and AI) to ready. Then the first round starts.
	 */
	private void startGame() {
		
		if(this.status != Status.WARMUP) return;
		this.status = Status.RUNNING;
		
		this.self.setReady(true);
		this.enemy.setReady(true);
		
		// start first game round
		this.nextRound();
	}
	
	/**
	 * This method prints the console head with the choosen difficulty. If this.ownTurn if true, the method for the humans player action is called.
	 * Otherwise it is the AI's move. Then a new game round is started by a recursive call.
	 */
	private void nextRound() {
		
		this.screen.print("");
		this.screen.print("");
		this.screen.print("//==============> SPIEL (Schwierigkeit: " + ((KI) this.enemy).getDifficulty().getName() + ") <==============\\\\");
		
		// show players field
		if(this.ownTurn) {
			this.nextPlayerRound();
		} else {	// show enemys field (without ships
			this.nextKIRound();
		}
		
		// begin next round
		this.nextRound();
	}
	
	/**
	 * This method performs the player action in one round. It prints some important informations and reads a new coordinate to be fired upon.
	 * A corresponding success or error message is issued.
	 * It is checked whether the player has won and the opponent's playing matchfield is displayed with corresponding shots.
	 */
	private void nextPlayerRound() {
		
		boolean hit = false;
		Coordinate choosenCoordinate = null;
		
		// show enemys matchfield without ship positions (only hitted ships)
		this.screen.print("||                      Gegnerisches Feld                      ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		this.enemy.getMatchfield().show(false);
		
		this.screen.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");
		
		// loops for own shot
		while(this.scanner.hasNext()) {
			
			String input = this.scanner.next();
			
			// check if coordinate has correct syntax => shoot on this position
			if(input.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				choosenCoordinate = this.enemy.getMatchfield().getCoordinateByString(input);
					
				// check if already hitted
				if(choosenCoordinate.hasHit()) {
					this.screen.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)", "error");
				} else {
					hit = this.enemy.getMatchfield().shoot(choosenCoordinate);
					break;
				}
			} else if(input.equalsIgnoreCase("?") || input.equalsIgnoreCase("--help")|| input.equalsIgnoreCase("help")) {
				this.screen.showHelp();
			} else {
				this.screen.print("Ungueltige Eingabe! (Bsp.: A4)", "error");
			}
		}
		
		// check for win
		if(this.enemy.getMatchfield().isGameOver()) {
			this.endGame(true);
			return;
		}
		
		// hit evalutation
		if(hit) {
			
			// show enemys matchfield without ship positions (only hitted ships)
			this.screen.print("||                      Gegnerisches Feld                      ||");
			this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
			this.enemy.getMatchfield().show(false);			
			
			Toolkit.getDefaultToolkit().beep();
			this.run++;
			
			if(this.enemy.getMatchfield().isShipDown(choosenCoordinate)) {
				this.screen.print("Du hast ein komplettes Schiff versenkt! Du darfst noch einmal schiessen!");
			} else {
				this.screen.print("Schiffsteil getroffen! Du darfst noch einmal schiessen!");
			}
			
			// wait before second shot
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		} else {
			this.screen.print("Kein Schiff getroffen! Dein Gegner ist am Zug!");
			this.ownTurn = false;
			this.run = 1;
			
			// wait on player change
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}				
			
		}	
	}
	
	/**
	 * This method shows the player all information about the opponent's AI move.
	 * The player is shown his own playing field and is notified about the success or failure of the AI.
	 * In addition, it is checked whether the AI has won, i.e. whether the player has lost.
	 */
	private void nextKIRound() {
		boolean hit = false;
		
		// show players matchfield with all ships and states
		this.screen.print("||                         Eigenes Feld                        ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		this.self.getMatchfield().show(true);			
		this.screen.print("Dein Gegner ist am Zug!", "waiting");	
		
		// wait before shot
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		// do KI shot
		Matchfield matchfield = this.self.getMatchfield();
		Coordinate kiCoordinate = ((KI) this.enemy).getKICoordinate(matchfield);
		hit = this.self.getMatchfield().shoot(kiCoordinate);
		
		// show players matchfield with all ships and states
		this.screen.print("||                         Eigenes Feld                        ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		this.self.getMatchfield().show(true);		
		
		// check for KI win
		if(this.self.getMatchfield().isGameOver()) {
			this.endGame(false);
			return;
		}
		
		// hit evalutation
		if(hit) {
			Toolkit.getDefaultToolkit().beep();
			this.screen.print("Eines Deiner Schiffe wurde getroffen! Dein Gegner ist noch einmal am Zug!");
			this.run++;
			
			// wait after successfull shot
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		} else {
			this.screen.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");
			this.ownTurn = true;
			this.run = 1;
			
			// wait on player change
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}			
			
		}		
		
	}
	
	/**
	 * This method changes the game state and displays the end screen to the player.
	 * 
	 * @param winner is the way to tell the program if the player has won or not.
	 */
	private void endGame(boolean winner) {
		
		if(this.status != Status.RUNNING) return;
		this.status = Status.ENDING;
		
		this.screen.showEndScreen(winner);
		this.enemy.getMatchfield().calculateAndPrintStatistics(this.screen);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}		
		
		// exit console application
		System.exit(0);
	}
	
}
