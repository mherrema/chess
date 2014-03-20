package gvprojects.chess.model;

/*********************************************************************
 * This class creates a Chess game engine, altering and keeping track of each
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
	private int boardsize = 8;
	
	/** temporary move. */
	private Move tempMove;

	/**
	 * @param boolean putselfInCheck
	 * 
	 * @param boolean otherPlayerCheck
	 */
	private boolean putSelfInCheck = false;
	private boolean otherPlayerCheck = false;
	
	/******************************************************************
	 * Model Constructor Creates game board and places all pieces, marking king
	 * locations and setting it to white's turn
	 * 
	 ******************************************************************/
	public Model() {
		whiteTurn = true;
		whiteKing = new int[2];
		blackKing = new int[2];
		setBoard(new IChessPiece[boardsize][boardsize]);
		// creates black pieces
		getBoard()[0][0] = new Rook(Player.BLACK);
		getBoard()[0][7] = new Rook(Player.BLACK);
		getBoard()[0][1] = new Knight(Player.BLACK);
		getBoard()[0][6] = new Knight(Player.BLACK);
		getBoard()[0][2] = new Bishop(Player.BLACK);
		getBoard()[0][5] = new Bishop(Player.BLACK);
		getBoard()[0][3] = new King(Player.BLACK);
		blackKing[0] = 0;
		blackKing[1] = 3;
		getBoard()[0][4] = new Queen(Player.BLACK);
		// creates the pawns in their row
		for (int i = 0; i < boardsize; i++) {
			getBoard()[1][i] = new Pawn(Player.BLACK, 1);
		}

		// creates white pieces
		getBoard()[7][0] = new Rook(Player.WHITE);
		getBoard()[7][7] = new Rook(Player.WHITE);
		getBoard()[7][1] = new Knight(Player.WHITE);
		getBoard()[7][6] = new Knight(Player.WHITE);
		getBoard()[7][2] = new Bishop(Player.WHITE);
		getBoard()[7][5] = new Bishop(Player.WHITE);
		getBoard()[7][3] = new King(Player.WHITE);
		whiteKing[0] = 7;
		whiteKing[1] = 3;
		getBoard()[7][4] = new Queen(Player.WHITE);
		for (int i = 0; i < boardsize; i++) {
			getBoard()[6][i] = new Pawn(Player.WHITE, 6);
		}
	}

	/******************************************************************
	 * Returns current player
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
	 * Returns if the player given is in check
	 * 
	 * @param Player
	 * @return boolean if in check
	 ******************************************************************/
	public final boolean inCheck(Player p) {
		// runs through every row
		for (int i = 0; i < boardsize; i++) {
			// runs through every column
			for (int j = 0; j < boardsize; j++) {
				// if the player given is white
				if (p == Player.WHITE) {
					tempMove = new Move(i, j, whiteKing[0], whiteKing[1]);
					// if the player given is black
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
	 * Unimplemented checkmate
	 * 
	 * @return boolean
	 ******************************************************************/
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}

	/******************************************************************
	 * Determines if the given move is valid on this board. Each piece has their
	 * own isValidMove, but that is specific to which direction/distance they
	 * are able to move
	 * 
	 * @param Move
	 *            m
	 * @return boolean if valid
	 ******************************************************************/
	public final boolean isValidMove(Move m) {
		// if the location holds no piece
		if (getBoard()[m.fromRow][m.fromColumn] == null) {
			System.out.println("null");
			return false;
		}
		// if trying to move the wrong player's piece
		if (getBoard()[m.fromRow][m.fromColumn].player() != currentPlayer()) {
			System.out.println("not current");
			return false;
		}
		// if the piece can't move that direction/distance
		if (getBoard()[m.fromRow][m.fromColumn]
				.isValidMove(m, getBoard()) == false) {
			System.out.println(getBoard()[m.fromRow][m.fromColumn].type());
			System.out.println("wrong direction");
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Method specific to checking if the current move will put the player
	 * moving in check
	 * 
	 * @param Move
	 *            m
	 * @return boolean if valid
	 ******************************************************************/
	public boolean isValidCheck(final Move m) {
		// if the location is null
		if (getBoard()[m.fromRow][m.fromColumn] == null) {
			return false;
		}
		// if the piece can't move that direction/distance
		if (getBoard()[m.fromRow][m.fromColumn]
				.isValidMove(m, getBoard()) == false) {
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Moves the piece
	 * 
	 * @param Move
	 *            m
	 ******************************************************************/
	public void move(Move m) {
		// if valid move
		System.out.println(m.fromRow + " " + m.toRow);
		System.out.println(m.fromColumn + " " + m.toColumn);
		
		if (isValidMove(m)) {
			System.out.println("valid");
			// if moving the correct player's piece
			if ((whiteTurn && getBoard()[m.fromRow][m.fromColumn]
					.player() == Player.WHITE)
					|| (!whiteTurn && getBoard()[m.fromRow][m.fromColumn]
							.player() == Player.BLACK)) {
				getBoard()[m.toRow][m.toColumn] 
						= getBoard()[m.fromRow][m.fromColumn];
				getBoard()[m.fromRow][m.fromColumn] = null;
				// if moving the white king
				if (m.fromRow == whiteKing[0] && m.fromColumn == whiteKing[1]) {
					whiteKing[0] = m.toRow;
					whiteKing[1] = m.toColumn;
				}
				// if moving the black king
				if (m.fromRow == blackKing[0] && m.fromColumn == blackKing[1]) {
					blackKing[0] = m.toRow;
					blackKing[1] = m.toColumn;
				}
			}
			//if the move puts themselves in check
			if (inCheck(currentPlayer())) {
				cancelMove(m);
				setPutSelfInCheck(true);
				//update();
			}
			//if move puts other player in check
			if (inCheck(otherPlayer(currentPlayer()))) {
				setOtherPlayerCheck(true);
			}
			if (!inCheck(currentPlayer())) {
			switchTurns();
			}
		}
	}

	/******************************************************************
	 * Cancels the move (if putting self into check
	 * 
	 * @param Move
	 *            m
	 * @return none
	 ******************************************************************/
	public final void cancelMove(final Move m) {
		board[m.fromRow][m.fromColumn] = board[m.toRow][m.toColumn];
		// if moving the white king
		if (m.toRow == whiteKing[0] && m.toColumn == whiteKing[1]) {
			whiteKing[0] = m.fromRow;
			whiteKing[1] = m.fromColumn;
		}
		// if moving the black king
		if (m.toRow == blackKing[0] && m.toColumn == blackKing[1]) {
			blackKing[0] = m.fromRow;
			blackKing[1] = m.fromColumn;
		}
		board[m.toRow][m.toColumn] = null;
	}

	/******************************************************************
	 * Switches turns after a move
	 * 
	 * @param none
	 * @return none
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
	 * Returns the other player
	 * 
	 * @param Player
	 *            p
	 * @return other Player
	 ******************************************************************/
	public final Player otherPlayer(Player p) {
		// if p is white
		if (p.equals(Player.WHITE)) {
			return Player.BLACK;
		} else {
			return Player.WHITE;
		}
	}

	/******************************************************************
	 * Returns number of columns
	 * 
	 * @param none
	 * @return int columns
	 ******************************************************************/
	public final int numColumns() {
		return boardsize;
	}

	/******************************************************************
	 * Returns the number of rows
	 * 
	 * @param none
	 * @return int rows
	 ******************************************************************/
	public final int numRows() {
		return boardsize;
	}

	/******************************************************************
	 * Returns the piece at the given row and col
	 * 
	 * @param int row
	 * @param int col
	 * @return IChessPiece
	 ******************************************************************/
	public final IChessPiece pieceAt(int row, int col) {
		return getBoard()[row][col];
	}

	/******************************************************************
	 * Returns the game board
	 * 
	 * @param none
	 * @return IChessPiece[][] board
	 ******************************************************************/
	public final IChessPiece[][] getBoard() {
		return board;
	}

	/******************************************************************
	 * Sets the board
	 * 
	 * @param IChessPiece
	 *            [][] board
	 * @return none
	 ******************************************************************/
	public final void setBoard(IChessPiece[][] board) {
		this.board = board;
	}

	/**
	 * Checks for checkmate
	 * @return boolean
	 */
	public final boolean isPutSelfInCheck() {
		return putSelfInCheck;
	}

	/**
	 * Sets the checkmate
	 * 
	 *  *@param boolean putSelfInCheck
	 */
	public final void setPutSelfInCheck(boolean putSelfInCheck) {
		this.putSelfInCheck = putSelfInCheck;
	}

	/**
	 * Checks for checkmate
	 * @return boolean
	 */
	public final boolean isOtherPlayerCheck() {
		return otherPlayerCheck;
	}

	/**
	 * Sets the checkmate on the other player
	 *
	 *@param boolean otherPlayerCheck
	 */
	public final void setOtherPlayerCheck(boolean otherPlayerCheck) {
		this.otherPlayerCheck = otherPlayerCheck;
	}
}