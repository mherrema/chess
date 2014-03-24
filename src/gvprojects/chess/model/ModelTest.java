package gvprojects.chess.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

/*********************************************************************
 * This class tests the {@code Model} class.
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 *********************************************************************/

public class ModelTest {
	// Verify that starting player is white
	@Test
	public final void startingPlayerIsWhite() throws Exception {
		Model m = new Model();
		assertTrue("Model Test 1", m.currentPlayer() == Player.WHITE);
	}

	// Verify that check occurs when needed
	@Test
	public final void testCheck() throws Exception {
		Model m = new Model();
		Move move1 = new Move(6, 4, 5, 4);
		Move move2 = new Move(1, 3, 2, 3);
		Move move3 = new Move(7, 5, 3, 1);
		m.move(move1);
		m.move(move2);
		m.move(move3);
		assertTrue("Model Test 2", m.inCheck(Player.BLACK));
	}

	// Verify that check occurs when moving into check
	@Test
	public final void testMoveInCheck() throws Exception {
		Model m = new Model();
		Move move1 = new Move(6, 4, 5, 4);
		Move move2 = new Move(1, 3, 2, 3);
		Move move3 = new Move(7, 5, 3, 1);
		Move move4 = new Move(0, 4, 1, 3);
		m.move(move1);
		m.move(move2);
		m.move(move3);
		m.move(move4);
		assertTrue("Model Test 3", m.inCheck(Player.BLACK));
		assertTrue("Model Test 4", m.currentPlayer() == Player.WHITE);
	}

	// Verify that check goes away when moving another piece in the way 
	//and player is white
	@Test
	public final void testMoveOutOfCheck() throws Exception {
		Model m = new Model();
		Move move1 = new Move(6, 4, 5, 4);
		Move move2 = new Move(1, 3, 2, 3);
		Move move3 = new Move(7, 5, 3, 1);
		Move move4 = new Move(0, 2, 1, 3);
		m.move(move1);
		m.move(move2);
		m.move(move3);
		m.move(move4);
		assertFalse("Model Test 5", m.inCheck(Player.WHITE));
		assertTrue("Model Test 6", 
				m.currentPlayer() == Player.WHITE);
	}

	// Verify switch turns
	@Test
	public final void switchTurns() throws Exception {
		Model m = new Model();
		m.switchTurns();
		assertTrue("Model Test 7", m.currentPlayer() == Player.BLACK);
	}

	// Verify cancel move
	@Test
	public final void cancelMove() throws Exception {
		Model m = new Model();
		IChessPiece temporary = m.pieceAt(6, 3);
		Move move1 = new Move(6, 3, 5, 3);
		m.move(move1);
		m.cancelMove(move1);
		assertTrue("Model Test 8", m.pieceAt(6, 3) == temporary);
	}
	
	@Test
	public final void isComplete() throws Exception {
		Model m = new Model();
		Move move1 = new Move(6, 5, 5, 5);
		Move move2 = new Move(1, 4, 2, 4);
		Move move3 = new Move(6, 6, 4, 6);
		Move move4 = new Move(0, 3, 4, 7);
//		Move move1 = new Move(5, 2, 5, 3);
//		Move move2 = new Move(4, 6, 4, 5);
//		Move move3 = new Move(6, 1, 6, 3);
//		Move move4 = new Move(0, 4, 1, 3);
		m.move(move1);
		m.move(move2);
		m.move(move3);
		m.move(move4);
		m.isComplete();
		assertTrue(m.getWinner()==Player.BLACK);
		//assertTrue(m.getWinner() == Player.BLACK);
		//assertTrue("Model Test 3", m.isComplete());
		//assertTrue("Model Test 4", m.currentPlayer() == Player.BLACK);
	}
	
	// Verify kings move
		@Test
		public final void moveKings() throws Exception {
			Model m = new Model();
			Move move1 = new Move(6, 4, 5, 4);
			Move move2 = new Move(7, 4, 6, 4);
			Move move3 = new Move(1, 4, 2, 4);
			Move move4 = new Move(0, 4, 1, 4);
			Move move5 = new Move(6, 0, 5, 0);
			m.move(move1);
			m.move(move3);
			m.move(move2);
			m.cancelMove(move2);
			m.move(move5);
			m.move(move4);
			m.cancelMove(move4);
		}
	
		// Verify kings move
		@Test
		public final void selfCheck() throws Exception {
			Model m = new Model();
			Move move1 = new Move(6, 7, 5, 7);
			Move move2 = new Move(1, 4, 2, 4);
			Move move3 = new Move(5, 7, 4, 7);
			Move move4 = new Move(0, 5, 4, 1);
			Move move5 = new Move(6, 3, 5, 3);
			m.move(move1);
			m.move(move2);
			m.move(move3);
			m.move(move4);
			m.move(move5);
			assertTrue("Model Test 11", m.isPutSelfInCheck());
		}
	
}