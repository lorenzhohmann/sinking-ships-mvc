package control;

import java.awt.Toolkit;
import java.util.Scanner;

import model.Coordinate;
import model.KI;
import model.Matchfield;
import model.Player;
import model.Status;
import view.Screen;

public class GameManager {

	private Player self;
	private Player enemy;
	private Screen screen;
	private Status status;
	private Scanner scanner;
	private boolean ownTurn;
	private int run;

	public GameManager(Screen screen) {

		this.screen = screen;
		this.scanner = new Scanner(System.in);
		this.ownTurn = true;
		this.run = 1;

		this.self = new Player();
	}

	public void initGame() {

		this.status = Status.WAITING;

//		this.enemy = new KI();
//		this.goToSettings();
	}

//	private void goToSettings() {
//
//		this.screen.showSettings();
//		String chosenDifficulty;
//
//		while (this.scanner.hasNext()) {
//			chosenDifficulty = this.scanner.next().toLowerCase();
//
//			if (chosenDifficulty.equalsIgnoreCase("e")) {
//				((KI) this.enemy).setDifficulty(Difficulty.EASY);
//				break;
//			} else if (chosenDifficulty.equalsIgnoreCase("s")) {
//				((KI) this.enemy).setDifficulty(Difficulty.HARD);
//				break;
//			} else if (chosenDifficulty.equalsIgnoreCase("x")) {
//				((KI) this.enemy).setDifficulty(Difficulty.EXTREM);
//				break;
//			}
//
//			this.screen.print("Ungueltige Eingabe!", "error");
//			this.screen.showSettings();
//		}
//
//		this.goToShipPositioning(false);
//	}

//	public void goToShipPositioning(boolean hasManualPosition) {
//
//		// clear console
//		try {
//			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//		} catch (InterruptedException | IOException e) {
//		}
//
//		if (!hasManualPosition) { // if this function call does not come from manual positioning
//
//			// update game status
//			if (this.status != Status.WAITING)
//				return;
//			this.status = Status.WARMUP;
//
//			// place enemys ships randomly
//			this.enemy.getMatchfield().placeRandomShips();
//
//			// place players ships randomly
//			this.self.getMatchfield().placeRandomShips();
//		}
//
//		// place players ships randomly
//		this.self.getMatchfield().show(true);
//
//		// position of ships page
//		this.screen.showShipPositioning();
//
//		while (this.scanner.hasNext()) {
//
//			String input = this.scanner.next();
//
//			if (input.equalsIgnoreCase("z")) { // generate new random ship positions
//
//				// clear console
//				try {
//					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//				} catch (InterruptedException | IOException e) {
//				}
//				this.screen.print("Deine Flotte wurde neu positioniert!");
//
//				this.self.getMatchfield().resetShips();
//				this.self.getMatchfield().placeRandomShips();
//				this.self.getMatchfield().show(true);
//
//				this.screen.showShipPositioning();
//				continue;
//			} else if (input.equalsIgnoreCase("m")) {
//				this.goToManualShipPositioning();
//				break;
//
//			} else if (input.equalsIgnoreCase("s")) { // start game
//				this.startGame();
//				break;
//			}
//
//			this.screen.print("Ungueltige Eingabe, waehle [S], [Z] oder [M]!", "error");
//			this.screen.showShipPositioning();
//		}
//	}

//	private void goToManualShipPositioning() {
//
//		// clear console
//		try {
//			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//		} catch (InterruptedException | IOException e) {
//		}
//
//		this.screen.print("Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
//		this.screen.print(
//				"4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
//		this.screen.print("Beispiel: A1-G5-F3-A8");
//
//		while (scanner.hasNext()) {
//			String manualPositioningString = this.scanner.next();
//
//			boolean success = this.self.getMatchfield().positionShipsByString(manualPositioningString, 4);
//			if (success) {
//				break;
//			}
//
//			this.screen.print("Die Positionierung kann so nicht durchgefuehrt werden!", "error");
//
//			this.screen.print(
//					"Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
//			this.screen.print(
//					"4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
//			this.screen.print("Beispiel: A1-G5-F3-A8");
//		}
//
//		this.goToShipPositioning(true);
//	}

//	private void startGame() {
//
//		if (this.status != Status.WARMUP)
//			return;
//		this.status = Status.RUNNING;
//
//		this.self.setReady(true);
//		this.enemy.setReady(true);
//
//		// start first game round
//		this.nextRound();
//	}

//	private void nextRound() {
//
//		this.screen.print("");
//		this.screen.print("");
//		this.screen.print("//==============> SPIEL (Schwierigkeit: " + ((KI) this.enemy).getDifficulty().getName()
//				+ ") <==============\\\\");
//
//		// show players field
//		if (this.ownTurn) {
//			this.nextPlayerRound();
//		} else { // show enemys field (without ships
//			this.nextKIRound();
//		}
//
//		// begin next round
//		this.nextRound();
//	}

	private void nextPlayerRound() {

		boolean hit = false;
		Coordinate choosenCoordinate = null;

		// show enemys matchfield without ship positions (only hitted ships)
		this.screen.print("||                      Gegnerisches Feld                      ||");
		this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
		this.enemy.getMatchfield().show(false);

		this.screen.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");

		// loops for own shot
		while (this.scanner.hasNext()) {

			String input = this.scanner.next();

			// check if coordinate has correct syntax => shoot on this position
			if (input.matches("[A-Ja-j](1|2|3|4|5|6|7|8|9|10)")) {
				choosenCoordinate = this.enemy.getMatchfield().getCoordinateByString(input);

				// check if already hitted
				if (choosenCoordinate.hasHit()) {
					this.screen.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)",
							"error");
				} else {
					hit = this.enemy.getMatchfield().shoot(choosenCoordinate);
					break;
				}
			} else if (input.equalsIgnoreCase("?") || input.equalsIgnoreCase("--help")
					|| input.equalsIgnoreCase("help")) {
				this.screen.showHelp();
			} else {
				this.screen.print("Ungueltige Eingabe! (Bsp.: A4)", "error");
			}
		}

		// check for win
		if (this.enemy.getMatchfield().isGameOver()) {
			this.endGame(true);
			return;
		}

		// hit evalutation
		if (hit) {

			// show enemys matchfield without ship positions (only hitted ships)
			this.screen.print("||                      Gegnerisches Feld                      ||");
			this.screen.print("||                      " + this.run + ". Schuss in Folge                     ||");
			this.enemy.getMatchfield().show(false);

			Toolkit.getDefaultToolkit().beep();
			this.run++;

			if (this.enemy.getMatchfield().isShipDown(choosenCoordinate)) {
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
		if (this.self.getMatchfield().isGameOver()) {
			this.endGame(false);
			return;
		}

		// hit evalutation
		if (hit) {
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

	private void endGame(boolean winner) {

		if (this.status != Status.RUNNING)
			return;
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
