package view;

public class Game {

	private String enemyDifficulty;
	private GameHandler gameHandler;

	public Game(GameHandler gameHandler, String enemyDifficulty) {
		this.gameHandler = gameHandler;
		this.enemyDifficulty = enemyDifficulty;
	}

	public void showWaitingForSecondPlayer() {
		System.out.println("Warte auf zweiten Spieler...");
	}

	public void showSecondPlayerConnected() {
		System.out.println("Ein zweiter Spieler ist dem Spiel beigetreten!");
	}

	public void printHead() {

		ConsoleGUI.print("");
		ConsoleGUI.print("");
		ConsoleGUI.print("//==============> SPIEL (Schwierigkeit: " + this.enemyDifficulty + ") <==============\\\\");

	}

	public void printEnemyHeadline(int run) {
		ConsoleGUI.print("||                      Gegnerisches Feld                      ||");
		ConsoleGUI.print("||                      " + run + ". Schuss in Folge                     ||");
	}

	public void printPlayersHeadline(int run) {
		ConsoleGUI.print("||                         Eigenes Feld                        ||");
		ConsoleGUI.print("||                      " + run + ". Schuss in Folge                     ||");
	}

	public void showShotInstruction() {
		ConsoleGUI.print("Welche Position moechtest Du angreifen? (Bsp.: A4)", "highlight");
	}

	public void printPlayerRound(int run) {
		this.printEnemyHeadline(run);
		this.gameHandler.showEnemiesMatchfield();
		this.showShotInstruction();

		// loops for own shot
		while (ConsoleGUI.scanner.hasNext()) {

			String input = ConsoleGUI.scanner.next();
			boolean moveSuccessfull = this.gameHandler.doMove(input);

			if (moveSuccessfull) {
				break;
			}
		}
	}

	public void printEnemiesRound(int run) {
		this.printEnemiesMatchfield(run);
		this.showWaitingForEnemy();
	}

	public void printEnemiesMatchfield(int run) {
		this.printPlayersHeadline(run);
		this.gameHandler.showPlayersMatchfield();
	}

	public void printFieldAlreadyShot() {
		ConsoleGUI.print("Dieses Feld wurde bereits beschossen! Bitte waehle ein anderes aus! (Bsp.: A4)", "error");
	}

	public void printShotEvaluation(int run) {
		ConsoleGUI.print("||                      Gegnerisches Feld                      ||");
		ConsoleGUI.print("||                      " + run + ". Schuss in Folge                     ||");
		this.gameHandler.showEnemiesMatchfield();
	}

	public void printAfterShotMessage(boolean fullShipDown) {
		if (fullShipDown) {
			ConsoleGUI.print("Du hast ein komplettes Schiff versenkt! Du darfst noch einmal schiessen!");
		} else {
			ConsoleGUI.print("Schiffsteil getroffen! Du darfst noch einmal schiessen!");
		}
	}

	public void printNoShipHit() {
		ConsoleGUI.print("Kein Schiff getroffen! Dein Gegner ist am Zug!");
	}

	public void printInvalidInput() {
		ConsoleGUI.print("Ungueltige Eingabe! (Bsp.: A4)", "error");
	}

	private void showWaitingForEnemy() {
		ConsoleGUI.print("Dein Gegner ist am Zug!", "waiting");
	}

	public void printYourGameWasHit() {
		ConsoleGUI.print("Eines Deiner Schiffe wurde getroffen! Dein Gegner ist noch einmal am Zug!");
	}

	public void printEnemyMadeNoHit() {
		ConsoleGUI.print("Dein Gegner hat keines Deiner Schiffe getroffen! Du bist dran!");
	}

}
