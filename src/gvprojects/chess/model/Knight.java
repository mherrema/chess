/**
 *  File:               Knight.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code Knight} {@code ChessPiece}.
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Knight extends ChessPiece {

	/******************************************************************
	 * Knight Constructor.
	 * Creates a knight chess piece
	 * 
	 * @param p Player
	 ******************************************************************/
	public Knight(final Player p) {
		super(p, "Knight");
	}

	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param m - Move
	 * @param board - IChessPiece[][]
	 * @return boolean
	 ******************************************************************/
	public final boolean isValidMove(final Move m, 
			final IChessPiece[][] board) {
		//if the move as a Chesspiece is invalid
		if (!super.isValidMove(m, board)) {
			return false;
		}
		//if moving one row and two columns or one column and two rows
		if ((Math.abs(m.toRow() - m.getfromRow()) == 1 && Math.abs(m.toColumn()
				- m.fromColumn()) == 2)
				|| ((Math.abs(m.toRow() - m.getfromRow()) == 2 
				&& Math.abs(m.toColumn()
						- m.fromColumn()) == 1))) {
			return true;
		}
		return false;
	}
}
