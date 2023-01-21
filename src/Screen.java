import java.awt.Toolkit;
import java.io.IOException;

/**
 * This class simulates the animation when the game get started and also shows the game menu, 
 * where you can choose the difficulty of the game and the position of the ships.
 *
 * @author Valeria Savjalov
 */
public class Screen {

	/**
	 *
	 * Shows the picture series which simulate the animation when the game is running
	 * @throws exception when console can`t be cleared
	 *
	 */	
	public void playStartupAnimation() throws Exception {
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");    

		Thread.sleep(100);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();		 			
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("			                                              ");            
		System.out.println("			                                              ");            
		System.out.println("			                                              ");            
		System.out.println("			                                              ");  	
		System.out.println("			                                              ");            	
		System.out.println("			                                              ");            
		System.out.println("			                                              ");                 	
		System.out.println("                    @@@@                                  "); 
		System.out.println("                    @@@@@@@                               "); 
		System.out.println("                    @@  #@@@@@                            "); 
		System.out.println("                    @@#@@@   @@@@                         "); 
		System.out.println("                    @@@@        @@@@                      "); 
		System.out.println("                    @@             @@@@                   "); 
		System.out.println("                    @@             ,,,@@@@                "); 
		System.out.println("                    @@@@@@@@@@@@@@@@@@@@@@@               "); 
		System.out.println("                    @@                                    "); 
		System.out.println("       ,@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           "); 
		System.out.println("        @@@                                /@@            "); 
		System.out.println("         @@                                @@             "); 
		System.out.println("         @@@         @@@@@@@@             @@@             "); 
		System.out.println("          @@     @@@@@@@@@@@@@@@@        @@@@@@@@         "); 
		System.out.println("     @@@  @@@ @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  				

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	

		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                    @@@@                                  ");
		System.out.println("                    @@@@@@@.                              "); 
		System.out.println("                    @@  @@@@@@                            ");
		System.out.println("                    @@@@@@   @@@@*                        "); 
		System.out.println("                    @@@@        @@@@(                     ");  
		System.out.println("                    @@             @@@@*                  ");  
		System.out.println("                    @@          .%%%%&@@@@                ");  
		System.out.println("                    @@@@@@@@@@@@@@@@@@@@@@@               ");  
		System.out.println("                    @@,                                   ");  
		System.out.println("       &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           ");  
		System.out.println("        @@@                                %@@            ");  
		System.out.println("        (@@                          @@@@@@@@@@@          ");  
		System.out.println("         @@@@@@@@@@@@@             @@@@@@@@@@@@@@@&       ");  
		System.out.println("        @@@@@@@@@@@@@@@@@@       @@@@@@@@@@@@@@@@@@@      ");  
		System.out.println("   ***@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();			
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                   @      "); 
		System.out.println("                                                   # %    "); 
		System.out.println("                     @@@@#                         @ @    "); 
		System.out.println("                   @      &@@            %@@@@   ,@@      "); 
		System.out.println("                  *  @@@@@ @@@@@@@@@@@@@@@@@@@@@@         "); 
		System.out.println("                 @  @@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@       "); 
		System.out.println("                 @ @@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@        "); 
		System.out.println("                  @ @@@@@ @@@@@@@@@@@@@@@@@@@@@@@@#       "); 
		System.out.println("                   @@   @@@@@@@@@@@@@@@@@@@@@@@@@@@       "); 
		System.out.println("                          *@@@@@@@@@@@@@@@@@@@@@@@@@      "); 
		System.out.println("                          @@ #@@@@@@ @@@@ @@@@@@@@@@@&    "); 
		System.out.println("                           @@ @ @@@@@    @  @@@@@@@@@@    "); 
		System.out.println("                            @@@@@@ ,  @@  @ @@@@@@@@@@%   "); 
		System.out.println("                                  @@@ @   @ @@@@@@@@@@    ");   
		System.out.println("                                   @#@@@@ @    @@         "); 			
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                               		  ");
		System.out.println("                                               		  ");
		System.out.println("                                               		  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("  @                                                       ");
		System.out.println("   #@         @                                           "); 
		System.out.println("               @                                          "); 
		System.out.println("           @@   @                                         "); 
		System.out.println("      @@@@   @@   @@                               @      "); 
		System.out.println("    @@@@@@@@    @@                                 # %    "); 
		System.out.println("   @@@@@@@@@@        @@@@#                         @ @    "); 
		System.out.println("@  @@@@@@@@@@  @@  @      &@@            %@@@@   ,@@      "); 
		System.out.println("    *@@@@@@@      *  @@@@@ @@@@@@@@@@@@@@@@@@@@@@         "); 
		System.out.println("             @@@ @  @@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@       "); 
		System.out.println("                 @ @@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@        "); 
		System.out.println("            ,@@   @ @@@@@ @@@@@@@@@@@@@@@@@@@@@@@@#       "); 
		System.out.println("      .@@          @@   @@@@@@@@@@@@@@@@@@@@@@@@@@@       "); 
		System.out.println("    @                     *@@@@@@@@@@@@@@@@@@@@@@@@@      "); 
		System.out.println("   @                      @@ #@@@@@@ @@@@ @@@@@@@@@@@&    "); 
		System.out.println("                           @@ @ @@@@@    @  @@@@@@@@@@    "); 
		System.out.println("                            @@@@@@ ,  @@  @ @@@@@@@@@@%   "); 
		System.out.println("                                  @@@ @   @ @@@@@@@@@@    "); 
		System.out.println("                                   @#@@@@ @    @@         "); 

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("           @                                              ");  
		System.out.println("            @                                             ");  
		System.out.println("       #@     @                                           ");  
		System.out.println("         @      @                                         ");  
		System.out.println("      @@@@        @                                @      ");  
		System.out.println("    @@@@@@@@                                       # %    ");  
		System.out.println("   @@@@@@@@@@  @     @@@@#                         @ @    ");  
		System.out.println("   @@@@@@@@@@  @@  @      &@@            %@@@@   ,@@      ");  
		System.out.println("    *@@@@@@@      *  @@@@@ @@@@@@@@@@@@@@@@@@@@@@         ");  
		System.out.println("                 @  @@@@@@ @@@@@@@@@@@@@@@@@@@@@@@@       ");  
		System.out.println("                 @ @@@@@@@ @@@@@@@@@@@@@@@@@@@@@@@        ");  
		System.out.println(" #@@@@@      @@   @ @@@@@ @@@@@@@@@@@@@@@@@@@@@@@@#       ");  
		System.out.println("                   @@   @@@@@@@@@@@@@@@@@@@@@@@@@@@       ");  
		System.out.println("                          *@@@@@@@@@@@@@@@@@@@@@@@@@      ");  
		System.out.println("                          @@ #@@@@@@ @@@@ @@@@@@@@@@@&    ");  
		System.out.println("                           @@ @ @@@@@    @  @@@@@@@@@@    ");  
		System.out.println("                            @@@@@@ ,  @@  @ @@@@@@@@@@%   ");  
		System.out.println("                                  @@@ @   @ @@@@@@@@@@    ");   
		System.out.println("                                   @#@@@@ @    @@         ");  

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          "); 
		System.out.println("			                                              ");               
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                          @             @ ");  
		System.out.println("                                           @            @ ");  
		System.out.println("                                            @         @   ");  
		System.out.println("                                             @         @  ");  
		System.out.println("                                                        % ");  
		System.out.println("                                         @              @ ");  
		System.out.println("                                          @     @@@@@@   @");  
		System.out.println("                             @@            @  @@@@@@@@@   "); 
		System.out.println("                                  @@          @@@@@@@@@@  ");  
		System.out.println("                                              @@@@@@@@@@  ");  
		System.out.println("                                               /@@@@@@@   ");  
		System.out.println("                                      ,,                  ");  
		System.out.println("                                                        @ ");  
		System.out.println("                                                    @     ");  
		System.out.println("                                               %@@,       ");  
		System.out.println("                                                      @   ");  

		Thread.sleep(200);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          "); 
		System.out.println("			                                              ");              
		System.out.println("                                        @                 ");  
		System.out.println("                                         @                ");  
		System.out.println("                              @                           ");  
		System.out.println("                                @         @               ");  
		System.out.println("                                                          ");  
		System.out.println("                   @@,                           @  @     ");  
		System.out.println("                         @@         @@@           @  @    ");  
		System.out.println("                                  @@@@@@@@  @       @ @   ");  
		System.out.println("                                 @@@@@@@@@    @      @    ");  
		System.out.println("                                 @@@@@@@@@@     @      #  ");  
		System.out.println("                         @@       @@@@@@@@        @     @ ");  
		System.out.println("                                    %&&             @     ");  
		System.out.println("                                                      @   ");  
		System.out.println("                         ,@@@@@                         @ ");  
		System.out.println("                                          @@              ");  
		System.out.println("                                               @          ");  
		System.out.println("                                   @@@@@@,                ");  
		System.out.println("                                                @@@       ");  
		
		
		Thread.sleep(200);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");
		System.out.println("                                                          "); 
		System.out.println("                   @                                      "); 
		System.out.println("                     @          @@    @                   "); 
		System.out.println("              @              @     @                      "); 
		System.out.println("                              @      @                    "); 
		System.out.println("                                @      @     @       %    "); 
		System.out.println("    @@@            @@@@@@@        @*     #@   @      @    "); 
		System.out.println("                  @@@@@@@@@                 @   @         "); 
		System.out.println("                  @@@@@@@@@%                  @       @   "); 
		System.out.println("                   @@@@@@@@        @            @     ,   "); 
		System.out.println("                    @@@@@             @           @       "); 
		System.out.println("                                        @@          @     "); 
		System.out.println("                               @@                         "); 
		System.out.println("                                    @@        @.          "); 
		System.out.println("                           *@@           &       @@       "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                         @@@              "); 
		System.out.println("                                                @@        "); 
		System.out.println("                                                          "); 			
		System.out.println("														  ");			

		Thread.sleep(200);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
		System.out.println("                                               		  ");
		System.out.println("                                               		  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("           @                          @                   "); 
		System.out.println("             @               @         @                  "); 
		System.out.println("    @@@@&                      *@        @                "); 
		System.out.println("  @@@@@@@@@                       @       *#              "); 
		System.out.println("  @@@@@@@@@         @                @      @             "); 
		System.out.println("  @@@@@@@@@          @        @@       @@    *@           "); 
		System.out.println("   @@@@@@@@            @          @       @    @          "); 
		System.out.println("                                            @    @        "); 
		System.out.println("                                              @@   @      "); 
		System.out.println("        @@@@,         @                          @  @#    "); 
		System.out.println("@@            @@@@@@@@@@@                                 "); 
		System.out.println("                               %@@                        "); 
		System.out.println("                                     %@*                  "); 
		System.out.println("   @@@@@@@@@@@@@@@@@@%                    @               "); 
		System.out.println("                             @@@             @            "); 
		System.out.println("                                    @@                    "); 
		System.out.println("                           ,@@@@@       @@                "); 
		System.out.println("                                            @             "); 
		System.out.println("                                              @@          "); 
		System.out.println("                                                          "); 

		Thread.sleep(200);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("   @         @           @                                "); 
		System.out.println("              @            @@                             "); 
		System.out.println("    @@@@&      @              @               @           "); 
		System.out.println("  @@@@@@@@@     @               @#             @          "); 
		System.out.println("  @@@@@@@@@       @                @            @         "); 
		System.out.println("  @@@@@@@@@                          @           @        "); 
		System.out.println("   @@@@@@@@                             @                 "); 
		System.out.println("                                 @        @               "); 
		System.out.println("             @@@@@@@@              @@       @             "); 
		System.out.println("  @%                      @@@                             "); 
		System.out.println("                                *@@                   @   "); 
		System.out.println("                      %@@@,                            @  "); 
		System.out.println("                              @@.                         "); 
		System.out.println("             @@@@@       @@@@&     @@                     "); 
		System.out.println("       @                        @      @                  "); 
		System.out.println("                                                   @@     "); 
		System.out.println("                                                          "); 
		System.out.println("                  %@@@@@@@@@@@@@,                         "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 			
		
		Thread.sleep(100);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();								
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                               		  ");
		System.out.println("                                               		  ");
		System.out.println("                                              			  ");
        System.out.println("                                   @@@@@           	  ");
		System.out.println("                                   @@@@@@         		  ");
		System.out.println("                                   @@@@         		  ");
        System.out.println("                                              			  ");
		System.out.println("                    @@@@                                  ");
		System.out.println("                    @@@@@@@.                              "); 
		System.out.println("                    @@  @@@@@@                            ");
		System.out.println("                    @@@@@@   @@@@*                        "); 
		System.out.println("                    @@@@        @@@@(                     ");  
		System.out.println("                    @@             @@@@*                  ");  
		System.out.println("                    @@          .%%%%&@@@@                ");  
		System.out.println("                    @@@@@@@@@@@@@@@@@@@@@@@               ");  
		System.out.println("                    @@,                                   ");  
		System.out.println("       &@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@           ");  
		System.out.println("        @@@                                %@@            ");  
		System.out.println("        (@@                          @@@@@@@@@@@          ");  
		System.out.println("         @@@@@@@@@@@@@             @@@@@@@@@@@@@@@&       ");  
		System.out.println("        @@@@@@@@@@@@@@@@@@       @@@@@@@@@@@@@@@@@@@      ");  
		System.out.println("   ***@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@*"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                               		  ");
		System.out.println("                                               		  ");
		System.out.println("                                   @@@@@@@    			  ");			
		System.out.println("			                      @@@@@@@@                ");             
		System.out.println("                      @@@@@       @@@@@@@@@               "); 
		System.out.println("                      @@@@@@@*     @@@@@@@                "); 
		System.out.println("                      @@  @@@@@@                          "); 
		System.out.println("                     @@@@@@@  @@@@                        "); 
		System.out.println("                     @@@@       @@@@                      "); 
		System.out.println("                     @@           @@@@                    "); 
		System.out.println("                    @@@@@@@         #@@@                  "); 
		System.out.println("        @@@.        @@@*@@@@@@@@@@@@@@@@@@                "); 
		System.out.println("       #@@@@@@@@@@@@@@@%            ,@@@@@@               "); 
		System.out.println("        @@            @@@@@@@@@@@@@@                      "); 
		System.out.println("        @@@                       ,@@@@@@@@@@@            "); 
		System.out.println("        @@@                                @@@ @@@@@@@    "); 
		System.out.println(" @@@@@@@@@@              @@@@@@@@@        @@@@@@@@@@@@@@ @");
		System.out.println("@@@@@@@@@@@@@@@@     @@@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                               		  ");
		System.out.println("                                 ||              		  ");
		System.out.println("                                 ||              		  ");
		System.out.println("              \\                 ||                 	  ");
        System.out.println("               \\                ||          //    	  ");
		System.out.println("                \\               ||         //    		  ");
		System.out.println("                 \\                        //     		  ");
		System.out.println("      \\          \\            @@        //              ");                             
		System.out.println("       \\                    @@@@@       //               ");  
		System.out.println("        \\                  @@ %@@@     //                ");  
		System.out.println("         \\                @@@  @@@@                      ");  
		System.out.println("          \\             @@@@@@@ (@@@                     ");  
		System.out.println("                       @@@        @@@                     ");  
		System.out.println("         @@@@         @@@          @@@                    ");  
		System.out.println("         @@ @@@@@    .@@@@@@@       @@@                   "); 
		System.out.println("        @@@     .@@@@@@.   @@@@@@@    @@@                 ");  
		System.out.println("        @@@          @@@@@@     @@@@@@*@@@                ");  
		System.out.println("        @@&               @@@@@%     @@@@@@               ");  
		System.out.println("        @@@@,                  @@@@@@@&   @               ");  
		System.out.println("     @@@@@@@@@@@@            @@@@@@@@@@@@                 ");  
		System.out.println("    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      @@@#     ");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");       
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		
		Thread.sleep(200);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();	
		
		System.out.println("               **               **                   	**");
		System.out.println("                \\              ||                **   // ");
		System.out.println("                 \\             ||               //  //   ");
        System.out.println("   **             \\            ||              //  //	  ");
		System.out.println("    \\             \\           ||             //   //	  ");
		System.out.println("     \\             \\          ||            //   //	  ");
		System.out.println("      \\             \\         ||           //   //	  ");
        System.out.println("       \\             \\        ||          //   // 	  ");
		System.out.println("        \\             \\       ||         //   //		  ");
		System.out.println("         \\             \\                //   //   	  ");
		System.out.println("          \\             \\   @@         //   //          ");                             
		System.out.println("           \\               @@@@@       //   //           ");  
		System.out.println("            \\             @@ %@@@     //   //            ");  
		System.out.println("             \\           @@@  @@@@        //             ");  
		System.out.println("              **        @@@@@@@ (@@@      //              ");  
		System.out.println("                       @@@        @@@                     ");  
		System.out.println("         @@@@         @@@          @@@                    ");  
		System.out.println("         @@ @@@@@    .@@@@@@@       @@@                   "); 
		System.out.println("        @@@     .@@@@@@.   @@@@@@@    @@@                 ");  
		System.out.println("        @@@          @@@@@@     @@@@@@*@@@                ");  
		System.out.println("        @@&               @@@@@%     @@@@@@               ");  
		System.out.println("        @@@@,                  @@@@@@@&   @               ");  
		System.out.println("     @@@@@@@@@@@@            @@@@@@@@@@@@                 ");  
		System.out.println("    @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      @@@#     ");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");       
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		
		Thread.sleep(200);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                **             **               ** 	  ");
		System.out.println("   **            \\            ||              //    	  ");
		System.out.println("    \\            \\           ||             //     	  ");
		System.out.println("     \\            \\          ||            //      	  ");
        System.out.println("      \\            \\         ||           //      	**");
		System.out.println("       \\            \\        ||          //          // ");
		System.out.println("        \\            \\       ||         //          //  ");
		System.out.println("         \\            \\      ||        //          //   ");
        System.out.println("          \\            \\     ||       //          //	  ");
		System.out.println("           \\            \\    ||      //          //	  ");
		System.out.println("            \\            \\          //          //      ");			                                                           
		System.out.println("             \\                      //          //       "); 
		System.out.println("              \\             @@@@   //          //        "); 
		System.out.println("               \\           @@@@@@             //         "); 
		System.out.println("                \\         @@@  @@@@          //          "); 
		System.out.println("                 \\        @@@@@@@@@@@       //           "); 
		System.out.println("                         @@@@@     @@@      //            "); 
		System.out.println("                        @@         *@@@    //             "); 
		System.out.println("           @           @@@@          @@@                  "); 
		System.out.println("          @@@@@@@@    @@@@@@@@@@@      @@@                "); 
		System.out.println("          @@     @@@@@@@@      @@@@@@@@@@@@@              "); 
		System.out.println("  @@@@@@, @@           .@@@@@@@       @@@@@@@             "); 
		System.out.println("@@@@@@@@@@@@@                 @@@@@@@(           @@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@          @@@@@@@@@&@@@@@@@  @@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@    @@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
        System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                                          ");                                                 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  			                                                           
		System.out.println("                                                          ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                          @@@                             "); 
		System.out.println("                         @@@@@                            "); 
		System.out.println("                        @@@ @@@@                          "); 
		System.out.println("                        @@ .@@@@@                         "); 
		System.out.println("                       @@@@@@   @@@                       "); 
		System.out.println("                      @@@#       @@@@                     "); 
		System.out.println("                      @@           @@@                    "); 
		System.out.println("   @@@@ @@@@@@.      @@@@@@@@        @@@                  "); 
		System.out.println("@@@@@@@@@@@# @@@@@@@@@@    @@@@@@@@@  @@@.         &@@@@@@");
		System.out.println("@@@@@@@@@@@@@@      @@@@@@@       @@@@@@@@@      @@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@       @@@@@@@@@@@     @@@#   @@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@     @@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                               		  ");
		System.out.println("                                               		  ");
		System.out.println("                                               		  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
        System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                                          ");                                                 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                  @@@                     ");  
		System.out.println("                                @@@@@@                    ");  
		System.out.println("                               @@@ @@@                    ");  
		System.out.println("             @@@             @@@ @@@@@@                   ");  
		System.out.println("            @@@@@@         @@@@@@@@@@@@                   ");  
		System.out.println("           @@@   @@@@     @@@        @@@                  ");  
		System.out.println("          @@@       @@@ @@@@@@        @@&                 ");  
		System.out.println("     @@@@@@@@@        @@@@   @@@@@@@  @@@         @@@@    ");  
		System.out.println("   @@@@@@@@@@@@@@        @@@*@@@@@@@@@@@@@    @@@@@@@@@@@@");  
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@   @@@@@@@@@@@@@@@@@@*@@@@@@@@@@@@@@");     
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");     
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");     
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");     
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");     
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");  	

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
        System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                                          ");     			                                                            
		System.out.println("                                                          ");
		System.out.println("                                   @*                     ");
		System.out.println("                                .@@@@                     ");
		System.out.println("                               @@@ @@@                    ");
		System.out.println("             @@              @@@@  @@@                    ");
		System.out.println("            @@@@@@         @@@@@@@@@@@@                   "); 
		System.out.println("           @@@   @@@      @@@        @@@        @@@@@@@   "); 
		System.out.println("         @@@@@@@@@@@@@/ @@@@@    @@@@@@@   *@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@  @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
					
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
        System.out.println("                                                          "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                                                          ");     			                                                                                                
		System.out.println("                           @@                             "); 
		System.out.println("                          @@@@@                           "); 
		System.out.println("                         %@@ @@@                          "); 
		System.out.println("                         @@@  @@@@                        "); 
		System.out.println("                        @@@@@@@ @@@@     #@@@@@@@@        "); 
		System.out.println("@@         @@@@@@@@     @@@@      @@@  @@@@@@@@@@@@@@   #@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@#@@@          @@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                  @@@@                    "); 
		System.out.println("                                @@@@@@      @@@@@@@@      "); 
		System.out.println("                 %@@@@@@@@     @@@( @@@  @@@@@@@@@@@@@@@* ");    
		System.out.println("   @@        @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");   
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 	

		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();		

		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");                                                                                                      
        System.out.println("                                              			  "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  
		System.out.println("                    @@@@@.                  @@@@@@@@@     ");  
		System.out.println("@@@@@          @@@@@@@@@@@@@@             @@@@@@@@@@@@@   "); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&     @@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"); 
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");                                                                                                      
        System.out.println("                                              			  "); 
		System.out.println("                                                          ");  
		System.out.println("                                                          ");  			                                                                                                                                                                  
		System.out.println("              %                  @@@@@@@@@                "); 
		System.out.println("@@@       @@@@@@@@@@            @@@@@@@@@@@@              "); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@    &@@@@@@@@@@@@@@@@@            "); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@         "); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          ");                                                                                                      
        System.out.println("                                              			  ");                                                           
		System.out.println("@@                @@@@@@                  ,,              ");
		System.out.println("@@@@@         @@@@@@@@@@@@@         @@@@@@@@@@@@@         "); 
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@      @");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");		
		
		Thread.sleep(300);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                                          "); 
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");
		System.out.println("                                              			  ");
		System.out.println("                                              			  ");
        System.out.println("                                              			  ");    

		Thread.sleep(100);
		new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();		 	
			
	}
	
	/**
	 *
	 * Shows the screen of the gamemode, where the player can choose if he wanna play the current game by himself or in a multiplayer mode
	 *
	 */
	public void showMainMenu() {
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||                    Waehle einen Spielmodi!                  ||");
		System.out.println("||          ___________________       _________________        ||");
		System.out.println("||         |                   |     |                 |       ||");
		System.out.println("||         | Einzelspieler [E] |     | Mehrspieler [M] |       ||");
		System.out.println("||         |___________________|     |_________________|       ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}
	
	/**
	 *
	 * Shows the screen where the player can choose the difficulty of the current game
	 * The console will get cleared before
	 *
	 */	
	public void showSettings() {
		try {
			new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
		} catch (InterruptedException | IOException e) {
		}
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||               Wie stark soll Dein Gegner sein?              ||");
		System.out.println("||        _____________    ____________    ____________        ||");
		System.out.println("||       |             |  |            |  |            |       ||");
		System.out.println("||       | Einfach [E] |  | Schwer [S] |  | Extrem [X] |       ||");
		System.out.println("||       |_____________|  |____________|  |____________|       ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}
	
	/**
	 *
	 * Shows the screen for the ship positioning, where the player can choose if he wanna place the ships by random,
	 * by himself and start the game
	 *
	 */
	public void showShipPositioning() {
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
	 *
	 * Shows the screen with help settings
	 *
	 */
	public void showHelp() {
		System.out.println();
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		System.out.println("||                     Du brauchst Hilfe?!                     ||");
		System.out.println("||    --------------------------------------------------       ||");
		System.out.println("||    ~ ==>> Kein Treffer, du hast ins Wasser geschossen       ||");
		System.out.println("||                                                             ||");
		System.out.println("||    o ==>> Hier befindet sich dein eigenes Schiff            ||");
		System.out.println("||                                                             ||");
		System.out.println("||    x ==>> Du hast einen Teil des Schiffes getroffen         ||");
		System.out.println("||                                                             ||");
		System.out.println("||    X ==>> Schiff komplett versenkt                          ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}
	
	/**
	 *
	 * Shows the screen for the hit rate
	 *
	 */
	public void showStatistic(int totalShots, int hits, int noHits, double successRate) {
		System.out.println();
		System.out.println("//=============================================================\\\\");
		System.out.println("                                                               ");
		System.out.println("               	       Deine Trefferquote!         		       ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Anzahl Versuche:     ||  " + totalShots + "          ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Anzahl Treffer:      ||  "  + hits + "               ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Anzahl Fehlversuche: ||  " + noHits + "              ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("          Trefferquote:        ||  " + successRate + "%        ");
		System.out.println("         -----------------------------------------------       ");
		System.out.println("                                                               ");
		System.out.println("\\\\=============================================================//");
	}
	
	/**
	 *
	 * Shows the screen if the player have won or lost
	 *
	 */	
	public void showEndScreen(boolean winner) {
		System.out.println("//=============================================================\\\\");
		System.out.println("||                                                             ||");
		
		if(winner) {
			System.out.println("||                Glueckwunsch! Sie haben gewonnen!            ||");
			System.out.println("||                                                             ||");
			System.out.println("\\\\=============================================================//");
			Toolkit.getDefaultToolkit().beep();
			return;
		}

		System.out.println("||                  Game Over! Du hast verloren!               ||");
		System.out.println("||                                                             ||");
		System.out.println("\\\\=============================================================//");
	}
	
	/**
	 *
	 * Shows the message when the player is waiting for the game or if an error has occurred
	 * For the certain case it appeares in differnt styles
	 *
	 */
	public void print(String msg, String style) {
		switch(style) {
		case "error":
			System.out.println("<error> " + msg + " <error>");
			break;
		case "waiting":
			System.out.println("<waiting> " + msg + " <waiting>");
			break;
		case "highlight":
			System.out.println("==> " + msg);
			break;
		default:
			System.out.println(msg);
			break;
		}
	}
	
	/**
	 *
	 * Overload method with standart case 
	 *
	 */	
	public void print(String msg) {
		this.print(msg, "normal");
	}
	
	/**
	 *
	 * Shows the message for the second player
	 *
	 */
	public void showWaitingForSecondPlayer() {
		System.out.println("Warte auf zweiten Spieler...");
	}
	
	/**
	 *
	 * Shows the messsage that the second player has connected
	 *
	 */
	public void showSecondPlayerConnected() {
		System.out.println("Ein zweiter Spieler ist dem Spiel beigetreten!");
	}

}
