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
	 * @param pfromRow final int
	 * @param pfromColumn final int
	 * @param ptoRow final int
	 * @param ptoColumn final int
	 ******************************************************************/
	public Move(final int pfromRow, final int pfromColumn, 
			final int ptoRow, final int ptoColumn) {
		this.fromRow = pfromRow;
		this.fromColumn = pfromColumn;
		this.toRow = ptoRow;
		this.toColumn = ptoColumn;
	}
}