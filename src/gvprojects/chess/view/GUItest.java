package gvprojects.chess.view;

import static org.junit.Assert.*;

import javax.swing.JButton;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Player;
import gvprojects.chess.presenter.Presenter;
import gvprojects.chess.view.GUI;

import org.junit.Test;

public class GUItest {
	
	@Test
	public final void checkmateTextTestWhite() throws Throwable {
		GUI gui = new GUI();
		Player p = Player.WHITE;
		gui.checkmate(p);
	}
	
	public final void checkmateTextTestBlack() throws Throwable {
		GUI gui = new GUI();
		gui.checkmate(Player.BLACK);
	}
	
	@Test
	public final void paintBoardTest() throws Throwable {
		Presenter pres = new Presenter();
		GUI gui = new GUI();
		gui.printBoard(pres.getBoard());
	}
	
	@Test
	public final void updateCurrentPlayerTestWhite() throws Throwable {
		GUI gui = new GUI();
		Player p = Player.WHITE;
		gui.updateCurrentPlayer(p);
	}
	@Test
	public final void updateCurrentPlayerTestBlack() throws Throwable {
		GUI gui = new GUI();
		Player p = Player.BLACK;
		gui.updateCurrentPlayer(p);
	}
	@Test
	public final void resetTimeTest() throws Throwable {
		GUI gui = new GUI();
		gui.resetTime();
	}
	@Test
	public final void resetButtonsTest() throws Throwable {
		GUI gui = new GUI();
		gui.setButtons(gui.getButtons());
	}
	@Test
	public final void setTextTest() throws Throwable {
		GUI gui = new GUI();
		gui.setText();
	}
	@Test
	public final void getFrameTest() throws Throwable {
		GUI gui = new GUI();
		gui.getFrame();
	}
	@Test
	public final void putSelfInCheckTest() throws Throwable {
		GUI gui = new GUI();
		gui.putSelfInCheck();
	}
	@Test
	public final void inCheckTest() throws Throwable {
		GUI gui = new GUI();
		gui.inCheck();
	}
	@Test
	public final void endTest() throws Throwable {
		GUI gui = new GUI();
		gui.end();
	}
}
