package view.console;

import java.io.IOException;

public class ShipPositioning {

	/**
	 * Handler for all ship positioning actions
	 */
	private ShipPositioningHandler shipPosHandler;

	public ShipPositioning(ShipPositioningHandler handler) {
		this.shipPosHandler = handler;
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

	/**
	 * Shows the ship positioning and reads the user console input to call the
	 * controller
	 * 
	 * @param hasManualPosition - whether the function call from the users input or
	 *                          another method
	 */
	public void showShipPositioning(boolean hasManualPosition) {

		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}

		if (!hasManualPosition) { // if this function call does not come from manual positioning

			// place enemys ships randomly
			this.shipPosHandler.placeEnemiesShipsRandomly();

			// place players ships randomly
			this.shipPosHandler.placePlayersShipsRandomly();
		}

		// place players ships randomly
		this.shipPosHandler.showPlayersMatchfield();

		// position of ships page
		this.showShipPositioningMenu();

		while (ConsoleGUI.scanner.hasNext()) {

			String input = ConsoleGUI.scanner.next();
			ShipPosReturnCode handlerResponse = this.shipPosHandler.handlePositioningInput(input);

			if (handlerResponse == ShipPosReturnCode.CONTINUE) {
				continue;
			}
			if (handlerResponse == ShipPosReturnCode.BREAK) {
				break;
			}
		}

	}

	/**
	 * Shows and reads the manual positioning of ships
	 */
	public void showManualShipPositioning() {

		// clear console
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}

		this.showManualShipPositioningInstruction();

		while (ConsoleGUI.scanner.hasNext()) {
			String manualPosString = ConsoleGUI.scanner.next();

			boolean success = this.shipPosHandler.positionShipManual(manualPosString, 4);
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

	public void showShipsNewSet() {
		ConsoleGUI.print("Deine Flotte wurde neu positioniert!");
	}

}
