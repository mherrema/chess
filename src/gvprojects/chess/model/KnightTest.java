package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*********************************************************************
 * This class tests the {@code Knight} {@code ChessPiece} class. 
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class KnightTest extends ChessPieceTest {

	// This method overrides make(Player p) in the ChessPieceTest class
	// Thus, when running these tests, calling make() generates a Pawn object
	// whereas calling make when running ChessPiece Tests generates a ChessPiece
	// object.
	@Override
	protected final IChessPiece make(final Player p) {
		return new Knight(p);
	}

	// As noted in ChessPieceTest: Many tests require a valid move. The set of
	// valid moves is different for
	// each chess piece. This method generates a move that is valid from the
	// given row and column
	@Override
	protected final Move getValidMove(final int row, final int col) {
		int newRow = row + 2;
		int newCol = col + 1;
		if (newRow >= board.length) {
			newRow = row - 3;
		}
		if (newCol >= board.length) {
			newCol = col - 1;
		}
		return new Move(row, col, newRow, newCol);
	}

	// Verify that a knight cannot move across a row
	@Test
	public final void cannotMoveInRow() throws Exception {
		board[1][1] = piece;
		assertFalse("Knight Test 1",
				piece.isValidMove(new Move(1, 1, 1, 6), board));
	}

	// Verify that a knight cannot move up a column
	@Test
	public final void cannotMoveInColumn() throws Throwable {
		board[1][1] = piece;
		assertFalse("Knight Test 2",
				piece.isValidMove(new Move(1, 1, 6, 1), board));
	}

	// Verify that a knight cannot move diagonally
	@Test
	public final void cannotMoveDiagonal() throws Throwable {
		board[1][1] = piece;
		assertFalse("Knight Test 3",
				piece.isValidMove(new Move(1, 1, 4, 4), board));
	}

	// Verify that a knight cannot move backward
	@Test
	public final void canMoveBackward() throws Throwable {
		board[1][1] = piece;
		assertFalse("Knight Test 5",
				piece.isValidMove(new Move(1, 1, 0, 0), board));
	}

	// Verify that a knight cannot move off the board
	@Test
	public final void cannotMoveOffBoard() throws Throwable {
		board[1][1] = piece;
		assertFalse("Knight Test 6",
				piece.isValidMove(new Move(1, 1, -2, 0), board));
	}

	// Verify that a knight can move correctly.
	@Test
	public final void firstCorrect() throws Throwable {
		board[2][2] = piece;
		assertTrue("Knight Test 7",
				piece.isValidMove(new Move(2, 2, 3, 4), board));
	}

	// Verify that a king can move correctly the other way.
	@Test
	public final void secondCorrect() throws Throwable {
		board[2][2] = piece;
		assertTrue("Knight Test 8",
				piece.isValidMove(new Move(2, 2, 4, 3), board));
	}

	// Verify that a knight can move the third way.
	@Test
	public final void thirdCorrect() throws Throwable {
		board[2][2] = piece;
		assertTrue("Knight Test 9",
				piece.isValidMove(new Move(2, 2, 0, 1), board));
	}
	
	// Verify that a knight can move the third way.
		@Test
		public final void fourthCorrect() throws Throwable {
			board[2][2] = piece;
			assertTrue("Knight Test 10",
					piece.isValidMove(new Move(2, 2, 4, 1), board));
		}
}
