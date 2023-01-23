package view.console;

public class Playground {

	/**
	 * Shows a matchfield in the console
	 * 
	 * @param fieldsize - the size of the match
	 * @param status
	 */
	public void print(String[][] status) {

		StringBuffer buffer = new StringBuffer();
		buffer.append("   ");

		int aDir = status[0].length; // X-coordinate
		int bDir = aDir; // Y-coordinate

		StringBuffer distToChar = new StringBuffer();
		String yCoordNumber = ""; // string for the numbers of the Y-coordinates

		// Formating the Output as String
		for (int i = 0; i < aDir; i++) { // alphabet of the X-coordinates
			distToChar.append(" " + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i) + "    ");
		}
		System.out.println(distToChar.toString());

		for (int i = 0; i < bDir; i++) { // sting to generate the numbers of the Y-coordinates
			yCoordNumber = yCoordNumber + Integer.toString(i);
		}

		for (int i = 0; i < aDir; i++) { // top row
			buffer.append(" _____");
		}
		buffer.append("\n"); // NOPMD by Lorenz on 23.01.23, 15:36

		for (int i = 0; i < bDir; i++) {
			buffer.append("   "); // structure of the fields
									// _____ --> top row
			for (int j = 0; j < aDir; j++) { // 1/3 of a field // | | --> 1/3
												// | X | --> 2/3 X --> symbol if hit,...
				buffer.append("|     "); // |_____| --> 3/3
			}

			buffer.append("|" + "\n");

			if (i + 1 < 10) {
				buffer.append((i + 1) + "  "); // imports the numbers of the Y-coordinate into the String of the
												// matchfield
			} else {
				buffer.append((i + 1) + " "); // imports the numbers of the Y-coordinate into the String of the
												// matchfield
			}

			for (int j = 0; j < aDir; j++) { // 2/3 of a field
				buffer.append("|  " + status[i][j] + "  "); // symbol if a ship was hit,...
			}
			buffer.append("|" + "\n" + "   ");

			for (int j = 0; j < aDir; j++) { // 3/3 of a field
				buffer.append("|_____");
			}
			buffer.append("|" + "\n"); // new line
		}

		System.out.println(buffer.toString());
	}

}
