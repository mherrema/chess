package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*********************************************************************
 * This class tests the {@code Bishop} {@code ChessPiece} class. 
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class BishopTest extends ChessPieceTest {

	// This method overrides make(Player p) in the ChessPieceTest class
	// Thus, when running these tests, calling make() generates a Pawn object
	// whereas calling make when running ChessPiece Tests generates a ChessPiece
	// object.
	@Override
	protected IChessPiece make(Player p) {
		return new Bishop(p);
	}

	// As noted in ChessPieceTest: Many tests require a valid move. The set of
	// valid moves is different for
	// each chess piece. This method generates a move that is valid from the
	// given row and column
	@Override
	protected Move getValidMove(int row, int col) {
		int newRow = row + 1;
		int newCol = col + 1;
		if (newRow >= board.length) {
			newRow = row - 1;
		}
		if (newCol >= board.length){
			newCol = col - 1;
		}
		return new Move(row, col, newRow, newCol);
	}

	// Verify that a bishop cannot move across a row
	@Test
	public void cannotMoveInRow() throws Exception {
		board[1][1] = piece;
		assertFalse("Bishop Test 1",
				piece.isValidMove(new Move(1, 1, 1, 6), board));
	}

	// Verify that a bishop cannot move up a column
	@Test
	public void cannotMoveInColumn() throws Throwable {
		board[1][1] = piece;
		assertFalse("Bishop Test 2",
				piece.isValidMove(new Move(1, 1, 2, 1), board));
	}

	// Verify that a bishop can move diagonally
	@Test
	public void cannotMoveDiagonalUnlessOtherPlayerPiece() throws Throwable {
		board[1][1] = piece;
		assertTrue("Bishop Test 3",
				piece.isValidMove(new Move(1, 1, 2, 2), board));
	}

	// Verify that a king can move diagonally with other player
	@Test
	public void canMoveDiagonalWithOtherPlayerPiece() throws Throwable {
		board[1][1] = piece;
		board[2][2] = make(Player.BLACK);
		assertTrue("Bishop Test 4",
				piece.isValidMove(new Move(1, 1, 2, 2), board));
	}

	// Verify that a bishop cannot move backward
	@Test
	public void canMoveBackward() throws Throwable {
		board[1][1] = piece;
		assertTrue("Bishop Test 5",
				piece.isValidMove(new Move(1, 1, 0, 0), board));
	}

	// Verify that a bishop cannot move off the board
	@Test
	public void cannotMoveOffBoard() throws Throwable {
		board[1][1] = piece;
		assertFalse("Bishop Test 6",
				piece.isValidMove(new Move(1, 1, -1, 1), board));
	}

	// Verify that a bishop cannot jump over other pieces.
	@Test
	public void pathMustBeClear1() throws Throwable {
		board[2][2] = piece;
		board[3][3] = make();
		assertFalse("Bishop Test 7",
				piece.isValidMove(new Move(2, 2, 4, 4), board));
	}

}
