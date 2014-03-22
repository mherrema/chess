package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code King} {@code ChessPiece}.
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class King extends ChessPiece {
	
	/******************************************************************
	 * King Constructor.
	 * Creates a king chess piece
	 * 
	 * @param p Player
	 ******************************************************************/
	public King(final Player p) {
		super(p, "King");
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
		//if moving greater than one space
		if (Math.abs(m.toRow - m.fromRow) > 1 
				|| Math.abs(m.toColumn - m.fromColumn) > 1) {
			return false;
		}
		return true;
	}
}
