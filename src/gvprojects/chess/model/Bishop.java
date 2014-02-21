package gvprojects.chess.model;

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
	 ******************************************************************/
	public Bishop(Player p) {
		super(p, "Bishop");
	}

	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param Move m
	 * 
	 * @param IChessPiece [][] board
	 * @return boolean
	 ******************************************************************/
	public boolean isValidMove(Move m, IChessPiece[][] board) {
		int rowChange = Math.abs(m.fromRow - m.toRow);
		int colChange = Math.abs(m.fromColumn - m.toColumn);
		int startRow;
		// if the move as a Chesspiece is invalid
		if (super.isValidMove(m, board) == false) {
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
