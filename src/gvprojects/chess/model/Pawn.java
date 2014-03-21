package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code Pawn} {@code ChessPiece}, 
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class Pawn extends ChessPiece {
	
	/** starting row */
	private int startRow;
	
	/** player */
	private Player p;
	
	/******************************************************************
	 * Pawn Constructor
	 * Creates a pawn
	 *  chess piece
	 * 
	 * @param Player p
	 * @param int Start Row
	 ******************************************************************/
	public Pawn(final Player p, final int startRow) {
		super(p, "Pawn");
		this.startRow = startRow;
		this.p = p;
	}
	
	/******************************************************************
	 * Returns if the move is valid
	 * 
	 * @param Move m
	 * @param IChessPiece[][] board
	 * @return boolean
	 ******************************************************************/
	
	@Override
	public final boolean isValidMove(final Move m, final IChessPiece[][] board) {
		int rowChange;
		//System.out.println("from col " + m.fromColumn + " to col "+ m.toColumn);
		int colChange = Math.abs(m.fromColumn - m.toColumn);
		
		//if the move as a Chesspiece is invalid
		if (super.isValidMove(m, board) == false) {
			//System.out.println("super");
			return false;
		}
		
		//if starting from row one
		if (startRow == 1) {
			rowChange = m.toRow - m.fromRow;
		} else {
			rowChange = m.fromRow - m.toRow;
		}
		//System.out.println("row change = " + rowChange);
		//System.out.println("col change = " + colChange);
			//if moving two rows, no columns, and moving from starting row
			if (rowChange == 2 && m.fromRow == startRow 
					&& colChange == 0 && board[m.toRow][m.toColumn] == null) {
				return true;
			}
			//if moving diagonal and other player is diagonal
			if (rowChange == 1 && colChange == 1 
					&& board[m.toRow][m.toColumn] != null 
					&& board[m.toRow][m.toColumn].player() != p) {
				return true;
				
			}
			//if moving one row and no columns
			if (rowChange == 1 && colChange == 0 
					&& board[m.toRow][m.toColumn] == null) {
				return true;
			}
		return false;
	}
}
