package gvprojects.chess.presenter;

import static org.junit.Assert.*;
import gvprojects.chess.model.Model;
import gvprojects.chess.model.Move;

import org.junit.Test;

public class PresentTest {
	@Test
	public final void validInputTest() throws Exception {
		Present pres = new Present();
		pres.isValidInput("2a 3a");
	}
	
	@Test
	public final void updateTest() throws Exception {

	}
	
}
