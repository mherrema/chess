/**
 *  File:               Move.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/******************************************************************
 * This class is the logic for moving a chess piece on a board.
 ******************************************************************/
public class Move {
	
	/** Initialize the fromRow. */
	public int fromRow;
	/** Initialize the fromColumn. */
	public int fromColumn;
	/** Initialize the toRow. */
	protected int toRow;
	/** Initialize the toColumn. */
	protected int toColumn;

	/******************************************************************
	 * The logic for moving a chess piece.
	 * 
	 * @param fromRow final int
	 * @param fromColumn final int
	 * @param toRow final int
	 * @param toColumn final int
	 ******************************************************************/
	public Move(final int fromRow, final int fromColumn, 
			final int toRow, final int toColumn) {
		this.fromRow = fromRow;
		this.fromColumn = fromColumn;
		this.toRow = toRow;
		this.toColumn = toColumn;
	}
}