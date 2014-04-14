/**
 *  File:               Rook.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code Rook} {@code ChessPiece}.
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Rook extends ChessPiece {

	/******************************************************************
	 * Rook Constructor.
	 * Creates a rook chess piece
	 * 
	 * @param p Player
	 ******************************************************************/
	public Rook(final Player p) {
		super(p, "Rook");
	}

	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param m Move
	 * @param board IChessPiece[][]
	 * @return boolean
	 ******************************************************************/
	@Override
	public final boolean isValidMove(final Move m, 
			final IChessPiece[][] board) {
		//if the move as a Chesspiece is invalid
		if (!super.isValidMove(m, board)) {
			return false;
		}
		//if moving diagonal
		if (m.getfromRow() != m.toRow() && m.fromColumn() != m.toColumn()) {
			return false;
		}
		//if moving rows
		if (m.getfromRow() != m.toRow() && m.fromColumn() == m.toColumn()) {
			//if moving down
			if (m.toRow() > m.getfromRow()) {
				//checks spaces between
				for (int i = (m.getfromRow() + 1); i < m.toRow(); i++) {
					//checks if empty
					if (board[i][m.toColumn()] != null) {
						return false;
					}
				}
				return true;
			} else {
				//checks spaces between
				for (int i = m.toRow() + 1; i < m.getfromRow(); i++) {
					//checks if empty
					if (board[i][m.fromColumn()] != null) {
						return false;
					}
				}
				return true;
			}
		}

		//if moving columns
		if (m.getfromRow() == m.toRow() && m.fromColumn() != m.toColumn()) {
			//if moving right
			if (m.toColumn() > m.fromColumn()) {
				//checks spaces between
				for (int i = m.fromColumn() + 1; i < m.toColumn(); i++) {
					//checks if empty
					if (board[m.getfromRow()][i] != null) {
						return false;
					}
				}
				return true;
			} else {
				//checks spaces between
				for (int i = m.toColumn() + 1; i < m.fromColumn(); i++) {
					//checks if empty
					if (board[m.getfromRow()][i] != null) {
						return false;
					}
				}
				return true;
			}
		}
		return false;
	}
	}
