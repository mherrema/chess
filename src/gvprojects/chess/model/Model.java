/**
 *  File:               Model.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

import java.util.ArrayList;

/*********************************************************************
 * This class creates a Chess game engine, altering and keeping track of each.
 * move of the game
 * 
 * @author Mitch Herrema
 * @version March 21, 2013
 *********************************************************************/
public class Model implements IChessModel {

	/** the game board. */
	private IChessPiece[][] board;

	/** white king location. */
	private int[] whiteKing;

	/** black king location. */
	private int[] blackKing;

	/** keeps track of who's turn. */
	private boolean whiteTurn;

	/** game board size. */
	private final int boardsize = 8;

	/** temporary move. */
	private Move tempMove;

	/** Saves the board. */
	private IChessPiece[][] saveFirstBoard;

	/** Saves the first player. */
	private boolean saveFirstPlayer;
	/** Winner variable. */
	private Player winner;

	/** Variable for check in self. */
	private boolean putSelfInCheck = false;

	/** Variable if other player is in check. */
	private boolean otherPlayerCheck = false;

	/** Variable is valid move is initialized. */
	private boolean[][] validMoves;

	/** Variable for the boneyeard is created. */
	private ArrayList<IChessPiece> boneyard;
	
	/** Variable for added to the boneyard is created. */
	private boolean addedToBoneyard = false;

	/******************************************************************
	 * Model Constructor Creates game board and places all pieces, marking king.
	 * locations and setting it to white's turn
	 * 
	 ******************************************************************/
	public Model() {
		whiteTurn = true;
		whiteKing = new int[2];
		blackKing = new int[2];
		setBoard(new IChessPiece[getBoardsize()][getBoardsize()]);
		// creates black pieces
		final int rook = 7; // ROOK
		final int knight = 6;
		final int bishop = 5;
		final int king = 4;
		final int queen = 3;
		getBoard()[0][0] = new Rook(Player.BLACK);
		getBoard()[0][rook] = new Rook(Player.BLACK);
		getBoard()[0][1] = new Knight(Player.BLACK);
		getBoard()[0][knight] = new Knight(Player.BLACK);
		getBoard()[0][2] = new Bishop(Player.BLACK);
		getBoard()[0][bishop] = new Bishop(Player.BLACK);
		getBoard()[0][king] = new King(Player.BLACK);
		blackKing[0] = 0;
		blackKing[1] = king;
		getBoard()[0][queen] = new Queen(Player.BLACK);
		// creates the pawns in their row
		for (int i = 0; i < getBoardsize(); i++) {
			getBoard()[1][i] = new Pawn(Player.BLACK, 1);
		}
		final int row = 7;
		// creates white pieces
		getBoard()[row][0] = new Rook(Player.WHITE);
		getBoard()[row][rook] = new Rook(Player.WHITE);
		getBoard()[row][1] = new Knight(Player.WHITE);
		getBoard()[row][knight] = new Knight(Player.WHITE);
		getBoard()[row][2] = new Bishop(Player.WHITE);
		getBoard()[row][bishop] = new Bishop(Player.WHITE);
		getBoard()[row][king] = new King(Player.WHITE);
		whiteKing[0] = row;
		whiteKing[1] = king;
		getBoard()[row][queen] = new Queen(Player.WHITE);
		for (int i = 0; i < getBoardsize(); i++) {
			getBoard()[row - 1][i] = new Pawn(Player.WHITE, knight);
		}
		saveFirstBoard = new IChessPiece[getBoardsize()][getBoardsize()];
		boneyard = new ArrayList<IChessPiece>();
	}

	/******************************************************************
	 * Returns current player.
	 * 
	 * @return Player
	 ******************************************************************/
	public final Player currentPlayer() {
		if (whiteTurn) {
			return Player.WHITE;
		} else {
			return Player.BLACK;
		}
	}

	/******************************************************************
	 * Returns if the player given is in check.
	 * 
	 * @param p
	 *            - Player
	 * @return boolean if in check
	 ******************************************************************/
	public final boolean inCheck(final Player p) {
		// runs through every row
		for (int i = 0; i < getBoardsize(); i++) {
			// runs through every column
			for (int j = 0; j < getBoardsize(); j++) {
				// if the player given is white
				if (p == Player.WHITE) {
					tempMove = new Move(i, j, whiteKing[0], whiteKing[1]);
				} else if (p == Player.BLACK) {
					tempMove = new Move(i, j, blackKing[0], blackKing[1]);
				}
				// if the move above is valid, player is in check
				if (isValidCheck(tempMove)) {
					return true;
				}
			}
		}
		return false;
	}

