package gvprojects.chess.model;

/*
 * This class is the logic for moving a chess piece on a board
 */
public class Move {
<<<<<<< HEAD
	/*
	 * Initialize the variables
	 */
	public int fromRow, fromColumn, toRow, toColumn;

	/*
	 * The logic for moving a chess piece
	 */
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
=======
	
	/** Initialize the variables. */
	@SuppressWarnings("unused")
	protected int fromRow, fromColumn, toRow, toColumn;

	/******************************************************************
	 * The logic for moving a chess piece.
	 ******************************************************************/
	public Move(final int fromRow, final int fromColumn, 
			final int toRow, final int toColumn) {
>>>>>>> parent of 9282353... Checkstyle Fixes
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}