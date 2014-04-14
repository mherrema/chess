package gvprojects.chess.presenter;

import gvprojects.chess.model.Model;
import gvprojects.chess.model.Move;
import gvprojects.chess.view.View;
import java.util.Scanner;

/*********************************************************************
 * This class presents a chess game, calling appropriate methods when necessary.
 * 
 * @author Mitch Herrema
 * @version March 21, 2013
 *********************************************************************/

public class Present {
	/** final variables for numbers. */
	private final int three = 3;
	/** final variables for numbers. */
	private final int four = 4;
	/** final variables for numbers. */
	private final int five = 5;

	/** input. */
	private String moveString;

	/** model. */
	private Model m;

	/** view. */
	private View v;

	/** scanner for input. */
	private Scanner in;

	/******************************************************************
	 * Presenter Constructor.
	 * 
	 ******************************************************************/
	public Present() {
		moveString = "";
		m = new Model();
		v = new View();
		in = new Scanner(System.in);
	}

	/******************************************************************
	 * Updates the gameboard.
	 * 
	 ******************************************************************/
	public final void update() {
		v.printBoard(m.getBoard());
		// if they put themselves in check
		if (m.isPutSelfInCheck()) {
			v.putSelfInCheck();
			m.setPutSelfInCheck(false);
		}
		// if they put the other player in check
		if (m.isOtherPlayerCheck()) {
			v.inCheck();
			m.setOtherPlayerCheck(false);
		}
		v.prompt(m.currentPlayer());
		moveString = in.nextLine();

		// while the input is invalid
		while (!isValidInput(moveString)) {
			v.invalidInput();
			v.prompt(m.currentPlayer());
			moveString = in.nextLine();
		}

		int fromRow = Math
				.abs(Integer.parseInt(moveString
						.substring(1, 2)) - m.getBoard().length);
		int fromCol = moveString.substring(0, 1)
				.toUpperCase().charAt(0) - 'A';
		int toRow = Math.abs(Integer.
				parseInt(moveString.substring(four, five)) 
				- m.getBoard().length);
		int toCol = moveString.substring(three, four)
				.toUpperCase().charAt(0) - 'A';
		Move move = new Move(fromRow, fromCol, toRow, toCol);

		m.move(move);
		update();
	}

	/** 
	 * Determines whether or not it is valid input.
	 * 
	 * @param s String
	 * @return boolean
	 */
	public final boolean isValidInput(final String s) {
		boolean fromRow = false;
		boolean fromCol = false;
		boolean toRow = false;
		boolean toCol = false;
		boolean space = false;
		String[] letters = new String[] { "a", "b", "c", "d", "e", "f", "g",
				"h" };
		String[] numbers = new String[] { "1", "2", "3", "4", "5", "6", "7",
				"8" };
		// if length is too short
		if (s.length() < five) {
			return false;
		}
		// if length too long
		if (s.length() > five) {
			return false;
		}
		// runs through all letters/numbers in arrays
		for (int i = 0; i < letters.length; i++) {
			// if first character is valid
			if (s.substring(0, 1).toLowerCase().equals(letters[i])) {
				fromRow = true;
			}
			// if fourth character is valid
			if (s.substring(three, four).toLowerCase().equals(letters[i])) {
				toRow = true;
			}
			// if second character is valid
			if (s.substring(1, 2).equals(numbers[i])) {
				fromCol = true;
			}
			// if fifth character is valid
			if (s.substring(four, five).equals(numbers[i])) {
				toCol = true;
			}
		}
		// if middle character is space
		if (s.substring(2, three).equals(" ")) {
			space = true;
		}
		// if string meets all criteria
		if (fromRow && fromCol && toRow && toCol && space) {
			return true;
		}
		return false;
	}

	/*****************************************************
	 * Main method.
	 * 
	 * @param args String[]
	 ****************************************************/
	public static void main(final String[] args) {
		Present p = new Present();
		p.update();
	}

}
