package gvprojects.chess.view;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Move;
import gvprojects.chess.model.Player;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.TitledBorder;

/**********************************************************************
 * A class that represents the GUI portion of the game of chess.
 * 
 * @authors Mitch Herrema
 * @version Winter 2013
 **********************************************************************/
public class GUI extends JPanel {

	/**
	 * Timer instance variable
	 */
	long startTime = System.currentTimeMillis();

	/**
	 * GUI component instance variables
	 */
	private JFrame frame;
	private JPanel panel;
	private JPanel rightPanel;
	private JTextPane pane;
	private JScrollPane scroll;
	private JMenuBar menu;
	private JMenu file;
	private JMenu help;
	private JMenuItem quit;
	private JMenuItem newGame;
	private JMenuItem gameTime;
	private JMenuItem version;
	private JMenuItem rules;
	private JMenuItem boneyard;

	/**
	 * Board instance variable
	 */
	private JButton board[][];

	/**
	 * Boolean instance variables
	 */
	boolean colorBool;
	boolean pickedUp;
	boolean gameComplete;

	/**
	 * Color instance variables
	 */
	Color tileColor;
	Color moveColor;

	/**
	 * ButtonListener instance variable
	 */
	ButtonListener l;

	/**
	 * Integer instance variables
	 */
	int x;
	int y;
	final int ROWS = 8;
	final int COLS = 8;

	/**
	 * String instance variables
	 */
	private String textString;
	private String temp;

	/**
	 * ImageIcon instance variables
	 */
	ImageIcon piece;
	ImageIcon wPawn;
	ImageIcon bPawn;
	ImageIcon wRook;
	ImageIcon bRook;
	ImageIcon wQueen;
	ImageIcon bQueen;
	ImageIcon wKing;
	ImageIcon bKing;
	ImageIcon wBishop;
	ImageIcon bBishop;
	ImageIcon wKnight;
	ImageIcon bKnight;
	ImageIcon emptyIcon;

