package view.console;

public class Playground {

	/**
	 * Shows a matchfield with all field states
	 * 
	 * @param status - the status array containg all informations about the
	 *               matchfield. (from Matchfield.getStatusArray())
	 */
	public void print(String[][] status) {

		StringBuffer buffer = new StringBuffer(43);
		buffer.append("   ");

		int aDir = status[0].length; // X-coordinate
		int bDir = aDir; // Y-coordinate
		int fieldsize = aDir;

		StringBuffer distToChar = new StringBuffer(36);
		distToChar.append("     ");

		// Formating the Output as String
		for (int i = 0; i < aDir; i++) { // alphabet of the X-coordinates
			distToChar.append(" " + "ABCDEFGHIJKLMNOPQRSTUVWXYZ".charAt(i) + "    ");
		}
		System.out.println(distToChar.toString());

		for (int i = 0; i < aDir; i++) { // top row
			buffer.append(" _____");
		}
		buffer.append('\n');

		for (int i = 0; i < bDir; i++) {
			buffer.append("   "); // structure of the fields
									// _____ --> top row
			for (int j = 0; j < aDir; j++) { // 1/3 of a field // | | --> 1/3
												// | X | --> 2/3 X --> symbol if hit,...
				buffer.append("|     "); // |_____| --> 3/3
			}

			buffer.append("|" + "\n");

			if (i + 1 < fieldsize) {
				buffer.append((i + 1) + "  "); // imports the numbers of the Y-coordinate into the String of the
												// matchfield
			} else {
				buffer.append((i + 1) + " "); // imports the numbers of the Y-coordinate into the String of the
												// matchfield
			}

			for (int j = 0; j < aDir; j++) { // 2/3 of a field
				buffer.append("|  ");
				buffer.append(status[i][j]); // symbol if a ship was hit,...
				buffer.append("  ");
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
