/**
 *  File:               Model.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

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
	private int boardsize = 8;

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

	/******************************************************************
	 * Model Constructor Creates game board and places all pieces, marking king.
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
		getBoard()[0][4] = new King(Player.BLACK);
		blackKing[0] = 0;
		blackKing[1] = 4;
		getBoard()[0][3] = new Queen(Player.BLACK);
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
		getBoard()[7][4] = new King(Player.WHITE);
		whiteKing[0] = 7;
		whiteKing[1] = 4;
		getBoard()[7][3] = new Queen(Player.WHITE);
		for (int i = 0; i < boardsize; i++) {
			getBoard()[6][i] = new Pawn(Player.WHITE, 6);
		}
		saveFirstBoard = new IChessPiece[boardsize][boardsize];
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
	 * @param p - Player
	 * @return boolean if in check
	 ******************************************************************/
	public final boolean inCheck(final Player p) {
		// runs through every row
				for (int i = 0; i < boardsize; i++) {
					// runs through every column
					for (int j = 0; j < boardsize; j++) {
						// if the player given is white
						if (p == Player.WHITE) {
							tempMove = new Move(i, j, whiteKing[0], 
									whiteKing[1]); 
						} else if (p == Player.BLACK) {
							tempMove = new Move(i, j, blackKing[0], 
									blackKing[1]);
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
		System.out.println("checked complete");
		// Player p = Player.WHITE;
		Move checkMateMove = null;
		saveFirstState();
		//View v = new View();
		//v.printBoard(saveFirstBoard);
		// for(int k=0; k<2; k++){
		for (int i = 0; i < numRows(); i++) {
			for (int j = 0; j < numColumns(); j++) {
				if (pieceAt(i, j) != null) {
					if (pieceAt(i, j).player() == currentPlayer()) {
						for (int l = 0; l < numRows(); l++) {
							for (int n = 0; n < numColumns(); n++) {
								checkMateMove = new Move(i, j, l, n);
								if (isValidMove(checkMateMove)) {
									// saveState();
									move(checkMateMove);
									System.out.println("moved");
									if (!inCheck(currentPlayer())) {
										System.out
										.println("not in check anymore");
										loadFirstState();
										//v.printBoard(saveFirstBoard);
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
		// p = Player.BLACK;
		// }

		if (inCheck(Player.WHITE)) {
			winner = Player.BLACK;
		} else {
			winner = Player.WHITE;
		}
		//loadFirstState();
		return true;
	}

	/******************************************************************
	 * Determines if the given move is valid on this board. Each piece has their
	 * own isValidMove, but that is specific to which direction/distance they
	 * are able to move
	 * 
	 * @param m Move
	 * 
	 * @return boolean if valid
	 ******************************************************************/
	public final boolean isValidMove(final Move m) {
		// if the location holds no piece
		if (getBoard()[m.fromRow][m.fromColumn] == null) {
			return false;
		}
		// if trying to move the wrong player's piece
		if (getBoard()[m.fromRow][m.fromColumn].player() != currentPlayer()) {
			return false;
		}
		// if the piece can't move that direction/distance
		if (!getBoard()[m.fromRow][m.fromColumn].isValidMove(m, getBoard())) {
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Method specific to checking if the current move will put the player.
	 * moving in check
	 * 
	 * @param m Move
	 * 
	 * @return boolean if valid
	 ******************************************************************/
	public final boolean isValidCheck(Move m) {
		// if the location is null
		if (getBoard()[m.fromRow][m.fromColumn] == null) {
			return false;
		}
		// if the piece can't move that direction/distance
		if (!getBoard()[m.fromRow][m.fromColumn].isValidMove(m, getBoard())) {
			return false;
		}
		return true;
	}

	/******************************************************************
	 * Moves the piece.
	 * 
	 * @param m Move
	 ******************************************************************/
	public final void move(final Move m) {
		//IChessPiece[][] tempboard = null;
		saveFirstState();
		// if valid move
		if (isValidMove(m)) {
			// if moving the correct player's piece
			if ((whiteTurn && getBoard()[m.fromRow][m.fromColumn].player()
					== Player.WHITE)
					|| (!whiteTurn && getBoard()[m.fromRow][m.fromColumn]
							.player() == Player.BLACK)) {
				//tempboard = getBoard();
				getBoard()[m.toRow][m.toColumn] = 
						getBoard()[m.fromRow][m.fromColumn];
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
			// if the move puts themselves in check
			if (inCheck(currentPlayer())) {
				cancelMove(m);
				setPutSelfInCheck(true);
				System.out.println("current player in check. current player ="
						+ whiteTurn); 
			}
			// if move puts other player in check
			else if (inCheck(otherPlayer(currentPlayer()))) {
				setOtherPlayerCheck(true);
			} else {
				switchTurns();
				// System.out.println("switched");
			}
		}
		//View v = new View();
		//v.printBoard(saveFirstBoard)
	}

	/******************************************************************
	 * Cancels the move (if putting self into check.
	 * 
	 * @param m Move
	 ******************************************************************/
	public final void cancelMove(final Move m) {
		//board[m.fromRow][m.fromColumn] = board[m.toRow][m.toColumn];
		loadFirstState();
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
		//board[m.toRow][m.toColumn] = null;
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
	 * @param p Player
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
		return boardsize;
	}

	/******************************************************************
	 * Returns the number of rows.
	 * 
	 * @param none
	 * @return int rows
	 ******************************************************************/
	public final int numRows() {
		return boardsize;
	}

	/******************************************************************
	 * Returns the piece at the given row and col.
	 * 
	 * @param row int
	 * @param col int
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
	 * @param board IChessPiece [][]
	 ******************************************************************/
	public void setBoard(final IChessPiece[][] board) {
		this.board = board;
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
	 * @param putSelfInCheck boolean
	 ******************************************************************/
	public final void setPutSelfInCheck(final boolean putSelfInCheck) {
		this.putSelfInCheck = putSelfInCheck;
	}

	/******************************************************************
	 * Checks to see if the other player is in check.
	 * 
	 * @return boolean
	 ******************************************************************/
	public boolean isOtherPlayerCheck() {
		return otherPlayerCheck;
	}

	/******************************************************************
	 * Checks to see if the other payer is in check then sets the variable.
	 * 
	 * @param otherPlayerCheck boolean
	 ******************************************************************/
	public final void setOtherPlayerCheck(final boolean otherPlayerCheck) {
		this.otherPlayerCheck = otherPlayerCheck;
	}

//	private void saveState() {
//		saveBoard = board;
//		savePlayer = whiteTurn;
//	}
//
//	private void loadState() {
//		board = saveBoard;
//		whiteTurn = savePlayer;
//	}

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
		System.out.println("loaded first state");
	}

	/** returns winning player.
	 * 
	 * @return winner Player 
	 */
	public final Player getWinner() {
		return winner;
	}

	/******************************************************************
	 * sets Winner.
	 * 
	 *@param winner Player  
	 ******************************************************************/
	public final void setWinner(Player winner) {
		this.winner = winner;
	}
}