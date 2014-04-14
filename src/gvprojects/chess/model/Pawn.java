/**
 *  File:               Pawn.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code Pawn} {@code ChessPiece}.
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Pawn extends ChessPiece {
	
	/** starting row. */
	private int startRow;
	
	/** player. */
	private Player p;
	
	/******************************************************************
	 * Pawn Constructor.
	 * Creates a pawn
	 *  chess piece
	 * 
	 * @param ap Player
	 * @param astartRow int
	 ******************************************************************/
	public Pawn(final Player ap, final int astartRow) {
		super(ap, "Pawn");
		this.startRow = astartRow;
		this.p = ap;
	}
	
	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param m Move
	 * @param board IChessPiece[][]
	 * 
	 * @return boolean
	 ******************************************************************/
	
	@Override
	public final boolean isValidMove(final Move m, 
			final IChessPiece[][] board) {
		int rowChange;
		//System.out.println("from col " + m.fromColumn + 
		//						" to col "+ m.toColumn);
		int colChange = Math.abs(m.fromColumn() - m.toColumn());
		
		//if the move as a Chesspiece is invalid
		if (!super.isValidMove(m, board)) {
			//System.out.println("super");
			return false;
		}
		
		//if starting from row one
		if (startRow == 1) {
			rowChange = m.toRow() - m.getfromRow();
		} else {
			rowChange = m.getfromRow() - m.toRow();
		}
		//System.out.println("row change = " + rowChange);
		//System.out.println("col change = " + colChange);
			//if moving two rows, no columns, and moving from starting row
			if (rowChange == 2 && m.getfromRow() == startRow 
					&& colChange == 0 
					&& board[m.toRow()][m.toColumn()] == null) {
				return true;
			}
			//if moving diagonal and other player is diagonal
			if (rowChange == 1 && colChange == 1 
					&& board[m.toRow()][m.toColumn()] != null 
					&& board[m.toRow()][m.toColumn()].player() != p) {
				return true;
				
			}
			//if moving one row and no columns
			if (rowChange == 1 && colChange == 0 
					&& board[m.toRow()][m.toColumn()] == null) {
				return true;
			}
		return false;
	}
}
