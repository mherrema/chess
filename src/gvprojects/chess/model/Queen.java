/**
 *  File:               Queen.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code Queen} {@code ChessPiece}.
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Queen extends ChessPiece {

	/******************************************************************
	 * Queen Constructor.
	 * Creates a queen chess piece
	 * 
	 * @param p Player
	 ******************************************************************/
	public Queen(final Player p) {
		super(p, "Queen");
	}

	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param m Move
	 * @param board IChessPiece[][]
	 * @return boolean
	 ******************************************************************/
	public final boolean isValidMove(final Move m, 
			final IChessPiece[][] board) {
		int rowChange = Math.abs(m.getfromRow() - m.toRow());
		int colChange = Math.abs(m.fromColumn() - m.toColumn());
		int startRow;
		//if the move as a Chesspiece is invalid
		if (!super.isValidMove(m, board)) {
			return false;
		}
		//if valid move
		if (rowChange == colChange || rowChange == 0 || colChange == 0) {
			//if diagonal
			if (rowChange == colChange) {
				//if higher row
				if (m.toRow() > m.getfromRow()) {
					startRow = m.getfromRow() + 1;
					//if higher column
					if (m.toColumn() > m.fromColumn()) {
						for (int i = m.fromColumn() + 1; 
								i < m.toColumn(); i++) {
							if (board[startRow][i] != null) {
								return false;
							}
							startRow++;
						}
					}
					//if lower column
					if (m.fromColumn() > m.toColumn()) {
						//checks spaces between
						for (int i = m.fromColumn() - 1; 
								i > m.toColumn(); i--) {
							//checks if empty
							if (board[startRow][i] != null) {
								return false;
							}
							startRow++;
						}
					}
				}
				//if lower row
				if (m.toRow() < m.getfromRow()) {
					startRow = m.getfromRow() - 1;
					//if higher column
					if (m.toColumn() > m.fromColumn()) {
						//checks spaces between
						for (int i = m.fromColumn() + 1; 
								i < m.toColumn(); i++) {
							//checks if empty
							if (board[startRow][i] != null) {
								return false;
							}
							startRow--;
						}
					}
					//if lower column
					if (m.fromColumn() > m.toColumn()) {
						//checks spaces between
						for (int i = m.fromColumn() - 1; 
								i > m.toColumn(); i--) {
							//checks if empty
							if (board[startRow][i] != null) {
								return false;
							}
							startRow--;
						}
					}
				}
				//if passes all
				return true;
				//if not diaganol
			} else {
				//if moving rows
				if (colChange == 0) {
					//to higher row
					if (m.toRow() > m.getfromRow()) {
						//checks spaces between
						for (int i = m.getfromRow() + 1; i < m.toRow(); i++) {
							//checks if empty
							if (board[i][m.fromColumn()] != null) {
								return false;
							}
						}
					}
					//to lower row
					if (m.toRow() < m.getfromRow()) {
						//checks spaces between
						for (int i = m.getfromRow() - 1; i > m.toRow(); i--) {
							//checks if empty
							if (board[i][m.fromColumn()] != null) {
								return false;
							}
						}
					}
					return true;
				}
				//if column change
				if (rowChange == 0) {
					//if higher column
					if (m.toColumn() > m.fromColumn()) {
						//checks spaces between
						for (int i = m.fromColumn() + 1; 
								i < m.toColumn(); i++) {
							//checks if empty
							if (board[m.getfromRow()][i] != null) {
								return false;
							}
						}
					}
					//if lower column
					if (m.toColumn() < m.fromColumn()) {
						//checks spaces between
						for (int i = m.fromColumn() - 1; 
								i > m.toColumn(); i--) {
							//checks if empty
							if (board[m.getfromRow()][i] != null) {
								return false;
							}
						}
					}
					return true;
				}
			}
		}
		return false;
	}
}
