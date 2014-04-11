/**
 *  File:               presenter.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.presenter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Model;
import gvprojects.chess.model.Move;
import gvprojects.chess.view.GUI;

public class Presenter {

	/** model */
	private Model m;

	/** view */
	private GUI g;

	private boolean from;
	private String temp;

	private int fromRow;
	private int fromCol;
	private int toRow;
	private int toCol;

	/*********************************************************************
	 * ActionListener and ActionPerformed for array
	 * 
	 * @return none
	 *********************************************************************/
	class boardListener implements ActionListener {

		private int buttonRow;
		private int buttonCol;

		boardListener(int buttonRow, int buttonCol) {
			this.buttonRow = buttonRow;
			this.buttonCol = buttonCol;
		}

		public void actionPerformed(ActionEvent e) {

			if(isFrom() && hasPiece(buttonRow, buttonCol)){
				setFromRow(buttonRow);
				setFromCol(buttonCol);
				g.getBoard()[buttonRow][buttonCol].setBackground(Color.blue);
				g.getBoard()[buttonRow][buttonCol].setOpaque(true);
				//System.out.println(getFromRow()+ " + "+ getFromCol());
				//g.getBoard()[buttonRow][buttonCol].setBackground(Color.BLUE);
				from = false;
				
				getMoves(buttonRow, buttonCol);
				
			}
			else{
				repaint();
				setToRow(buttonRow);
				setToCol(buttonCol);
				//System.out.println(getToRow()+ " + "+ getToCol());
				Move tempMove = new Move(fromRow, fromCol, toRow, toCol);
				m.move(tempMove);
				//System.out.println("self check: " + m.isPutSelfInCheck());
				 
				//g.getBoard()[tempMove.fromRow][tempMove.fromColumn].setOpaque(false);
				g.printBoard(m.getBoard());
				if(m.isPutSelfInCheck()){
					g.putSelfInCheck();
					m.setPutSelfInCheck(false);
				}
				if(m.isOtherPlayerCheck()) {
					g.inCheck();
					m.setOtherPlayerCheck(false);
					m.switchTurns();
				}
				if(m.inCheck(m.currentPlayer())){
				if(m.isComplete()){
					g.checkmate(m.getWinner());
				}
				//g.printBoard(m.getBoard());
				}
				g.updateCurrentPlayer(m.currentPlayer());
				from = true;
			}

			//			if (m.isComplete()) {
			//				g.end();
			//			}

			
			//			// pops up window if white won
			//			if (engine.status() == GameStatus.X_WON) {
			//				window.won(true);
			//			}
			//
			//			// pops up window if black won
			//			if (engine.status() == GameStatus.O_WON) {
			//				window.won(false);
			//			}
		}
	}

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
				g.addButtonListener(i, j, new boardListener(i,
						j));
			}
		}

		// adds action listener to the quit button
		g.getQuit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int option = JOptionPane.showConfirmDialog(g.getFrame(),
						"Are you sure you want to quit?", "Exit Game",
						JOptionPane.YES_NO_OPTION);
				if (option == 0) {
					System.exit(0);
				} else {
					g.setTextString(": Game resumed.\n---------------\n" + g.getTextString());
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
			public void actionPerformed(ActionEvent e) {
				int option = 0;
				if (!m.isComplete())
					option = JOptionPane.showConfirmDialog(g.getFrame(),
							"All current progress will be lost, continue?",
							"New Game", JOptionPane.YES_NO_OPTION);
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
					g.setTextString(": Game resumed.\n*************************\n" + g.getTextString());
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
			public void actionPerformed(ActionEvent e) {
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
			public void actionPerformed(ActionEvent e) {
				try {
					java.awt.Desktop
					.getDesktop()
					.browse(java.net.URI
							.create("http://www.chess.com/learn-how-to-play-chess"));
					g.setTextString(": Rules opened in \n: default browser.\n*************************\n"
							+ g.getTextString());

				} catch (Exception ex) {
					g.setTextString(": Page not found.\n*************************\n" + g.getTextString());
				}
				temp = m.currentPlayer().toString().toLowerCase();
				temp = temp.substring(0, 1).toUpperCase()
						+ temp.substring(1, temp.length()) + "'s turn.\n";
				g.setTextString(": " + temp + g.getTextString());
				g.setText();
			}
		});
	}

	/******************************************************************
	 * Updates the gameboard
	 * 
	 ******************************************************************/
//	public final void update() {
//		g.printBoard(m.getBoard());
//		// if they put themselves in check
//		if (m.isPutSelfInCheck()) {
//			g.putSelfInCheck();
//			m.setPutSelfInCheck(false);
//		}
//		// if they put the other player in check
//		if (m.isOtherPlayerCheck()) {
//			g.inCheck();
//			m.setOtherPlayerCheck(false);
//		}
//		g.prompt(m.currentPlayer());
//
//	}

	public static void main(String[] args) {
		Presenter p = new Presenter();
		p.g.printBoard(p.m.getBoard());
		p.g.updateCurrentPlayer(p.m.currentPlayer());
	}

	public boolean hasPiece(int r, int c){
		if(m.getBoard()[r][c]!=null){
			return true;
		}
		else{
			return false;
		}
	}

	public IChessPiece[][] getBoard() {
		return m.getBoard();
	}
	public boolean isFrom() {
		return from;
	}

	public void setFrom(boolean from) {
		this.from = from;
	}

	public int getFromRow(){
		return fromRow;
	}

	public void setFromRow(int r){
		fromRow = r;
	}

	public int getFromCol(){
		return fromRow;
	}

	public void setFromCol(int c){
		fromCol = c;
	}

	public int getToRow(){
		return fromRow;
	}

	public void setToRow(int r){
		toRow = r;
	}

	public int getToCol(){
		return fromRow;
	}

	public void setToCol(int c){
		toCol = c;
	}
	
	public void getMoves(int row, int col) {
		ArrayList list = new ArrayList();
		
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if (m.pieceAt(row, col).isValidMove(new Move(row, col, j, i), m.getBoard()) == true) {
					g.getBoard()[j][i].setBackground(Color.blue);
				}
			}
		}
	}
	
	public void repaint() {
		for (int j = 0; j < 8; j++) {
			for (int i = 0; i < 8; i++) {
				if (g.getBoard()[j][i].getBackground() == Color.blue) {
					if (j % 2 == 1) {
						if (i % 2 == 0) {
							g.getBoard()[j][i].setBackground(Color.gray);
						} else {
							g.getBoard()[j][i].setBackground(Color.white);
						}
					} else {
						if (j % 2 == 0) {
							if (i % 2 == 1) { 
								g.getBoard()[j][i].setBackground(Color.gray);
							} else {
								g.getBoard()[j][i].setBackground(Color.white);
							}
						}
					}
				}
			}
		}
	}
}



