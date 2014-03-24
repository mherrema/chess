package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/*********************************************************************
 * This class tests the {@code Pawn} {@code ChessPiece} class. 
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class PawnTest extends ChessPieceTest {
	
	
	   // This method overrides make(Player p) in the ChessPieceTest class
	   // Thus, when running these tests, calling make() generates a Pawn object
	   // whereas calling make when running ChessPiece Tests generates a ChessPiece object.
	   @Override
	   protected IChessPiece make(Player p) {
		   return new Pawn(p, 1);
	   }

	   // As noted in ChessPieceTest:  Many tests require a valid move.  The set of valid moves is different for
	   // each chess piece.  This method generates a move that is valid from the given row and column
	   @Override
	   protected Move getValidMove(int row, int col) {
	      int newRow = row + 1;
	      int newCol = col + 1;
	      if (newRow >= board.length) {
	         newRow = row - 1;
	      }
	      return new Move(row, col, newRow, newCol);
	   }

	   // Verify that a pawn cannot move across a row
	   @Test
	   public void cannotMoveInRow() throws Exception {
	      board[1][1] = piece;
	      assertFalse("Pawn Test 1", piece.
	    		  isValidMove(new Move(1, 1, 1, 6), board));
	   }

	   // Verify that a pawn can move up a column
	   @Test
	   public void canMoveInColumn() throws Throwable {
	      board[1][1] = piece;
	      assertTrue("Pawn Test 2", piece.
	    		  isValidMove(new Move(1, 1, 2, 1), board));
	   }

	   // Verify that a pawn cannot move diagonally
	   @Test
	   public void cannotMoveDiagonalUnlessOtherPlayerPiece() throws Throwable {
	      board[1][1] = piece;
	      assertFalse("Pawn Test 3", piece.
	    		  isValidMove(new Move(1, 1, 2, 2), board));
	   }
	   
	// Verify that a pawn cannot move diagonally unless other player
	   @Test
	   public void canMoveDiagonalWithOtherPlayerPiece() throws Throwable {
	      board[1][1] = piece;
	      board[2][2] = make(Player.BLACK);
	      assertTrue("Pawn Test 4", piece.
	    		  isValidMove(new Move(1, 1, 2, 2), board));
	   }
	   
	// Verify that a pawn cannot move backward
	   @Test
	   public void cannotMoveBackward() throws Throwable {
	      board[1][1] = piece;
	      assertFalse("Pawn Test 5", piece.
	    		  isValidMove(new Move(1, 1, 0, 1), board));
	   }
	   
	// Verify that a pawn cannot move off the board
	   @Test
	   public void cannotMoveOffBoard() throws Throwable {
	      board[1][1] = piece;
	      assertFalse("Pawn Test 6", piece.
	    		  isValidMove(new Move(1, 1, -1, 1), board));
	   }

	   // Verify that a pawn cannot jump over other pieces.
	   @Test
	   public void rowMustBeClear1() throws Throwable {
	      board[2][2] = piece;
	      board[3][2] = make();
	      assertFalse("Pawn Test 7", piece.
	    		  isValidMove(new Move(2, 2, 4, 2), board));
	   }
}
