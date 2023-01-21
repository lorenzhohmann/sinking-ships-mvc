package control;

import java.awt.Toolkit;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import model.Coordinate;
import model.Matchfield;
import model.Player;
import model.Status;
import view.Screen;

public class RemoteGame implements Runnable {

	public static final int PORT = 4998;

	private ServerSocket server;
	private Socket client;
	private boolean running;
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	private Thread thread;
	private int port;
	private String ip;
	private boolean isConnected;
	private Screen screen;
	private Scanner scanner;
	private Status status;
	private Player player;
	private Matchfield enemysField;
	private boolean ownTurn;
	private int run;
	private int connectionError;

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
		if (!this.connect()) {
			this.createServer();
		}

		// start async thread
		this.thread = new Thread(this);
		this.thread.start();
	}

	private void checkGameAction() {

		switch (this.status) {
		case WARMUP:
			break;
		case WAITING:
			this.goToShipPositioning(false);
			break;
		case RUNNING:

			if (this.ownTurn) { // own turn => do shot

				this.executeOwnTurn();

			} else { // enemys turn => read incoming shots

				this.executeEnemysTurn();

			}

			break;
		case ENDING:
			this.endGame();
			break;
		}
	}

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
		while (this.scanner.hasNext()) {

			String input = this.scanner.next();

			// check if coordinate has correct syntax => shoot on this position
			if (input.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				choosenCoordinate = this.enemysField.getCoordinateByString(input);

				// check if already hitted
				if (choosenCoordinate.hasHit()) {
					this.screen.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)",
							"error");
				} else {
					hit = this.enemysField.shoot(choosenCoordinate);
					break;
				}
			} else if (input.equalsIgnoreCase("?") || input.equalsIgnoreCase("--help")
					|| input.equalsIgnoreCase("help")) {
				this.screen.showHelp();
			} else {
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
		if (this.enemysField.isGameOver()) {
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
		if (hit) {
			Toolkit.getDefaultToolkit().beep();

			if (this.enemysField.isShipDown(choosenCoordinate)) {
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
			Coordinate simulatedShot = this.player.getMatchfield().getCoordinateByXAndY(enemysShot.getX(),
					enemysShot.getY());
			this.player.getMatchfield().shoot(simulatedShot);

			// show matchfield again with new state
			this.screen.print("");
			this.screen.print("");
			this.screen.print("//==========================> SPIEL <==========================\\\\");
			this.screen.print("||                         Eigenes Feld                        ||");
			this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
			this.player.getMatchfield().show(true);

			if (!enemysShot.hasShip()) {
				this.screen.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");

				Thread.sleep(3000);

				this.run = 1;
				this.ownTurn = true;
			} else {
				Toolkit.getDefaultToolkit().beep();

				if (this.player.getMatchfield().isShipDown(simulatedShot)) {
					this.screen.print(
							"Dein Gegner hat eines Deiner Schiffe versenkt! Dein Gegner ist noch einmal am Zug!");
				} else {
					this.screen.print("Ein Schiffsteil von Dir wurde getroffen! Dein Gegner ist noch einmal am Zug!");
				}

				this.run++;

				Thread.sleep(3000);
			}

			// check for enemys win
			if (this.player.getMatchfield().isGameOver()) {
				this.screen.showEndScreen(false);
				this.endGame();
				return;
			}

		} catch (ClassNotFoundException | IOException | InterruptedException e) {
			this.connectionError++;
			this.screen.print("Aktuell gibt es Probleme bei der Verbindung, gleich geht es weiter!", "error");
		}
	}

	private void goToShipPositioning(boolean hasManualPosition) {

		// // if this function call does not come from manual positioning
		if (!hasManualPosition) {
			// place players ships randomly
			this.player.getMatchfield().resetShips();
			this.player.getMatchfield().placeRandomShips();
		}

		this.player.getMatchfield().show(true);

		// position of ships page
		this.screen.showShipPositioning();

		while (this.scanner.hasNext()) {

			String input = this.scanner.next();

			if (input.equalsIgnoreCase("z")) { // generate new random ship positions
				this.screen.print("Deine Flotte wurde neu positioniert!");

				this.player.getMatchfield().resetShips();
				this.player.getMatchfield().placeRandomShips();
				this.player.getMatchfield().show(true);

				this.screen.showShipPositioning();
				continue;
			} else if (input.equalsIgnoreCase("m")) {
				this.goToManualShipPositioning();
				break;
			} else if (input.equalsIgnoreCase("s")) { // start game
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

	private void goToManualShipPositioning() {

		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}

		this.screen.print("Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
		this.screen.print(
				"4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
		this.screen.print("Beispiel: A1-G5-F3-A8");

		while (scanner.hasNext()) {
			String manualPositioningString = this.scanner.next();

			boolean success = this.player.getMatchfield().positionShipsByString(manualPositioningString, 4);
			if (success) {
				break;
			}

			this.screen.print("Die Positionierung kann so nicht durchgefuehrt werden!", "error");

			this.screen.print(
					"Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
			this.screen.print(
					"4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
			this.screen.print("Beispiel: A1-G5-F3-A8");
		}

		this.goToShipPositioning(true);
	}

	public void endGame() {
		this.enemysField.calculateAndPrintStatistics(this.screen);
		this.status = Status.ENDING;
		this.running = false;

		// exit application
		System.exit(0);
	}

	@Override
	public void run() {
		while (this.running) {

			if (this.connectionError >= 10) {
				this.running = false;
				this.screen.print("Die Verbindung zu Deinem Gegner wurde getrennt! Das Spiel wird jetzt beendet!",
						"error");
				System.exit(0);
			}

			if (!this.isConnected) {
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