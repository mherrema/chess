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
	   // whereas calling make when running ChessPiece Tests generates 
	   //a ChessPiece object.
	   @Override
	   protected final IChessPiece make(final Player p) {
		   return new Pawn(p, 1);
	   }

	   // As noted in ChessPieceTest:  Many tests require a valid move.
	   //The set of valid moves is different for
	   // each chess piece.  This method generates a move that is valid 
	   //from the given row and column
	   @Override
	   protected final Move getValidMove(final int row, final int col) {
	      int newRow = row + 1;
	      int newCol = col + 1;
	      if (newRow >= getBoard().length) {
	         newRow = row - 1;
	      }
	      return new Move(row, col, newRow, newCol);
	   }

	   // Verify that a pawn cannot move across a row
	   @Test
	   public final void cannotMoveInRow() throws Exception {
		   getBoard()[1][1] = getPiece();
	      assertFalse("Pawn Test 1", getPiece().
	    		  isValidMove(new Move(1, 1, 1, 6), getBoard()));
	   }

	   // Verify that a pawn can move up a column
	   @Test
	   public final void canMoveInColumn() throws Throwable {
		   getBoard()[1][1] = getPiece();
	      assertTrue("Pawn Test 2", getPiece().
	    		  isValidMove(new Move(1, 1, 2, 1), getBoard()));
	   }

	   // Verify that a pawn cannot move diagonally
	   @Test
	   public final void cannotMoveDiagonalUnlessOtherPlayerPiece() 
			throws Throwable {
		   getBoard()[1][1] = getPiece();
	      assertFalse("Pawn Test 3", getPiece().
	    		  isValidMove(new Move(1, 1, 2, 2), getBoard()));
	   }
	   
	   // Verify that a pawn cannot move diagonally unless other player
	   @Test
	   public final void canMoveDiagonalWithOtherPlayerPiece() 
	   throws Throwable {
		   getBoard()[1][1] = getPiece();
		   getBoard()[2][2] = make(Player.BLACK);
	       assertTrue("Pawn Test 4", getPiece().
	    		  isValidMove(new Move(1, 1, 2, 2), getBoard()));
	   }
	   
	   // Verify that a pawn cannot move backward
	   @Test
	   public final void cannotMoveBackward() throws Throwable {
		   getBoard()[1][1] = getPiece();
	      assertFalse("Pawn Test 5", getPiece().
	    		  isValidMove(new Move(1, 1, 0, 1), getBoard()));
	   }
	   
	   // Verify that a pawn cannot move off the board
	   @Test
	   public final void cannotMoveOffBoard() throws Throwable {
		   getBoard()[1][1] = getPiece();
	      assertFalse("Pawn Test 6", getPiece().
	    		  isValidMove(new Move(1, 1, -1, 1), getBoard()));
	   }

	   // Verify that a pawn cannot jump over other pieces.
	   @Test
	   public final void rowMustBeClear1() throws Throwable {
		   getBoard()[2][2] = getPiece();
		   getBoard()[3][2] = make();
	       assertFalse("Pawn Test 7", getPiece().
	    		  isValidMove(new Move(2, 2, 4, 2), getBoard()));
	   }
}
