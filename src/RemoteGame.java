import java.net.*;
import java.util.Scanner;
import java.awt.Toolkit;
import java.io.*;

/**
 * This class handles the whole logic for the multiplayer game (equivalent to the GameManger class for the singleplayer game).
 * Some functions create and check the connection of two game instances representing two players.
 * It has some methods to show and handle the menu actions. There are also methods that control the course of the game,
 * from user input to the evaluation and display of the game results.
 * 
 * @author Lorenz Hohmann
 */
public class RemoteGame implements Runnable {
	
	/**
	 * The socket connection of the game hoster
	 */
	private ServerSocket server;
	
	/**
	 * The socket connection of the second player (client)
	 */
	private Socket client;
	
	/**
	 * While this boolean is true the game loop runs in an end loop
	 */
	private boolean running;
	
	/**
	 * The input stream to handle incoming data from the other player
	 */
	private ObjectInputStream inputStream;
	
	/**
	 * The output stream to handle outgoing data to the other player
	 */
	private ObjectOutputStream outputStream;
	
	/**
	 * A new thread that the whole multiplayer game runs async
	 */
	private Thread thread;
	
	/**
	 * The port on which the game should run
	 */
	private int port;
	
	/**
	 * The ip on which the game should run
	 */
	private String ip;
	
	/**
	 * A bool that shows if a player is connected to the server
	 */
	private boolean isConnected;
	
	/**
	 * A screen instance that can display messages to the player
	 */
	private Screen screen;
	
	/**
	 * The Scanner object to handle user console inputs
	 */
	private Scanner scanner;
	
	/**
	 * The current state of the game (e.g. waiting, running, ...)
	 */
	private Status status;
	
	/**
	 * The Player instance of the own player
	 */
	private Player player;
	
	/**
	 * The matchfield of the second player (opponent)
	 */
	private Matchfield enemysField;
	
	/**
	 * A bool if it's the own turn or the opponents turn
	 */
	private boolean ownTurn;
	
	/**
	 * Counts how many shots you or your opponent have in a row
	 */
	private int run;
	
	/**
	 * Counts up on every connection error to abort the whole game on too many errors
	 */
	private int connectionError;
	
	/**
	 * The constructor passes some variables from the Main class and initialize other important variables.
	 * The application tries to connect to the server. If there is no server a new server will be created.
	 * 
	 * A new thread for this class is initialized and started. So the main application is running asynchronous.
	 * 
	 * @param ip where the server should run on or the client should connect at
	 * @param port where the server should run on the client should connect at
	 * @param screen instance from the Main class (avoids double initialization)
	 */
	public RemoteGame(String ip, int port, Screen screen) {
		this.running = true;
		this.port = port;
		this.ip = ip;
		this.isConnected = false;
		this.screen = screen;
		this.scanner = new Scanner(System.in);
		this.status = Status.WAITING;
		this.player = new Player();
		this.ownTurn = false;
		this.enemysField = null;
		this.run = 1;
		this.connectionError = 0;

		// connect to server or start server if no exists
		if(!this.connect()) {
			this.createServer();
		}
		
		// start async thread
		this.thread = new Thread(this);
		this.thread.start();
	}
	
	/**
	 * This method is a part of the endless while-loop (repeating every 1000ms, see run() method).
	 * It calls specific methods that contains the logic for that state depending on the current game state.
	 */
	private void checkGameAction() {
		
		switch(this.status) {
			case WARMUP:
				break;
			case WAITING:
				this.goToShipPositioning(false);
				break;
			case RUNNING:
				
				if(this.ownTurn) {	// own turn => do shot
					
					this.executeOwnTurn();
					
				} else {	// enemys turn => read incoming shots
					
					this.executeEnemysTurn();
				
				}				
				
				break;
			case ENDING:
				this.endGame();
				break;
		}
	}
	