	/**
	 * Timer instance variable
	 */
	public GUI() {
		/**
		 * Initialize the game and set the player
		 */
		// game = new ChessModel();
		// game.setCurrentPlayer(Player.WHITE);

		/**
		 * Initialize all piece ImageIcons
		 */
		wPawn = new ImageIcon("wPawn.png");
		bPawn = new ImageIcon("bPawn.png");
		wRook = new ImageIcon("wRook.png");
		bRook = new ImageIcon("bRook.png");
		wKnight = new ImageIcon("wKnight.png");
		bKnight = new ImageIcon("bKnight.png");
		wQueen = new ImageIcon("wQueen.png");
		bQueen = new ImageIcon("bQueen.png");
		wKing = new ImageIcon("wKing.png");
		bKing = new ImageIcon("bKing.png");
		wBishop = new ImageIcon("wBishop.png");
		bBishop = new ImageIcon("bBishop.png");
		emptyIcon = new ImageIcon("emptyIcon.png");
		piece = emptyIcon;

		/**
		 * Initialize Strings
		 */
		setTextString("");
		temp = "";

		/**
		 * Initialize the GUI components and set defaults
		 */
		frame = new JFrame("Awesome Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new JPanel(new GridLayout(8, 8));
		rightPanel = new JPanel();
		pane = new JTextPane();
		scroll = new JScrollPane(pane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		/**
		 * Initialize the ints
		 */
		x = 0;
		y = 0;

		/**
		 * Initialize the menus and add components respectively
		 */
		// boneyard = new JMenuItem("Boneyard");
		menu = new JMenuBar();
		file = new JMenu("File");
		help = new JMenu("Help");
		quit = new JMenuItem("Quit");
		setNewGame(new JMenuItem("New Game"));
		setVersion(new JMenuItem("Version"));
		gameTime = new JMenuItem("Game Time");
		setRules(new JMenuItem("Rules"));
		frame.setJMenuBar(menu);
		menu.add(file);
		menu.add(help);
		file.add(getNewGame());
		// file.add(boneyard);
		file.add(gameTime);
		file.add(quit);
		help.add(getVersion());
		help.add(getRules());

		/**
		 * Initialize the board
		 */
		board = new JButton[ROWS][COLS];

		/**
		 * Initialize the ActionListener
		 */
		l = new ButtonListener();

		/**
		 * Add action listeners
		 */
		getNewGame().addActionListener(l);
		quit.addActionListener(l);
		// boneyard.addActionListener(l);
		gameTime.addActionListener(l);
		getVersion().addActionListener(l);
		getRules().addActionListener(l);

		/**
		 * Initialize booleans
		 */
		colorBool = false;
		pickedUp = false;

		/**
		 * Initialize custom colors
		 */
		tileColor = new Color(150, 150, 150);
		moveColor = new Color(77, 255, 77);

		/**
		 * Set borders and layouts of the panel and pane
		 */
		pane.setBorder(new TitledBorder("Status"));
		rightPanel.setLayout(new BoxLayout(rightPanel, 1));

		/**
		 * Call three native methods that initialize the buttons in the panel,
		 * paint the checkered board, and fill it with the individual pieces
		 */
		initializeButtons();
		quickPaint();

		/**
		 * Set default size of panel and pane
		 */
		frame.setPreferredSize(new Dimension(920, 700));
		scroll.setPreferredSize(new Dimension(183, 700));

		/**
		 * Set the pane uneditable
		 */
		pane.setEditable(false);

		/**
		 * Add components to the respective places, pack, and set visibility
		 */
		rightPanel.add(scroll);
		frame.add(panel, BorderLayout.CENTER);
		frame.add(rightPanel, BorderLayout.EAST);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	}

	/**
	 * Set the player turn on the status
	 */
	public void updateCurrentPlayer(Player p) {
		temp = p.toString();
		temp = temp.substring(0, 1).toUpperCase()
				+ temp.substring(1, temp.length()) + "'s turn.\n";
		setTextString(": " + temp + getTextString());
		setText();
	}

	/**********************************************************************
	 * A class that represents the listeners for the board and all of the
	 * buttons.
	 * 
	 * @authors Ryan Hoezee Tyler Vassallo Joe Willard Jon Wood
	 * @version Winter 2013
	 **********************************************************************/
	public class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			/**
			 * If user clicks "Game Time" in the menu, display a dialog box
			 * showing the current run time of the game
			 */
			if (e.getSource() == gameTime) {
				ImageIcon tempIcon = new ImageIcon("clock.png");
				long time = (System.currentTimeMillis() - startTime) / 1000;
				long min = time / 60;
				long sec = time % 60;
				JOptionPane.showMessageDialog(frame, "Game time: " + min
						+ " min. " + sec + " sec. ", "Game Time",
						JOptionPane.INFORMATION_MESSAGE, tempIcon);
			}

			/**
			 * For every board space
			 */
			// for (int i = 0; i < 8; i++) {
			// for (int j = 0; j < 8; j++) {
			//
			// /**
			// * If the user clicked the board and the game is not complete
			// */
			// if (e.getSource() == board[i][j] && !gameComplete) {
			//
			// /**
			// * Initialize a temporary list of ints as well as a game board
			// */
			// ArrayList<Integer> tempList = new ArrayList<Integer>();
			// IChessPiece[][] tempBoard = game.getBoard();
			//
			// /**
			// * If a piece is not already picked up and the space clicked is
			// not empty
			// */
			// if (pickedUp == false
			// && board[i][j].getIcon().equals(emptyIcon) == false) {
			//
			// /**
			// * Simulate all possible moves for selected piece and color spaces
			// */
			// for (int a = 0; a < 8; a++) {
			// for (int b = 0; b < 8; b++) {
			// if (tempBoard[i][j].player() == game
			// .currentPlayer()) {
			// if (game.isValidMove(new Move(i, j, a,
			// b)) == true ||
			// game.castlingLegal(new Move(i, j, a, b))) {
			// board[a][b]
			// .setBackground(moveColor);
			// board[i][j]
			// .setBackground(moveColor);
			// tempList.add(1);
			// }
			// }
			// }
			// }
			//
			// /**
			// * If there are no moves and the same space was clicked
			// */
			// if (tempList.size() != 0) {
			// pickedUp = true;
			// x = i;
			// y = j;
			// }
			//
			// /**
			// * Else if a piece has already been picked up
			// */
			// } else {
			// if (pickedUp == true) {
			//
			// /**
			// * Create a new move and player
			// */
			// Move m = new Move(x, y, i, j);
			// Player p = game.currentPlayer();
			//
			// /**
			// * If castling is legal, execute it
			// */
			// if (game.castlingLegal(m)) {
			// game.performCastle(m);
			// if (j - y == -2) {
			// board[i][j].setIcon(board[x][y].getIcon());
			// board[x][y].setIcon(emptyIcon);
			// board[i][3].setIcon(board[x][0].getIcon());
			// board[x][0].setIcon(emptyIcon);
			// }
			// else {
			// board[i][j].setIcon(board[x][y].getIcon());
			// board[x][y].setIcon(emptyIcon);
			// board[i][5].setIcon(board[x][7].getIcon());
			// board[x][7].setIcon(emptyIcon);
			// }
			// }
			// /**
			// * If isValidMove is true make the move
			// */
			// else if (game.isValidMove(m) == true) {
			// game.move(new Move(x, y, i, j));
			//
			// /**
			// * Set the player turn on the pane
			// */
			// temp = game.currentPlayer().toString()
			// .toLowerCase();
			// temp = temp.substring(0, 1).toUpperCase()
			// + temp.substring(1, temp.length())
			// + "'s turn.\n";
			// textString = ": " + temp + textString;
			// pane.setText(textString);
			// if (game.lastMoveValid()) {
			// board[i][j].setIcon(board[x][y]
			// .getIcon());
			// board[x][y].setIcon(emptyIcon);
			// }
			// }
			// gameStatus(p, m);
			// pickedUp = false;
			// }
			// /**
			// * Clear the status and repaint the checkered board
			// */
			// clearStatus();
			// quickPaint();
			// }
			// }
			// }
			/**
			 * Update all components
			 */
			SwingUtilities.updateComponentTreeUI(frame);
		}
	}

