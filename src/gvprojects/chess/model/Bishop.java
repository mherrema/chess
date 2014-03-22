package gvprojects.chess.model;
/**
 *  File:               Bishop.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */


/*********************************************************************
 * This class creates a {@code Bishop} {@code ChessPiece}.
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Bishop extends ChessPiece {
	/******************************************************************
	 * Bishop Constructor Creates a bishop chess piece.
	 * 
	 * @param p Player
	 *****************************************************************/

	public Bishop(final Player p) {
		super(p, "Bishop");
	}

	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param m Move
	 * 
	 * @param board IChessPiece[][]
	 * 
	 * @return boolean
	 ******************************************************************/
	public final boolean isValidMove(final Move m, 
			final IChessPiece[][] board) {
		int rowChange = Math.abs(m.fromRow - m.toRow);
		int colChange = Math.abs(m.fromColumn - m.toColumn);
		int startRow;

		// if the move as a Chesspiece is invalid
		if (!super.isValidMove(m, board)) {
			return false;
		}

		// if not diagonal
		if (rowChange != colChange) {
			return false;
		}

		// if moving to the down
		if (m.toRow > m.fromRow) {
			startRow = m.fromRow + 1;
			// if moving right
			if (m.toColumn > m.fromColumn) {
				// checks spaces between
				for (int i = m.fromColumn + 1; i < m.toColumn; i++) {
					// checks if empty
					if (board[startRow][i] != null) {
						return false;
					}
					startRow++;
				}
			}

			// if moving left
			if (m.fromColumn > m.toColumn) {
				// checks spaces between
				for (int i = m.fromColumn - 1; i > m.toColumn; i--) {
					// checks if empty
					if (board[startRow][i] != null) {
						return false;
					}
					startRow++;
				}
			}
		}

		// if moving up
		if (m.toRow < m.fromRow) {
			startRow = m.fromRow - 1;
			// if moving right
			if (m.toColumn > m.fromColumn) {
				// checks spaces between
				for (int i = m.fromColumn + 1; i < m.toColumn; i++) {
					// checks if empty
					if (board[startRow][i] != null) {
						return false;
					}
					startRow--;
				}
			}
			// if moving left
			if (m.fromColumn > m.toColumn) {
				// checks spaces between
				for (int i = m.fromColumn - 1; i > m.toColumn; i--) {
					// checks if empty
					if (board[startRow][i] != null) {
						return false;
					}
					startRow--;
				}
			}
		}
		return true;
	}

}
