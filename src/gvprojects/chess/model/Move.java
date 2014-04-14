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
	private int fromRow;
	/** Initialize the fromColumn. */
	private int fromColumn;
	/** Initialize the toRow. */
	private int toRow;
	/** Initialize the toColumn. */
	private int toColumn;
	
	/** returns the fromRow variable.
	 * 
	 * @return fromRow
	 */
	public final int getfromRow() {
		return fromRow;
	}
	
	/** returns the fromColumn variable.
	 * 
	 * @return fromColumn
	 */
	public final int fromColumn() {
		return fromColumn;
	}
	
	/** returns the toRow variable.
	 * 
	 * @return toRow
	 */
	public final int toRow() {
		return toRow;
	}
	
	/** returns the toColumn variable.
	 * 
	 * @return toColumn
	 */
	public final int toColumn() {
		return toColumn;
	}

	/******************************************************************
	 * The logic for moving a chess piece.
	 * 
	 * @param afromRow final int
	 * @param afromColumn final int
	 * @param atoRow final int
	 * @param atoColumn final int
	 ******************************************************************/
	public Move(final int afromRow, final int afromColumn, 
			final int atoRow, final int atoColumn) {
		this.fromRow = afromRow;
		this.fromColumn = afromColumn;
		this.toRow = atoRow;
		this.toColumn = atoColumn;
	}
}