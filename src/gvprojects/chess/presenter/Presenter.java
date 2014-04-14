/**
 *  File:               presenter.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.presenter;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Model;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;
import gvprojects.chess.view.GUI;

/**
 * Created a presenter class for the GUI to be displayed.
 *
 */
public class Presenter {
	/** Initializing variables. */
	private final int three = 3;
	/** Initializing variables. */
	private final int eight = 8;
	/** Initializing variables. */
	private final int fifty = 50;

	/** model. */
	private Model m;

	/** view. */
	private GUI g;

	/** Initialize variables. */
	private boolean from;
	/** Initialize variables. */
	private String temp;
	/** Initialize variables. */
	private int fromRow;
	/** Initialize variables. */
	private int fromCol;
	/** Initialize variables. */
	private int toRow;
	/** Initialize variables. */
	private int toCol;

	/*********************************************************************
	 * ActionListener and ActionPerformed for array.
	 *********************************************************************/
	class BoardListener implements ActionListener {
		/** Initialize variables. */
		private int buttonRow;
		/** Initialize variables. */
		private int buttonCol;

		/**
		 * Initializes the board listener.
		 * @param abuttonRow int
		 * @param abuttonCol int
		 */
		BoardListener(final int abuttonRow, final int abuttonCol) {
			this.buttonRow = abuttonRow;
			this.buttonCol = abuttonCol;
		}

		/**
		 * Creates the actionPerformed class.
		 * 
		 * @param e ActionEvent
		 */
		public void actionPerformed(final ActionEvent e) {

			if (isFrom() && hasPiece(buttonRow, buttonCol)) {
				setFromRow(buttonRow);
				setFromCol(buttonCol);
				g.getBoard()[buttonRow][buttonCol].setBackground(Color.blue);
				from = false;
				
				getMoves(buttonRow, buttonCol);
				cursor(buttonRow, buttonCol);
				
			} else {
				g.getFrame().setCursor(Cursor.getDefaultCursor());
				setToRow(buttonRow);
				setToCol(buttonCol);
				Move tempMove = new Move(fromRow, fromCol, toRow, toCol);
				m.move(tempMove);
				g.printBoard(m.getBoard());
				if (m.isPutSelfInCheck()) {
					g.putSelfInCheck();
					m.setPutSelfInCheck(false);
				}
				if (m.isOtherPlayerCheck()) {
					g.inCheck();
					m.setOtherPlayerCheck(false);
					m.switchTurns();
				}
				if (m.inCheck(m.currentPlayer())) {
				if (m.isComplete()) {
					g.checkmate(m.getWinner());
				}
				}
				g.updateCurrentPlayer(m.currentPlayer());
				from = true;
			}
		}
	}

