package gvprojects.chess.view;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Player;
import java.io.PrintStream;

/*********************************************************************
 * This class visualizes the chess game.
 * 
 * @author Mitch Herrema
 * @version March 21, 2013
 *********************************************************************/


public class View {
	/** opens a PrintStream. */
	private PrintStream out = System.out;
	/** Constant number variables. */
	private final int eight = 8;

	/******************************************************************
	 * View constructor.
	 * 
	 * @param none
	 ******************************************************************/
	public View() {

	}

	/******************************************************************
	 * Prints the board.
	 * 
	 * @param board IChessPiece[][]
	 ******************************************************************/
	public final void printBoard(final IChessPiece[][] board) {
		out.println("\n      A    B    C    D    E    F    G    H  ");
		out.println("   *****************************************");
		// runs down rows
		for (int i = eight; i > 0; i--) {
			out.print(i + "  *");
			// runs over columns
			for (int c = 0; c < eight; c++) {
				// prints empty
				if (board[Math.abs(i - eight)][c] == null) {
					out.print("    *");
				} else {
					out.print(" " + pieceToString(board[Math.abs(i - eight)][c])
							+ " *");
					
				}
			}
			out.println();
			out.println("   *****************************************");
		}
		out.println("     A    B    C    D    E    F    G    H  ");
	}

	/******************************************************************
	 * Returns the shorthand string of the piece.
	 * 
	 * @param p IChessPiece
	 * @return String name
	 ******************************************************************/
	public final String pieceToString(final IChessPiece p) {
		String name = "";
		name += p.player().name().charAt(0);
		name += p.type().charAt(0);
		return name;
	}

	/******************************************************************
	 * Prompts for input.
	 * 
	 * @param p Player
	 ******************************************************************/
	public final void prompt(final Player p) {
		out.print(p.name() + ", please enter a move: ");
	}

	/******************************************************************
	 * Displays if put self in check.
	 * 
	 ******************************************************************/
	public final void putSelfInCheck() {
		out.println("You can't place yourself in check.");
	}

	/******************************************************************
	 * Displays if player has been placed in check.
	 * 
	 ******************************************************************/
	public final void inCheck() {
		out.println("You have been placed in check!");
	}

	/******************************************************************
	 * Displays if invalid input.
	 * 
	 ******************************************************************/
	public final void invalidInput() {
		out.println("Command not recognized. Please try again.");
	}
}