	/******************************************************************
	 * Unimplemented checkmate.
	 * 
	 * @return boolean
	 ******************************************************************/
	public final boolean isComplete() {
		Move checkMateMove = null;
		saveFirstState();
		for (int i = 0; i < numRows(); i++) {
			for (int j = 0; j < numColumns(); j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).player() == currentPlayer()) {
						for (int l = 0; l < numRows(); l++) {
							for (int n = 0; n < numColumns(); n++) {
								checkMateMove = new Move(i, j, l, n);
								if (isValidMove(checkMateMove)) {
									move(checkMateMove);
									if (!inCheck(currentPlayer())) {
										loadFirstState();
										return false;
									} else {
										loadFirstState();
									}
								}
							}
						}
					}
				}
			}
		}
		if (inCheck(Player.WHITE)) {
			winner = Player.BLACK;
		} else {
			winner = Player.WHITE;
		}
		return true;
	}

	/******************************************************************
	 * Determines if the given move is valid on this board. Each piece has their
	 * own isValidMove, but that is specific to which direction/distance they
	 * are able to move
	 * 
	 * @param m
	 *            Move
	 * 
	 * @return boolean if valid
	 ******************************************************************/
	public final boolean isValidMove(final Move m) {
		// if the location holds no piece
		if (getBoard()[m.getfromRow()][m.fromColumn()] == null) {
			return false;
		}
		// if trying to move the wrong player's piece
		if (getBoard()[m.getfromRow()][m.fromColumn()].player() 
				!= currentPlayer()) {
			return false;
		}
		// if the piece can't move that direction/distance
		if (!getBoard()[m.getfromRow()][m.fromColumn()]
				.isValidMove(m, getBoard())) {
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Method specific to checking if the current move will put the player.
	 * moving in check
	 * 
	 * @param m
	 *            Move
	 * 
	 * @return boolean if valid
	 ******************************************************************/
	public final boolean isValidCheck(final Move m) {
		// if the location is null
		if (getBoard()[m.getfromRow()][m.fromColumn()] == null) {
			return false;
		}
		// if the piece can't move that direction/distance
		if (!getBoard()[m.getfromRow()][m.fromColumn()]
				.isValidMove(m, getBoard())) {
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Moves the piece.
	 * 
	 * @param m
	 *            Move
	 ******************************************************************/
	public final void move(final Move m) {
		saveFirstState();
		// if valid move
		if (isValidMove(m)) {
			
			// if moving the correct player's piece
			if ((whiteTurn && getBoard()[m.getfromRow()]
					[m.fromColumn()].player() 
					== Player.WHITE)
					|| (!whiteTurn && getBoard()[m.getfromRow()][m.fromColumn()]
							.player() == Player.BLACK)) {
				// Tries to add the piece at the "to" position to the boneyard,
				// which is assumed to be one of the opponent's pieces,
				// and catches the NullPointerException if the "to" position is
				// empty.
				if (board[m.toRow()][m.toColumn()] != null) {
					boneyard.add(board[m.toRow()][m.toColumn()]);
					addedToBoneyard = true;
				}

				getBoard()[m.toRow()][m.toColumn()] = 
						getBoard()[m.getfromRow()][m.fromColumn()];
				getBoard()[m.getfromRow()][m.fromColumn()] = null;
				// if moving the white king
				if (m.getfromRow() == whiteKing[0] && m.fromColumn() 
						== whiteKing[1]) {
					whiteKing[0] = m.toRow();
					whiteKing[1] = m.toColumn();
				}
				// if moving the black king
				if (m.getfromRow() == blackKing[0] && m.fromColumn() 
						== blackKing[1]) {
					blackKing[0] = m.toRow();
					blackKing[1] = m.toColumn();
				}
			}
			// if the move puts themselves in check
			if (inCheck(currentPlayer())) {
				cancelMove(m);
				if (addedToBoneyard) {
					boneyard.remove(boneyard.size() - 1);
				}
				setPutSelfInCheck(true);
			} else if (inCheck(otherPlayer(currentPlayer()))) {
				setOtherPlayerCheck(true);
			} else {
				switchTurns();
			}
			addedToBoneyard = false;

		}
	}

	/******************************************************************
	 * Cancels the move (if putting self into check.
	 * 
	 * @param m
	 *            Move
	 ******************************************************************/
	public final void cancelMove(final Move m) {
		loadFirstState();
		// if moving the white king
		if (m.toRow() == whiteKing[0] && m.toColumn() == whiteKing[1]) {
			whiteKing[0] = m.getfromRow();
			whiteKing[1] = m.fromColumn();
		}
		// if moving the black king
		if (m.toRow() == blackKing[0] && m.toColumn() == blackKing[1]) {
			blackKing[0] = m.getfromRow();
			blackKing[1] = m.fromColumn();
		}
	}

	/******************************************************************
	 * Switches turns after a move.
	 ******************************************************************/
	public final void switchTurns() {
		// if it's white's turn
		if (whiteTurn) {
			whiteTurn = false;
		} else {
			whiteTurn = true;
		}
	}

	/******************************************************************
	 * Returns the other player.
	 * 
	 * @param p
	 *            Player
	 * 
	 * @return other Player
	 ******************************************************************/
	public final Player otherPlayer(final Player p) {
		// if p is white
		if (p.equals(Player.WHITE)) {
			return Player.BLACK;
		} else {
			return Player.WHITE;
		}
	}

	/******************************************************************
	 * Returns number of columns.
	 * 
	 * @param none
	 * @return int columns
	 ******************************************************************/
	public final int numColumns() {
		return getBoardsize();
	}

	/******************************************************************
	 * Returns the number of rows.
	 * 
	 * @param none
	 * @return int rows
	 ******************************************************************/
	public final int numRows() {
		return getBoardsize();
	}

	/******************************************************************
	 * Returns the piece at the given row and col.
	 * 
	 * @param row
	 *            int
	 * @param col
	 *            int
	 * @return IChessPiece
	 ******************************************************************/
	public final IChessPiece pieceAt(final int row, final int col) {
		return getBoard()[row][col];
	}

	/******************************************************************
	 * Returns the game board.
	 * 
	 * @param none
	 * @return IChessPiece[][] board
	 ******************************************************************/
	public final IChessPiece[][] getBoard() {
		return board;
	}

	/******************************************************************
	 * Sets the board.
	 * 
	 * @param aboard
	 *            IChessPiece [][]
	 ******************************************************************/
	public final void setBoard(final IChessPiece[][] aboard) {
		this.board = aboard;
	}

	/******************************************************************
	 * Checks to see if you put yourself in check.
	 * 
	 * @return boolean
	 ******************************************************************/
	public final boolean isPutSelfInCheck() {
		return putSelfInCheck;
	}

	/******************************************************************
	 * Checks to see if you put yourself in check tbe sets the variable.
	 * 
	 * @param aputSelfInCheck
	 *            boolean
	 ******************************************************************/
	public final void setPutSelfInCheck(final boolean aputSelfInCheck) {
		this.putSelfInCheck = aputSelfInCheck;
	}

	/******************************************************************
	 * Checks to see if the other player is in check.
	 * 
	 * @return boolean
	 ******************************************************************/
	public final boolean isOtherPlayerCheck() {
		return otherPlayerCheck;
	}

	/******************************************************************
	 * Checks to see if the other payer is in check then sets the variable.
	 * 
	 * @param potherPlayerCheck
	 *            boolean
	 ******************************************************************/
	public final void setOtherPlayerCheck(final boolean potherPlayerCheck) {
		this.otherPlayerCheck = potherPlayerCheck;
	}

	/** Saves board state. */
	private void saveFirstState() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				saveFirstBoard[i][j] = board[i][j];
			}
		}
		saveFirstPlayer = whiteTurn;
	}

	/** Loads board state. */
	private void loadFirstState() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				board[i][j] = saveFirstBoard[i][j];
			}
		}
		whiteTurn = saveFirstPlayer;
	}

	/**
	 * returns winning player.
	 * 
	 * @return winner Player
	 */
	public final Player getWinner() {
		return winner;
	}

	/******************************************************************
	 * sets Winner.
	 * 
	 * @param pwinner
	 *            Player
	 ******************************************************************/
	public final void setWinner(final Player pwinner) {
		this.winner = pwinner;
	}

	/**
	 * Creates a method which will get the available moves.
	 * 
	 * @param row 
	 * @param col 
	 * @return boolean expression
	 */
	public final boolean[][] getMoves(final int row, final int col) {
		validMoves = new boolean[getBoardsize()][getBoardsize()];
		for (int i = 0; i < getBoardsize(); i++) {
			for (int j = 0; j < getBoardsize(); j++) {
				if (pieceAt(row, col).isValidMove(new Move(row, col, i, j),
						getBoard())
						&& pieceAt(row, col).player() == currentPlayer()) {
					validMoves[i][j] = true;
				}
			}
		}
		return validMoves;
	}

	/**********************************************************************
	 * Method that returns the ArrayList containing all the captured pieces in
	 * the game.
	 * 
	 * @param none
	 * @return ArrayList<IChessPiece> the ArrayList of captured pieces
	 **********************************************************************/
	public final ArrayList<IChessPiece> getBoneyard() {
		return boneyard;
	}

	/**
	 * returns the boardsize.
	 * 
	 * @return BOARDSIZE int
	 */
	public final int getBoardsize() {
		return boardsize;
	}

}