	/**
	 * Initializing the Presenter method.
	 */
	public Presenter() {
		m = new Model();
		g = new GUI();
		setFrom(true);
		fromRow = 0;
		fromCol = 0;
		toCol = 0;
		toRow = 0;

		// adds action listeners to all the buttons in the array
		for (int i = 0; i < g.getBoard().length; i++) {
			for (int j = 0; j < g.getBoard()[0].length; j++) {
				g.addButtonListener(i, j, new BoardListener(i,
						j));
			}
		}

		// adds action listener to the quit button
		g.getQuit().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(g.getFrame(),
						"Are you sure you want to quit?", "Exit Game",
						JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					System.exit(0);
				} else {
					g.setTextString(": Game resumed.\n---------------\n" 
							+ g.getTextString());
					temp = m.currentPlayer().toString().toLowerCase();
					temp = temp.substring(0, 1).toUpperCase()
							+ temp.substring(1, temp.length()) + "'s turn.\n";
					g.setTextString(": " + temp + g.getTextString());
					g.setText();
				}
			}
		});

		// adds action listener to the quit button
		g.getNewGame().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				int option = 0;
				if (!m.isComplete()) {
					option = JOptionPane.showConfirmDialog(g.getFrame(),
							"All current progress will be lost, continue?",
							"New Game", JOptionPane.YES_NO_OPTION);
				}
				if (option == 0) {
					m = new Model();
					g.printBoard(m.getBoard());
					g.setTextString(": New game.\n*************************\n");
					temp = m.currentPlayer().toString().toLowerCase();
					temp = temp.substring(0, 1).toUpperCase()
							+ temp.substring(1, temp.length()) + "'s turn.\n";
					g.setTextString(": " + temp + g.getTextString());
					g.setText();
					g.resetTime();
				} else {
					g.setTextString(": Game resumed.\n*************"
							+ "************\n" + g.getTextString());
					temp = m.currentPlayer().toString().toLowerCase();
					temp = temp.substring(0, 1).toUpperCase()
							+ temp.substring(1, temp.length()) + "'s turn.\n";
					g.setTextString(": " + temp + g.getTextString());
					g.setText();
				}
			}
		});

		// adds action listener to the quit button
		g.getVersion().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				g.setTextString("*************************\n"
						+ "       Awesome Chess\n           Version 2.0\n"
						+ "*************************\n" + g.getTextString());
				temp = m.currentPlayer().toString().toLowerCase();
				temp = temp.substring(0, 1).toUpperCase()
						+ temp.substring(1, temp.length()) + "'s turn.\n";
				g.setTextString(": " + temp + g.getTextString());
				g.setText();
			}
		});

		/**
		 * If user clicks "Rules" in the menu, open the default web browser
		 * with a web site detailing the rules of chess
		 */
		g.getRules().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
				try {
					java.awt.Desktop
					.getDesktop()
					.browse(java.net.URI
							.create("http://www.chess.com/"
									+ "learn-how-to-play-chess"));
					g.setTextString(": Rules opened in \n: default browser."
							+ "\n*************************\n"
							+ g.getTextString());

				} catch (Exception ex) {
					g.setTextString(": Page not found.\n**************"
							+ "***********\n" + g.getTextString());
				}
				temp = m.currentPlayer().toString().toLowerCase();
				temp = temp.substring(0, 1).toUpperCase()
						+ temp.substring(1, temp.length()) + "'s turn.\n";
				g.setTextString(": " + temp + g.getTextString());
				g.setText();
			}
		});
		
		g.getBoneyard().addActionListener(new ActionListener() {
			public void actionPerformed(final ActionEvent e) {
			ImageIcon tempIcon = new ImageIcon("skull.png");
			String fin = "";
			if (m.getBoneyard().size() == 0) {
				fin = "Empty";
			} else {
			for (int i = 0; i < m.getBoneyard().size(); i++) {
				if (i == (m.getBoneyard().size() % three)) {
					fin = fin + "\n";
				}
				if (i == (m.getBoneyard().size() - 1)) {
					fin = fin + m.getBoneyard().get(i).player()
							+ " " + m.getBoneyard().get(i).type();
				} else {
					fin = fin + m.getBoneyard().get(i).player() 
							+ " " + m.getBoneyard().get(i).type() + ", ";
				}
			}
			
		}
			JOptionPane.showMessageDialog(g.getFrame(), fin, "Boneyard",
					JOptionPane.INFORMATION_MESSAGE, tempIcon);
			}
		});
	}

	/**
	 * Main function.
	 * @param args String[]
	 */
	public static void main(final String[] args) {
		Presenter p = new Presenter();
		p.g.printBoard(p.m.getBoard());
		p.g.updateCurrentPlayer(p.m.currentPlayer());
	}

	/**
	 * Creates the has Piece class which checks to see if 
	 * you have a piece in the spot.
	 * 
	 * @param r int
	 * @param c int
	 * @return boolean
	 */
	public final boolean hasPiece(final int r, final int c) {
		return m.getBoard()[r][c] != null;
	}

	/**
	 * Initializes the getBoard class.
	 * @return board
	 */
	public final IChessPiece[][] getBoard() {
		return m.getBoard();
	}
	
	/**
	 * Method returns where the piece is from.
	 * @return from
	 */
	public final boolean isFrom() {
		return from;
	}

	/**
	 * Method sets where the piece was from.
	 * @param afrom boolean
	 */
	public final void setFrom(final boolean afrom) {
		this.from = afrom;
	}

	/**
	 * returns the row where the piece was from.
	 * @return fromRow
	 */
	public final int getFromRow() {
		return fromRow;
	}

	/**
	 * Sets where the row was from.
	 * @param r int
	 */
	public final void setFromRow(final int r) {
		fromRow = r;
	}

	/**
	 * returns which column the piece was from.
	 * @return fromRow int
	 */
	public final int getFromCol() {
		return fromRow;
	}

	/**
	 * sets which column the piece was from.
	 * @param c int
	 */
	public final void setFromCol(final int c) {
		fromCol = c;
	}

	/**
	 * returns the row the piece is going to.
	 * @return fromRow int
	 */
	public final int getToRow() {
		return fromRow;
	}

	/**
	 * sets the row where the piece is going.
	 * @param r int
	 */
	public final void setToRow(final int r) {
		toRow = r;
	}

	/**
	 * returns the column the piece is going to.
	 * @return fromRow int
	 */
	public final int getToCol() {
		return fromRow;
	}
	
	/**
	 * sets the column the piece is going to.
	 * @param c int
	 */
	public final void setToCol(final int c) {
		toCol = c;
	}
	
	/**
	 * This method returns all of the possible moves that a piece could have.
	 * @param row int
	 * @param col int
	 */
	public final void getMoves(final int row, final int col) {
		boolean[][] tmpMoves = m.getMoves(row, col);
		for (int i = 0; i < eight; i++) {
			for (int j = 0; j < eight; j++) {
				if (tmpMoves[i][j]) {
					g.printMove(i, j);
				}
			}
		}
		
	}
	
	/**
	 * Creates a highlight of the areas where the pieces could move.
	 * @param r int
	 * @param c int
	 */
	public final void cursor(final int r, final int c) {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		  Image image = toolkit.getImage("emptyIcon.png");
		  
		  if (m.pieceAt(r, c).toString().contains("Bishop")) {
			  if (m.pieceAt(r, c).player() == Player.WHITE) {
				  image = toolkit.getImage("wBishop.png");
			  } else {
				  image = toolkit.getImage("bBishop.png");
			  }
		  }
		  
		  if (m.pieceAt(r, c).toString().contains("Knight")) {
			  if (m.pieceAt(r, c).player() == Player.WHITE) {
				  image = toolkit.getImage("wKnight.png");
			  } else {
				  image = toolkit.getImage("bKnight.png");
			  }
		  }
		  
		  if (m.pieceAt(r, c).toString().contains("Pawn")) {
			  if (m.pieceAt(r, c).player() == Player.WHITE) {
				  image = toolkit.getImage("wPawn.png");
			  } else {
				  image = toolkit.getImage("bPawn.png");
			  }
		  }
		  
		  if (m.pieceAt(r, c).toString().contains("Rook")) {
			  if (m.pieceAt(r, c).player() == Player.WHITE) {
				  image = toolkit.getImage("wRook.png");
			  } else {
				  image = toolkit.getImage("bRook.png");
			  }
		  }
		  
		  if (m.pieceAt(r, c).toString().contains("King")) {
			  if (m.pieceAt(r, c).player() == Player.WHITE) {
				  image = toolkit.getImage("wKing.png");
			  } else {
				  image = toolkit.getImage("bKing.png");
			  }
		  }
		  
		  if (m.pieceAt(r, c).toString().contains("Queen")) {
			  if (m.pieceAt(r, c).player() == Player.WHITE) {
				  image = toolkit.getImage("wQueen.png");
			  } else {
				  image = toolkit.getImage("bQueen.png");
			  }
		  }
		  
		  Cursor curs = toolkit.createCustomCursor(image, 
				  new Point(fifty, fifty), "cursor");
		  Image img = toolkit.getImage("emptyIcon.png");
		  ImageIcon empty = new ImageIcon(img);
		  g.getBoard()[r][c].setIcon(empty);
		  g.getFrame().setCursor(curs);
	}
}



