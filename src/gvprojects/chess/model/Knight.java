package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code Knight} {@code ChessPiece}, 
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Knight extends ChessPiece {

	/******************************************************************
	 * Knight Constructor
	 * Creates a knight chess piece
	 * 
	 ******************************************************************/
	public Knight(final Player p) {
		super(p, "Knight");
	}

	/******************************************************************
	 * Returns if the move is valid
	 * 
	 * @param Move m
	 * @param IChessPiece[][] board
	 * @return boolean
	 ******************************************************************/
	public boolean isValidMove(Move m, IChessPiece[][] board) {
		//if the move as a Chesspiece is invalid
		if (super.isValidMove(m, board) == false) {
			return false;
		}
		//if moving one row and two columns or one column and two rows
		if ((Math.abs(m.toRow - m.fromRow) == 1 && Math.abs(m.toColumn
				- m.fromColumn) == 2)
				|| ((Math.abs(m.toRow - m.fromRow) == 2 && Math.abs(m.toColumn
						- m.fromColumn) == 1))) {
			return true;
		}
		return false;
	}
}
