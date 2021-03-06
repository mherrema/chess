/**
 *  File:               ChessPiece.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code ChessPiece}. 
 * and determines if moves are valid
 * 
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class ChessPiece implements IChessPiece {

	/*****************************************************************
	 * Initialize variable.
	 * 
	 * @param name String
	 ****************************************************************/
	private String name;
	/*****************************************************************
	 * Initialize variable.
	 * 
	 * @param color Player
	 ****************************************************************/
	private Player color;

	/******************************************************************
	 * ChessPiece Constructor creates a chess piece.
	 * 
	 * @param acolor - Player
	 * 
	 * @param aname - String
	 * 
	 ******************************************************************/
	public ChessPiece(final Player acolor, final String aname) {
		this.color = acolor;
		this.name = aname;
	}

	/******************************************************************
	 * Returns if the move is valid.
	 * 
	 * @param position - Move
	 *            
	 * @param  board - IChessPiece [][]
	 * 
	 * @return boolean
	 ******************************************************************/
	public boolean isValidMove(final Move position, 
		final IChessPiece[][] board) {
		int fromRow = position.getfromRow();
		int fromColumn = position.fromColumn();
		int toRow = position.toRow();
		int toColumn = position.toColumn();
		int max = board[0].length - 1;
		if (fromRow > max || toRow > max || fromColumn > max || toColumn > max
				|| fromRow < 0 || toRow < 0 || fromColumn < 0 || toColumn < 0) {
			return false;
		}
		if (board[fromRow][fromColumn] == null) {
			throw new IllegalArgumentException("null from");
		}
		if (board[fromRow][fromColumn] != this) {
			throw new IllegalArgumentException("wrong object");
		}
		if (fromRow == toRow && fromColumn == toColumn) {
			return false;
		}
		if (board[toRow][toColumn] != null) {
			if (board[toRow][toColumn].player() == color) {
				return false;
			}
		}

		return true;
	}

	/******************************************************************
	 * Returns the name of the piece.
	 * 
	 * @return String name
	 ******************************************************************/
	@Override
	public final String type() {
		return name;
	}

	/******************************************************************
	 * Returns the player that owns the piece.
	 * 
	 * @return Player color
	 ******************************************************************/
	@Override
	public final Player player() {
		return color;
	}
}
