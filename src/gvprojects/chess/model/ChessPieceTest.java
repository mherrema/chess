package gvprojects.chess.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test the {@code ChessPiece} class
 *
 * @author Zachary Kurmas
 */
// Created  12/8/12 at 9:28 PM
// (C) Zachary Kurmas 2012

public class ChessPieceTest {

   private static final int BOARD_SIZE = 8;

   // The @Before method below creates these objects for every test.
   // Doing so saves us the trouble of having to explicitly create 
   // them in (almost) every test.
   protected IChessPiece[][] board;
   protected IChessPiece piece;

   @Before
public final void makeBoard() {
      // Don't put any pieces on the board.
      board = new IChessPiece[BOARD_SIZE][BOARD_SIZE];
      piece = make();
   }


   // These methods create the particular type of chess piece we intend to test.
   // Here, we create ChessPiece; but, subclasses will override this
   // method to create
   
   // Kings, Bishops, Pawns, etc.
   protected IChessPiece make(Player p) {
      return new ChessPiece(p, "George");
   }

   // Helper method to create a white pawn. (This is just here because I'm lazy)
   protected final IChessPiece make() {
      return make(Player.WHITE);
   }

   // To test isValidMove, we need to generate a valid move to test.
   // Each piece has a different set of valid moves.  Subclasses override this
   // method to generate a valid move.  When testing the parent 
   // ChessPiece class,
   // we make a "dummy" move of moving the piece up one row.
   
   protected Move getValidMove(int row, int col) {
      return new Move(row, col, row + 1, col);
   }

   /////////////////////////////////////////////////////////////////////////////
   //
   // The actual tests
   //
   ////////////////////////////////////////////////////////////////////////////

   @Test
public final void nameIsCorrect() throws Throwable {
      final String theName = "Plain, old chess piece";
      assertEquals(theName, (new ChessPiece(Player.WHITE, theName)).type());
   }

   @Test
public final void playerIsCorrect() throws Throwable {
      assertEquals(Player.WHITE, make(Player.WHITE).player());
      assertEquals(Player.BLACK, make(Player.BLACK).player());
   }

   @Test(expected = IllegalArgumentException.class)
public final void complainsIfFromLocIsNull() throws Throwable {
      piece.isValidMove(getValidMove(0, 3), board);
   }

   @Test(expected = IllegalArgumentException.class)
public final void complainsIfFromLocIsDifferentObject() throws Throwable {
      board[1][3] = make();
      assertFalse("ChessPiece Test 2", 
    		  piece.isValidMove(getValidMove(1, 3), board));
   }

   @Test
public final void complainsIfStartingAndEndingLocationsAreIdentical() 
		throws Throwable {
      board[4][4] = piece;
      assertFalse("Move onto same loc", 
    		  piece.isValidMove(new Move(4, 4, 4, 4), board));
   }


   @Test
public final void complainsIfTargetOccupiedBySamePlayer() throws Throwable {
      Move move = getValidMove(2, 4);
      board[move.toRow][move.toColumn] = make();
      board[move.fromRow][move.fromColumn] = piece;
      assertFalse("ChessPiece Test 3", piece.isValidMove(move, board));
   }

   @Test
public final void canCapture() throws Throwable {
      Move move = getValidMove(2, 4);
      board[move.toRow][move.toColumn] = make(piece.player().next());
      board[move.fromRow][move.fromColumn] = piece;
      assertTrue("ChessPiece Test 4", piece.isValidMove(move, board));
   }
}