	public void setVersionText(Player p) {
		/**
		 * If user clicks "Version" in the menu, display the version in the
		 * status pane
		 */

	}

	/**
	 * GameStatus calls the methods that determine, in order, whether a piece
	 * needs to be promoted, whether the current player is in checkmate, and
	 * whether the current player is in check.
	 * 
	 * @param Player
	 * @param Move
	 */
	// private void gameStatus(Player p, Move m) {
	// IChessPiece piece = null;// = game.checkPromotion(p,
	// // m);
	// if (piece != null) {
	// if (p == Player.WHITE) {
	// if (piece.type().equals("Rook"))
	// board[m.toRow][m.toColumn].setIcon(wRook);
	// if (piece.type().equals("Knight"))
	// board[m.toRow][m.toColumn].setIcon(wKnight);
	// if (piece.type().equals("Bishop"))
	// board[m.toRow][m.toColumn].setIcon(wBishop);
	// if (piece.type().equals("Queen"))
	// board[m.toRow][m.toColumn].setIcon(wQueen);
	// } else {
	// if (piece.type().equals("Rook"))
	// board[m.toRow][m.toColumn].setIcon(bRook);
	// if (piece.type().equals("Knight"))
	// board[m.toRow][m.toColumn].setIcon(bKnight);
	// if (piece.type().equals("Bishop"))
	// board[m.toRow][m.toColumn].setIcon(bBishop);
	// if (piece.type().equals("Queen"))
	// board[m.toRow][m.toColumn].setIcon(bQueen);
	// }
	//
	// }


	public void checkmate(Player p) {
		long time = (System.currentTimeMillis() - startTime) / 1000;
		long min = time / 60;
		long sec = time % 60;
		ImageIcon winIcon = new ImageIcon("winner.png");

		if (p.toString().equals("WHITE")) {
			JOptionPane.showMessageDialog(frame,
					"Black has been checkmated.  White wins!" + "\nGame time: "
							+ min + " min. " + sec + " sec.", "Game Over",
					JOptionPane.INFORMATION_MESSAGE, winIcon);
		} else {
			JOptionPane.showMessageDialog(frame,
					"White has been checkmated.  Black wins!" + "\nGame time: "
							+ min + " min. " + sec + " sec.", "Game Over",
					JOptionPane.INFORMATION_MESSAGE, winIcon);
		}
	}

	//
	// /**
	// * White is in check
	// */
	// if (game.inCheck(game.currentPlayer())) {
	// if (game.currentPlayer() == Player.WHITE)
	// JOptionPane
	// .showMessageDialog(
	// frame,
	// "White King is in check!",
	// "Check",
	// JOptionPane.INFORMATION_MESSAGE,
	// checkIcon);
	//
	// /**
	// * Black is in check
	// */
	// else
	// JOptionPane
	// .showMessageDialog(
	// frame,
	// "Black King is in check!",
	// "Check",
	// JOptionPane.INFORMATION_MESSAGE,
	// checkIcon);
	// }
	// }
	// }

	/**
	 * A native method that clears the status pane
	 */
	// private void clearStatus() {
	// temp = game.currentPlayer().toString().toLowerCase();
	// temp = temp.substring(0, 1).toUpperCase()
	// + temp.substring(1, temp.length()) + "'s turn.\n";
	// textString = ": " + temp;
	// pane.setText(textString);
	// }
	// }