	/**
	 * This method contains the logic for the players own turn.
	 * Information about the opponent's playing field is displayed.
	 * You can also enter a position on the board to be fired at.
	 * The shot will then be executed and messages about success or failure will be displayed.
	 * This data is sent to the opponent player via the output stream.
	 * Furthermore it is checked if the player has won.
	 */
	private void executeOwnTurn() {
		
		this.screen.print("//==========================> SPIEL <==========================\\\\");
		this.screen.print("||                      Gegnerisches Feld                      ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		
		
		boolean hit = false;
		Coordinate choosenCoordinate = null;
		
		// show enemys matchfield (without ships)
		this.enemysField.show(false);
		
		this.screen.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");
		
		// loops for own shot
		while(this.scanner.hasNext()) {
			
			String input = this.scanner.next();
			
			// check if coordinate has correct syntax => shoot on this position
			if(input.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				choosenCoordinate = this.enemysField.getCoordinateByString(input);
					
				// check if already hitted
				if(choosenCoordinate.hasHit()) {
					this.screen.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)", "error");
				} else {
					hit = this.enemysField.shoot(choosenCoordinate);
					break;
				}
			} else if(input.equalsIgnoreCase("?") || input.equalsIgnoreCase("--help")|| input.equalsIgnoreCase("help")) {
				this.screen.showHelp();
			}	
			else {
				this.screen.print("Ungueltige Eingabe! (Bsp.: A4)", "error");
			}
		}
		
		// send hitted coordinate to enemy
		try {
			// send shot coordinate
			this.outputStream.writeObject(choosenCoordinate);
			this.outputStream.flush();
		} catch (IOException e) {
			this.connectionError++;
			this.screen.print("Aktuell gibt es Probleme bei der Verbindung, gleich geht es weiter!", "error");
		}	
		
		// check for own win
		if(this.enemysField.isGameOver()) {
			this.screen.showEndScreen(true);
			this.endGame();
			return;
		}		
		
		// show enemys matchfield with new state
		this.screen.print("//==========================> SPIEL <==========================\\\\");
		this.screen.print("||                      Gegnerisches Feld                      ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		this.enemysField.show(false);
		
		// hit evalutation
		if(hit) {
			Toolkit.getDefaultToolkit().beep();
			
			if(this.enemysField.isShipDown(choosenCoordinate)) {
				this.screen.print("Du hast ein komplettes Schiff versenkt! Du darfst noch einmal schiessen!");
			} else {
				this.screen.print("Schiffsteil getroffen! Du darfst noch einmal schiessen!");
			}
			
			this.run++;
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			this.screen.print("Kein Schiff getroffen! Dein Gegner ist am Zug!");
			
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			this.run = 1;
			this.ownTurn = false;
		}	
	}
	
	/**
	 * This method contains the logic for the opponent players turn.
	 * Information about the own playing field is displayed.
	 * The input stream waits for incoming data from the opponent and then makes a shot on the own playing field with the given coordinate.
	 * Thereby corresponding messages about success or failure are displayed.
	 * Furthermore it is checked if the player has lost. 
	 */
	private void executeEnemysTurn() {
		
		// show own matchfield
		this.screen.print("");
		this.screen.print("");
		this.screen.print("//==========================> SPIEL <==========================\\\\");
		this.screen.print("||                         Eigenes Feld                        ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		this.player.getMatchfield().show(true);
		
		this.screen.print("Dein Gegner ist am Zug!", "waiting");
		
		try {
			// get shot coordinate
			Coordinate enemysShot = (Coordinate) this.inputStream.readObject();
			
			
			// simulate shot on own field
			Coordinate simulatedShot = this.player.getMatchfield().getCoordinateByXAndY(enemysShot.getX(), enemysShot.getY());
			this.player.getMatchfield().shoot(simulatedShot);
			
			
			// show matchfield again with new state 
			this.screen.print("");
			this.screen.print("");
			this.screen.print("//==========================> SPIEL <==========================\\\\");
			this.screen.print("||                         Eigenes Feld                        ||");
			this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
			this.player.getMatchfield().show(true);
			
			if(!enemysShot.hasShip()) {
				this.screen.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");
				
				Thread.sleep(3000);
				
				this.run = 1;	
				this.ownTurn = true;			
			} else {
				Toolkit.getDefaultToolkit().beep();
				
				if(this.player.getMatchfield().isShipDown(simulatedShot)) {
					this.screen.print("Dein Gegner hat eines Deiner Schiffe versenkt! Dein Gegner ist noch einmal am Zug!");
				} else {
					this.screen.print("Ein Schiffsteil von Dir wurde getroffen! Dein Gegner ist noch einmal am Zug!");
				}
				
				this.run++;
				
				Thread.sleep(3000);
			}	
			
			// check for enemys win
			if(this.player.getMatchfield().isGameOver()) {
				this.screen.showEndScreen(false);
				this.endGame();
				return;
			}
			
		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			this.connectionError++;
			this.screen.print("Aktuell gibt es Probleme bei der Verbindung, gleich geht es weiter!", "error");
		}
	}
	
	/**
	 * This method shows the player his matchfield, on which he has the possibility to position ships automatically or manually.
	 * Therefor the program reads the users console input. 
	 * An error is displayed if the entry is incorrect. The game starts if the user select "s".
	 * Afterwards the matchfield is sent to the opponent and the matchfield data is waited for.
	 * 
	 * @param hasManualPosition defines it the ships should be set randomly or not (important after call from goToManualShipPositioning())
	 */
	private void goToShipPositioning(boolean hasManualPosition) {

		// // if this function call does not come from manual positioning
		if(!hasManualPosition) {
			// place players ships randomly
			this.player.getMatchfield().resetShips();
			this.player.getMatchfield().placeRandomShips();		
		}
		
		this.player.getMatchfield().show(true);
		
		// position of ships page
		this.screen.showShipPositioning();	
		
		while(this.scanner.hasNext()) {
			
			String input = this.scanner.next(); 
			
			if(input.equalsIgnoreCase("z")) {	// generate new random ship positions
				this.screen.print("Deine Flotte wurde neu positioniert!");

				this.player.getMatchfield().resetShips();
				this.player.getMatchfield().placeRandomShips();
				this.player.getMatchfield().show(true);
				
				this.screen.showShipPositioning();
				continue;
			} else if(input.equalsIgnoreCase("m")) {
				this.goToManualShipPositioning();
				break;
			} else if(input.equalsIgnoreCase("s")) {	// start game
				this.player.setReady(true);
				this.status = Status.RUNNING;
				this.screen.print("Warte auf Gegner...!", "waiting");
				
				// send generated matchfield to enemy
				try {
					this.screen.print("Spieldaten werden versendet...", "waiting");
					this.outputStream.writeObject(this.player.getMatchfield());
					this.outputStream.flush();
					this.screen.print("Spieldaten erfolgreich versendet!");
				} catch (IOException e) {
					this.connectionError++;
					this.screen.print("Aktuell gibt es Probleme bei der Verbindung, gleich geht es weiter!", "error");
				}
				
				// get matchfield from enemy
				try {
					this.screen.print("Warte auf gegnerische Spieldaten...", "waiting");
					this.enemysField = (Matchfield) this.inputStream.readObject();
					this.screen.print("Spieldaten erfolgreich synchronisiert!");
				} catch (IOException | ClassNotFoundException e) {
					this.connectionError++;
					this.screen.print("Aktuell gibt es Probleme bei der Verbindung, gleich geht es weiter!", "error");
				}
				
				// NOW SWITCHED MATCHFIELDS
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
			
			boolean success = this.player.getMatchfield().positionShipsByString(manualPositioningString, 4);
			
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
	 * This methods changes the state to ending, stops the endless while-loop in the run() method and aborts the whole application.
	 */
	public void endGame() {
		this.enemysField.calculateAndPrintStatistics(this.screen);
		this.status = Status.ENDING;
		this.running = false;
		
		// exit application
		System.exit(0);
	}

	
	/* == NETWORK/THREAD STUFF == */
	/**
	 * This method is from the implemented Runnable (async Thread) and builds the main game loop.
	 * A while-loop is running while running is true and it repeats every second.
	 * The host of the game checks to connect to the client if the host is not connected to him.
	 * Every time the checkGameAction() method is called, there happens the main logic.
	 * 
	 * If there are more or exactly ten connection error a message will be displayed and the application will be aborted.
	 */
	@Override
	public void run() {
		while(this.running) {
			
			if(this.connectionError >= 10) {
				this.running = false;
				this.screen.print("Die Verbindung zu Deinem Gegner wurde getrennt! Das Spiel wird jetzt beendet!", "error");
				System.exit(0);
			}
			
			if(!this.isConnected) {
				this.tryReconnect();
			}
			
			this.checkGameAction();
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method is waiting for the second players connection to the host.
	 * If this succeeds the input and output stream is initialized and a message will be displayed that a second player joined.
	 */
	private void tryReconnect() {
		Socket socket = null;
		try {					
			socket = this.server.accept();
			this.inputStream = new ObjectInputStream(socket.getInputStream());
			this.outputStream = new ObjectOutputStream(socket.getOutputStream());
			this.isConnected = true;
			this.screen.print("Ein zweiter Spieler ist dem Spiel beigetreten!");
		} catch (IOException e) {
			this.connectionError++;
			this.screen.print("Aktuell gibt es Probleme bei der Verbindung, gleich geht es weiter!", "error");
		}
	}
	
	/**
	 * This method tries to start a connection to the ip and port passed in the constructor.
	 * It also initializes the input and output stream to handle the users data.
	 * A message is displayed if everything works, the game state changes to waiting and the boolean for the own turn is set to true.
	 * 
	 * @return a boolean if the connection to the ip and port was successfull or not
	 */
	private boolean connect() {
		try {
			this.client = new Socket(this.ip, this.port);
			this.outputStream = new ObjectOutputStream(this.client.getOutputStream());
			this.inputStream = new ObjectInputStream(this.client.getInputStream());
			this.isConnected = true;
		} catch (IOException e) {
			return false;
		}
		this.screen.print("Du wurdest erfolgreich mit dem Server verbunden!");
		
		// set player to for first turn
		this.ownTurn = true;
		
		// go to ship positioning
		this.status = Status.WAITING;
		return true;
	}
	
	/**
	 * This method creates a new socket connection on localhost and the passed port from the constructor. (the host)
	 * A success or error message will be displayed to the player.
	 */
	private void createServer() {
		try {
			this.server = new ServerSocket(this.port);
			this.screen.print("Server unter " + this.server.getLocalSocketAddress() + " gestartet!");
			this.screen.print("Warte auf zweiten Spieler...", "waiting");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}