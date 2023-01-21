package view;

public class Matchfield {

	private final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int fieldsize;

	public Matchfield(int fieldsize) {
		this.fieldsize = fieldsize;
	}

	public void show(String[][] status) {

		// Function for writing the matchfield on the console
		int a = this.fieldsize; // X-coordinate
		int b = this.fieldsize; // Y-coordinate

		String rand = "   ";// distance from the left edge to the top row of a field
		String x = "     "; // distance from the left edge to A
		String y = ""; // string for the numbers of the Y-coordinates

		// Formating the Output as String
		for (int i = 0; i < a; i++) { // alphabet of the X-coordinates
			x = x + " " + alphabet.charAt(i) + "    ";
		}
		System.out.println(x);

		for (int i = 0; i < b; i++) { // sting to generate the numbers of the Y-coordinates
			y = y + Integer.toString(i);
		}

		for (int i = 0; i < a; i++) { // top row
			rand = rand + " _____";
		}
		rand = rand + "\n";

		for (int i = 0; i < b; i++) {
			rand = rand + "   "; // structure of the fields
									// _____ --> top row
			for (int j = 0; j < a; j++) { // 1/3 of a field // | | --> 1/3
											// | X | --> 2/3 X --> symbol if hit,...
				rand = rand + "|     "; // |_____| --> 3/3
			}

			rand = rand + "|" + "\n";

			if (i + 1 < 10) {
				rand = rand + (i + 1) + "  "; // imports the numbers of the Y-coordinate into the String of the
												// matchfield
			} else {
				rand = rand + (i + 1) + " "; // imports the numbers of the Y-coordinate into the String of the
												// matchfield
			}

			for (int j = 0; j < a; j++) { // 2/3 of a field
				rand = rand + "|  " + status[i][j] + "  "; // symbol if a ship was hit,...
			}
			rand = rand + "|" + "\n" + "   ";

			for (int j = 0; j < a; j++) { // 3/3 of a field
				rand = rand + "|_____";
			}
			rand = rand + "|" + "\n"; // new line
		}

		System.out.println(rand);
	}

}
