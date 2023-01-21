import java.util.Scanner;

public class Main {
	
	private static final int PORT = 4998;
	
	public static void main(String[] args) {
		
		Screen screen = new Screen();
		
		// play startup animation
		try {
			screen.playStartupAnimation();
		} catch (Exception e) {
		}
		
		// show main menu to choose mode
		Scanner scanner = new Scanner(System.in);
		screen.showMainMenu();
		
		while(scanner.hasNext()) {
			
			String input = scanner.next();
			
			if(input.equalsIgnoreCase("e")) {	// start singleplayer session
				
				GameManager manager = new GameManager(screen);
				manager.initGame();
				scanner.close();
				return;
			} else if(input.equalsIgnoreCase("m")) {	// start multiplayer session

				screen.print("Zu welchem Server (IP) moechtest Du dich verbinden? \nFalls Du einen Server erstellen moechtest, gib einfach \"localhost\" ein.", "highlight");
				
				// read remote ip
				while(scanner.hasNext()) {
					String ip = scanner.next();
					
					// start remote game
					new RemoteGame(ip, Main.PORT, screen);
					return;
				}
				
				screen.print("Ungueltige Eingabe! Bitte gebe eine IP-Adresse (xxx.xxx.xxx.xxx) ein.", "error");
			}
			
			screen.print("Ungueltige Eingabe!", "error");
		}
		
		scanner.close();
	}
}
