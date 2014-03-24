package gvprojects.chess.model;

/*
 * This class is the logic for moving a chess piece on a board
 */
public class Move {
	/*
	 * Initialize the variables
	 */
	public int fromRow, fromColumn, toRow, toColumn;

	/*
	 * The logic for moving a chess piece
	 */
	public Move(int fromRow, int fromColumn, int toRow, int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}