package gvprojects.chess.model;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/*********************************************************************
 * This class tests the {@code Rook} {@code ChessPiece} class. 
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class RookTest extends ChessPieceTest {


   // This method overrides make(Player p) in the ChessPieceTest class
   // Thus, when running these tests, calling make() generates a Rook object
   // whereas calling make when running ChessPiece 
	// Tests generates a ChessPiece object.
   @Override
protected final IChessPiece make(final Player p) {
     // System.out.println("rook");
	   return new Rook(p);
   }

   // As noted in ChessPieceTest:  Many tests require a valid move.  
   // The set of valid moves is different for
   // each chess piece.  This method generates a 
   // move that is valid from the given row and column
   @Override
   protected final Move getValidMove(final int row, final int col) {
       int newRow = row + 1;
       if (newRow >= getBoard().length) {
          newRow = row - 1;
       }
       return new Move(row, col, newRow, col);
   }

   // Verify that a rook can move across a row
   @Test
   public final void canMoveInRow() throws Exception {
	   getBoard()[1][1] = getPiece();
       assertTrue("Rook Test 1", getPiece().isValidMove(new Move(1, 1, 1, 6), 
    		   getBoard()));
   }

   // Verify that a rook can move up and down a column
   @Test
   public final void canMoveInColumn() throws Throwable {
	   getBoard()[1][1] = getPiece();
       assertTrue("Rook Test 2", getPiece().isValidMove(new Move(1, 1, 6, 1), 
    		   getBoard()));
   }

   // Verify that a rook cannot move diagonally
   @Test
   public final void cannotMoveDiagonal() throws Throwable {
	   getBoard()[1][1] = getPiece();
       assertFalse("Rook Test 3", getPiece().isValidMove(
    		  new Move(1, 1, 3, 3), getBoard()));
   }
   
   // Verify that a rook cannot move off the board
   @Test
   public final void cannotMoveOffBoard() throws Throwable {
	   getBoard()[1][1] = getPiece();
       assertFalse("Rook Test 3", getPiece().isValidMove(
    		  new Move(1, 1, 9, 1), getBoard()));
   }

   // Verify that a rook cannot jump over other pieces.
   @Test
   public final void rowMustBeClear1() throws Throwable {
	   getBoard()[2][2] = getPiece();
	   getBoard()[3][2] = make();
       assertFalse("Rook Test 4", getPiece().isValidMove(
    		  new Move(2, 2, 4, 2), getBoard()));
   }

}
