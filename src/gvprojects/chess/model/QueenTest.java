package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*********************************************************************
 * This class tests the {@code Queen} {@code ChessPiece} class. 
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class QueenTest extends ChessPieceTest{
	// This method overrides make(Player p) in the ChessPieceTest class
	// Thus, when running these tests, calling make() 
	// generates a Pawn object
	// whereas calling make when running ChessPiece
	//Tests generates a ChessPiece
	// object.
	
		@Override
		protected final IChessPiece make(final Player p) {
			return new Queen(p);
		}

		// As noted in ChessPieceTest: Many tests require a valid move. 
		// The set of
		// valid moves is different for
		// each chess piece. This method generates a move that is valid from the
		// given row and column
		@Override
		protected final Move getValidMove(final int row, final int col) {
			int newRow = row + 1;
			int newCol = col + 1;
			if (newRow >= board.length) {
				newRow = row - 1;
			}
			if (newCol >= board.length) {
				newCol = col - 1;
			}
			return new Move(row, col, newRow, newCol);
		}

		// Verify that a queen can move across a row
		@Test
		public final void canMoveInRow() throws Exception {
			board[1][1] = piece;
			assertTrue("Bishop Test 1",
					piece.isValidMove(new Move(1, 1, 1, 6), board));
		}

		// Verify that a queen can move up a column
		@Test
		public final void canMoveInColumn() throws Throwable {
			board[1][1] = piece;
			assertTrue("Bishop Test 2",
					piece.isValidMove(new Move(1, 1, 2, 1), board));
		}

		// Verify that a queen can move diagonally
		@Test
		public final void canMoveDiagonal() throws Throwable {
			board[1][1] = piece;
			assertTrue("Bishop Test 3",
					piece.isValidMove(new Move(1, 1, 2, 2), board));
		}

		// Verify that a queen can move diagonally with other player
		@Test
		public final void canMoveDiagonalWithOtherPlayerPiece() 
				throws Throwable {
			board[1][1] = piece;
			board[2][2] = make(Player.BLACK);
			assertTrue("Bishop Test 4",
					piece.isValidMove(new Move(1, 1, 2, 2), board));
		}

		// Verify that a queen cannot move backward
		@Test
		public final void canMoveBackward() throws Throwable {
			board[1][1] = piece;
			assertTrue("Bishop Test 5",
					piece.isValidMove(new Move(1, 1, 0, 0), board));
		}

		// Verify that a queen cannot move off the board
		@Test
		public final void cannotMoveOffBoard() throws Throwable {
			board[1][1] = piece;
			assertFalse("Bishop Test 6",
					piece.isValidMove(new Move(1, 1, -1, 1), board));
		}

		// Verify that a queen cannot jump over other pieces.
		@Test
		public final void pathMustBeClear1() throws Throwable {
			board[2][2] = piece;
			board[3][3] = make();
			assertFalse("Bishop Test 7",
					piece.isValidMove(new Move(2, 2, 4, 4), board));
		}
		
		// Verify that a queen cannot jump over other pieces.
				@Test
				public final void pathMustBeClear2() throws Throwable {
					board[3][2] = piece;
					board[3][3] = make();
					assertFalse("Bishop Test 7",
							piece.isValidMove(new Move(3, 2, 3, 4), board));
				}
				
				// Verify that a queen cannot jump over other pieces.
				@Test
				public final void pathMustBeClear3() throws Throwable {
					board[2][2] = piece;
					board[2][3] = make();
					assertFalse("Bishop Test 7",
							piece.isValidMove(new Move(2, 2, 2, 4), board));
				}
}
