package gvprojects.chess.model;

/*********************************************************************
 * This class creates a {@code ChessPiece}, 
 * and determines if moves are valid
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class ChessPiece implements IChessPiece {

	private String name;
	private Player color;

	/******************************************************************
	 * ChessPiece Constructor creates a chess piece
	 * 
	 * @return none
	 ******************************************************************/
	public ChessPiece(Player color, String name) {
		this.name = name;
		this.color = color;
	}

	/******************************************************************
	 * Returns if the move is valid
	 * 
	 * @param Move
	 *            m
	 * @param IChessPiece
	 *            [][] board
	 * @return boolean
	 ******************************************************************/
	@Override
	public boolean isValidMove(Move position, IChessPiece[][] board) {
		int fromRow = position.fromRow;
		int fromColumn = position.fromColumn;
		int toRow = position.toRow;
		int toColumn = position.toColumn;
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
	 * Returns the name of the piece
	 * 
	 * @return String name
	 ******************************************************************/
	@Override
	public String name() {
		return name;
	}

	/******************************************************************
	 * Returns the player that owns the piece
	 * 
	 * @return Player color
	 ******************************************************************/
	@Override
	public Player player() {
		return color;
	}

}
