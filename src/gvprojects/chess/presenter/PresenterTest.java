package gvprojects.chess.presenter;

import static org.junit.Assert.*;
import gvprojects.chess.view.GUI;

import org.junit.Test;

public class PresenterTest {

	@Test
	public final void getBoardTest() throws Exception {
		Presenter pres = new Presenter();
		pres.getBoard();
	}
	
	@Test
	public final void getfromColTest() throws Exception {
		Presenter pres = new Presenter();
		pres.getFromCol();
	}
	
	@Test
	public final void getfromRowTest() throws Exception {
		Presenter pres = new Presenter();
		pres.getFromRow();
	}
	
	@Test
	public final void gettoRowTest() throws Exception {
		Presenter pres = new Presenter();
		pres.getToRow();
	}
	
	@Test
	public final void gettoColTest() throws Exception {
		Presenter pres = new Presenter();
		pres.getToCol();
	}
	
	@Test
	public final void hasPiecetest() throws Exception {
		Presenter pres = new Presenter();
		pres.hasPiece(2, 2);
	}
	
	@Test
	public final void isFromTestt() throws Exception {
		Presenter pres = new Presenter();
		pres.isFrom();
	}
	
	@Test
	public final void setfromRowTest() throws Exception {
		Presenter pres = new Presenter();
		for (int i = 0; i <= 7; i++) {
			pres.setFromRow(i);
		}
	}
	
	@Test
	public final void setfromColTest() throws Exception {
		Presenter pres = new Presenter();
		for (int i = 0; i <= 7; i++) {
			pres.setFromCol(i);
		}
	}
	
	@Test
	public final void setToRowTest() throws Exception {
		Presenter pres = new Presenter();
		for (int i = 0; i <= 7; i++) {
			pres.setToRow(i);
		}
	}
	
	@Test
	public final void setToColTest() throws Exception {
		Presenter pres = new Presenter();
		for (int i = 0; i <= 7; i++) {
			pres.setToCol(i);
		}
	}
	
	@Test
	public final void setFromTest() throws Exception {
		Presenter pres = new Presenter();
		pres.setFrom(true);
		pres.setFrom(false);
	}
	
	@Test
	public final void Test() throws Exception {
		Presenter pres = new Presenter();
		GUI gui = new GUI();
		gui.getNewGame();
	}
	
}