	/**
	 * A native method that resets the time of the game
	 */
	public void resetTime() {
		startTime = System.currentTimeMillis();
	}

	/**
	 * A native method that initializes the buttons on the board
	 */
	public void initializeButtons() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				board[i][j] = new JButton();
				board[i][j].addActionListener(l);
				panel.add(board[i][j]);
			}
		}
	}

	/**
	 * A native method that paints the checkered background
	 */
	public void quickPaint() {
		Color tempColor = Color.WHITE;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				getBoard()[i][j].setBackground(tempColor);
				if (colorBool == true) {
					colorBool = false;
					tempColor = Color.WHITE;
				} else {
					colorBool = true;
					tempColor = tileColor;
				}
			}

			if (colorBool == true) {
				colorBool = false;
				tempColor = Color.WHITE;
			} else {
				colorBool = true;
				tempColor = tileColor;
			}
		}
	}

	/*********************************************************************
	 * Adds ActionListeners to the button array
	 * 
	 * @param i
	 *            row of button
	 * @param j
	 *            column of button
	 * @param actionListener
	 *            the action listener from the Present class
	 **********************************************************************/
	public void addButtonListener(int i, int j, ActionListener actionListener) {
		getBoard()[i][j].addActionListener(actionListener);
	}

	public JButton[][] getBoard() {
		return board;
	}

	public void setButtons(JButton[][] buttons) {
		this.board = buttons;
	}

	public static void main(String[] args) {
		GUI g = new GUI();
	}

	public void printBoard(IChessPiece[][] gameBoard) {

		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				if (gameBoard[i][j] != null) {
					if (gameBoard[i][j].player() == Player.WHITE) {
						if (gameBoard[i][j].type().equals("Rook")) {
							board[i][j].setIcon(wRook);
						} else if (gameBoard[i][j].type().equals("Knight")) {
							board[i][j].setIcon(wKnight);
						} else if (gameBoard[i][j].type().equals("Bishop")) {
							board[i][j].setIcon(wBishop);
						} else if (gameBoard[i][j].type().equals("Queen")) {
							board[i][j].setIcon(wQueen);
						} else if (gameBoard[i][j].type().equals("King")) {
							board[i][j].setIcon(wKing);
						} else if (gameBoard[i][j].type().equals("Pawn")) {
							board[i][j].setIcon(wPawn);
						}
					} else {
						if (gameBoard[i][j].type().equals("Rook")) {
							board[i][j].setIcon(bRook);
						} else if (gameBoard[i][j].type().equals("Knight")) {
							board[i][j].setIcon(bKnight);
						} else if (gameBoard[i][j].type().equals("Bishop")) {
							board[i][j].setIcon(bBishop);
						} else if (gameBoard[i][j].type().equals("Queen")) {
							board[i][j].setIcon(bQueen);
						} else if (gameBoard[i][j].type().equals("King")) {
							board[i][j].setIcon(bKing);
						} else if (gameBoard[i][j].type().equals("Pawn")) {
							board[i][j].setIcon(bPawn);
						}
					}
				} else {
					board[i][j].setIcon(emptyIcon);
				}
			}
		}
	}

	public void setText() {
		pane.setText(textString);
	}

	public JFrame getFrame() {
		return frame;
	}

	public JMenuItem getQuit() {
		return quit;
	}

	public JMenuItem getNewGame() {
		return newGame;
	}

	public JMenuItem getVersion() {
		return version;
	}

	public JMenuItem getRules() {
		return rules;
	}

	public void putSelfInCheck() {
		setTextString(": You can't put\n: yourself in check!\n*************************\n"
				+ getTextString());
		setText();
	}

	public void inCheck() {
		// TODO Auto-generated method stub
		setTextString(": You placed them in check!\n*************************\n"
				+ getTextString());
		setText();
	}

	public void prompt(Player currentPlayer) {
		// TODO Auto-generated method stub

	}

	public void repack() {
		// TODO Auto-generated method stub

	}

	public void update(Move tempMove) {
		// TODO Auto-generated method stub

	}

	public void end() {
		// TODO Auto-generated method stub

		JOptionPane.showMessageDialog(null, "Checkmate!");

	}

	public String getTextString() {
		return textString;
	}

	public void setTextString(String textString) {
		this.textString = textString;
	}

	public void reset() {
		// TODO Auto-generated method stub

	}

	public void setNewGame(JMenuItem newGame) {
		this.newGame = newGame;
	}

	public void setVersion(JMenuItem version) {
		this.version = version;
	}

	public void setRules(JMenuItem rules) {
		this.rules = rules;
	}
}
