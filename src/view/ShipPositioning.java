package view;

import java.io.IOException;

public class ShipPositioning {

	private ShipPositioningHandler shipPositioningHandler;

	public ShipPositioning(ShipPositioningHandler handler) {
		this.shipPositioningHandler = handler;
	}

	public void showShipPositioningMenu() {
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||                 Bist Du mit der Positionierung              ||");
		System.out.println("||                     der Schiffe zufrieden?                  ||");
		System.out.println("||    _______________     _____________     ________________   ||");
		System.out.println("||   |               |   |             |   |                |  ||");
		System.out.println("||   | Zufaellig [Z] |   | Manuell [M] |   |  Starten [S]   |  ||");
		System.out.println("||   |_______________|   |_____________|   |________________|  ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}

	public void showShipPositioning(boolean hasManualPosition) {

		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}

		if (!hasManualPosition) { // if this function call does not come from manual positioning

			// update game status
			if (this.shipPositioningHandler.isNotWaitingStatus()) {
				return;
			}

			this.shipPositioningHandler.setStatusWarmup();

			// place enemys ships randomly
			this.shipPositioningHandler.placeEnemiesShipsRandomly();

			// place players ships randomly
			this.shipPositioningHandler.placePlayersShipsRandomly();
		}

		// place players ships randomly
		this.shipPositioningHandler.showPlayersMatchfield();

		// position of ships page
		this.showShipPositioningMenu();

		while (ConsoleGUI.scanner.hasNext()) {

			String input = ConsoleGUI.scanner.next();

			if (input.equalsIgnoreCase("z")) { // generate new random ship positions

				// clear console
				try {
					new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
				} catch (InterruptedException | IOException e) {
				}
				ConsoleGUI.print("Deine Flotte wurde neu positioniert!");

				this.shipPositioningHandler.resetPlayersShips();
				this.shipPositioningHandler.placePlayersShipsRandomly();
				this.shipPositioningHandler.showPlayersMatchfield();

				this.showShipPositioningMenu();

				continue;
			} else if (input.equalsIgnoreCase("m")) {
				this.showManualShipPositioning();
				break;

			} else if (input.equalsIgnoreCase("s")) { // start game
				this.shipPositioningHandler.startGame();
				break;
			}

			ConsoleGUI.print("Ungueltige Eingabe, waehle [S], [Z] oder [M]!", "error");
			this.showShipPositioning(true);
		}
	}

	private void showManualShipPositioning() {

		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}

		this.showManualShipPositioningInstruction();

		while (ConsoleGUI.scanner.hasNext()) {
			String manualPositioningString = ConsoleGUI.scanner.next();

			boolean success = this.shipPositioningHandler.setShipPositionsManual(manualPositioningString, 4);
			if (success) {
				break;
			}

			ConsoleGUI.print("Die Positionierung kann so nicht durchgefuehrt werden!", "error");
			this.showManualShipPositioningInstruction();
		}

		this.showShipPositioning(true);
	}

	private void showManualShipPositioningInstruction() {
		ConsoleGUI.print("Bitte gebe nun die Positionerung Deiner Schiffe manuell ein!\nNutze dazu folgendes Format:");
		ConsoleGUI.print(
				"4er vertikales Schiff <==> 4er vertikales Schiff <==> 4er horizontales Schiff <==> 4er horizontales Schiff");
		ConsoleGUI.print("Beispiel: A1-G5-F3-A8");
	}

}
