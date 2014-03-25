package gvprojects.chess.view;

import gvprojects.chess.model.Model;
import gvprojects.chess.model.Player;
import gvprojects.chess.presenter.Presenter;

import org.junit.Test;

public class ViewTest {

	@Test
	public final void viewTest() throws Throwable {
		View view = new View();
	}
	
	@Test
	public final void printBoardTest() throws Throwable {
		View view = new View();
		Presenter pres = new Presenter();
		view.printBoard(pres.getBoard());
	}
	
	@Test
	public final void pieceToStringTest() throws Throwable {
		View view = new View();
		Model mod = new Model();
		view.pieceToString(mod.pieceAt(0, 0));
	}
	
	@Test
	public final void promptTest() throws Throwable {
		View view = new View();
		view.prompt(Player.WHITE);
	}
	
	@Test
	public final void putSelfInCheckTest() throws Throwable {
		View view = new View();
		view.putSelfInCheck();
	}
	
	@Test
	public final void invalidInputTest() throws Throwable {
		View view = new View();
		view.invalidInput();
	}
	
	@Test
	public final void inCheckTest() throws Throwable {
		View view = new View();
		view.inCheck();
	}

}
