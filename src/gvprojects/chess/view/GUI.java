/**
 *  File:               GUI.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.view;

import gvprojects.chess.model.IChessPiece;
import gvprojects.chess.model.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.border.TitledBorder;

/**********************************************************************
 * A class that represents the GUI portion of the game of chess.
 * 
 * author Mitch Herrema
 * @version Winter 2013
 **********************************************************************/
public class GUI extends JPanel {

	/** number variable. */
	private final int eight = 8;
	/** number variable. */
	private final int onefifty = 150;
	/** number variable. */
	private final int seventyseven = 77;
	/** number variable. */
	private final int twofiftyfive = 255;
	/** number variable. */
	private final int ninetwenty = 920;
	/** number variable. */
	private final int sevenhundred = 700;
	/** number variable. */
	private final int oneeightythree = 183;
	/** number variable. */
	private final int onethousand = 1000;
	/** number variable. */
	private final int sixty = 60;
	

	/** serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Timer instance variable. */
	private long startTime1 = System.currentTimeMillis();

	/** GUI component instance variables. */
	private JFrame frame;
	/** GUI component instance variables. */
	private JPanel panel;
	/** GUI component instance variables. */
	private JPanel rightPanel;
	/** GUI component instance variables. */
	private JTextPane pane;
	/** GUI component instance variables. */
	private JScrollPane scroll;
	/** GUI component instance variables. */
	private JMenuBar menu;
	/** GUI component instance variables. */
	private JMenu file;
	/** GUI component instance variables. */
	private JMenu help;
	/** GUI component instance variables. */
	private JMenuItem quit;
	/** GUI component instance variables. */
	private JMenuItem newGame;
	/** GUI component instance variables. */
	private JMenuItem gameTime;
	/** GUI component instance variables. */
	private JMenuItem version;
	/** GUI component instance variables. */
	private JMenuItem rules;
	/** GUI component instance variables. */
	private JMenuItem boneyard;

	/**
	 * Board instance variable.
	 */
	private JButton[][] board;

	/** Boolean instance variables. */
	private boolean colorBool;
	
	/** Color instance variables. */
	private Color tileColor;
	
	/**
	 * ButtonListener instance variable.
	 */
	private ButtonListener l;

	/** Integer instance variables. */
	private final int rows = 8;
	/** Integer instance variables. */
	private final int cols = 8;

	/** String instance variables. */
	private String textString;
	/** String instance variables. */
	private String temp;

	/** ImageIcon instance variables. */
	private ImageIcon wPawn;
	/** ImageIcon instance variables. */
	private ImageIcon bPawn;
	/** ImageIcon instance variables. */
	private ImageIcon wRook;
	/** ImageIcon instance variables. */
	private ImageIcon bRook;
	/** ImageIcon instance variables. */
	private ImageIcon wQueen;
	/** ImageIcon instance variables. */
	private ImageIcon bQueen;
	/** ImageIcon instance variables. */
	private ImageIcon wKing;
	/** ImageIcon instance variables. */
	private ImageIcon bKing;
	/** ImageIcon instance variables. */
	private ImageIcon wBishop;
	/** ImageIcon instance variables. */
	private ImageIcon bBishop;
	/** ImageIcon instance variables. */
	private ImageIcon wKnight;
	/** ImageIcon instance variables. */
	private ImageIcon bKnight;
	/** ImageIcon instance variables. */
	private ImageIcon emptyIcon;

	/**
	 * Timer instance variable.
	 */
	public GUI() {
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
		panel = new JPanel(new GridLayout(eight, eight));
		rightPanel = new JPanel();
		pane = new JTextPane();
		scroll = new JScrollPane(pane);
		scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants
				.HORIZONTAL_SCROLLBAR_NEVER);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants
				.VERTICAL_SCROLLBAR_ALWAYS);

		/**
		 * Initialize the menus and add components respectively
		 */
		setBoneyard(new JMenuItem("Boneyard"));
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
		file.add(getBoneyard());
		file.add(gameTime);
		file.add(quit);
		help.add(getVersion());
		help.add(getRules());

		/**
		 * Initialize the board
		 */
		board = new JButton[rows][cols];

		/**
		 * Initialize the ActionListener
		 */
		l = new ButtonListener();

		/**
		 * Add action listeners
		 */
		getNewGame().addActionListener(l);
		quit.addActionListener(l);
		getBoneyard().addActionListener(l);
		gameTime.addActionListener(l);
		getVersion().addActionListener(l);
		getRules().addActionListener(l);

		/**
		 * Initialize booleans
		 */
		colorBool = false;
		/**
		 * Initialize custom colors
		 */
		tileColor = new Color(onefifty, onefifty, onefifty);
		new Color(seventyseven, twofiftyfive, seventyseven);

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
		frame.setPreferredSize(new Dimension(ninetwenty, sevenhundred));
		scroll.setPreferredSize(new Dimension(oneeightythree, sevenhundred));

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
	 * Set the player turn on the status.
	 * 
	 * @param p Player
	 */
	public final void updateCurrentPlayer(final Player p) {
		temp = p.toString().toLowerCase();
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
		/**
		 * Creates the actionPerformed method.
		 * 
		 * @param e ActionEvent
		 */
		public final void actionPerformed(final ActionEvent e) {
			/**
			 * If user clicks "Game Time" in the menu, display a dialog box
			 * showing the current run time of the game
			 */
			if (e.getSource() == gameTime) {
				ImageIcon tempIcon = new ImageIcon("clock.png");
				long time = (System.currentTimeMillis() - startTime1) 
						/ onethousand;
				long min = time / sixty;
				long sec = time % sixty;
				JOptionPane.showMessageDialog(frame, "Game time: " + min
						+ " min. " + sec + " sec. ", "Game Time",
						JOptionPane.INFORMATION_MESSAGE, tempIcon);
			}

			/**
			 * Update all components
			 */
			//SwingUtilities.updateComponentTreeUI(frame);
		}
	}
	
	/**
	 * This class checks to see if a player is in checkmate.
	 * @param p Player
	 */
	 public final void checkmate(final Player p) {
		long time = (System.currentTimeMillis() - startTime1) / onethousand;
		long min = time / sixty;
		long sec = time % sixty;
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
		System.exit(0);
	}

	/**
	 * A native method that resets the time of the game.
	 */
	public final void resetTime() {
		startTime1 = System.currentTimeMillis();
	}

	/**
	 * A native method that initializes the buttons on the board.
	 */
	public final void initializeButtons() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = new JButton();
				board[i][j].addActionListener(l);
				panel.add(board[i][j]);
			}
		}
	}

	/**
	 * A native method that paints the checkered background.
	 */
	public final void quickPaint() {
		Color tempColor = Color.WHITE;
		for (int i = 0; i < eight; i++) {
			for (int j = 0; j < eight; j++) {
				getBoard()[i][j].setBackground(tempColor);
				if (colorBool) {
					colorBool = false;
					tempColor = Color.WHITE;
				} else {
					colorBool = true;
					tempColor = tileColor;
				}
			}

			if (colorBool) {
				colorBool = false;
				tempColor = Color.WHITE;
			} else {
				colorBool = true;
				tempColor = tileColor;
			}
		}
	}

	/*********************************************************************
	 * Adds ActionListeners to the button array.
	 * 
	 * @param i
	 *            row of button
	 * @param j
	 *            column of button
	 * @param actionListener
	 *            the action listener from the Present class
	 **********************************************************************/
	public final void addButtonListener(final int i, final int j, 
			final ActionListener actionListener) {
		getBoard()[i][j].addActionListener(actionListener);
	}

	/**
	 * returns the board.
	 * @return board JButton[][]
	 */
	public final JButton[][] getBoard() {
		return board;
	}

	/**
	 * sets the buttons on the board.
	 * @param buttons JButton[][]
	 */
	public final void setButtons(final JButton[][] buttons) {
		this.board = buttons;
	}
	
	/**
	 * returns the buttons for the board.
	 * @return board JButton[][]
	 */
	public final JButton[][] getButtons() {
		return this.board;
	}

	/**
	 * prints the board.
	 * @param gameBoard IChessPiece[][]
	 */
	public final void printBoard(final IChessPiece[][] gameBoard) {

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
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
		for (int j = 0; j < eight; j++) {
			for (int i = 0; i < eight; i++) {
				board[i][j].setOpaque(true);
				if (board[j][i].getBackground() == Color.blue) {
					if (j % 2 == 1) {
						if (i % 2 == 0) {
							board[j][i].setBackground(Color.gray);
						} else {
							board[j][i].setBackground(Color.white);
						}
					} else {
						if (j % 2 == 0) {
							if (i % 2 == 1) { 
								board[j][i].setBackground(Color.gray);
							} else {
								board[j][i].setBackground(Color.white);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * Sets the text string.
	 */
	public final void setText() {
		pane.setText(textString);
	}

	/**
	 * returns the frame.
	 * @return frame JFrame
	 */
	public final JFrame getFrame() {
		return frame;
	}

	/**
	 * Returns the quit button.
	 * @return quit JMenuItem
	 */
	public final JMenuItem getQuit() {
		return quit;
	}

	/**
	 * returns a new game.
	 * @return newGame JMenuItem
	 */
	public final JMenuItem getNewGame() {
		return newGame;
	}

	/**
	 * gets the version number.
	 * @return version JMenuItem
	 */
	public final JMenuItem getVersion() {
		return version;
	}

	/**
	 * returns the rules.
	 * @return rules JMenuItem
	 */
	public final JMenuItem getRules() {
		return rules;
	}

	/**
	 * Prints text if you put yourself in check.
	 */
	public final void putSelfInCheck() {
		setTextString(": You can't put\n: yourself in check!\n****"
				+ "*********************\n"
				+ getTextString());
		setText();
	}

	/**
	 * Sets text if someone is in check.
	 */
	public final void inCheck() {
		setTextString(": You placed them in check!\n*************************\n"
				+ getTextString());
		setText();
	}

	/**
	 * returns the text string to be printed.
	 * @return textString String
	 */
	public final String getTextString() {
		return textString;
	}

	/**
	 * Sets the textString.
	 * @param atextString String
	 */
	public final void setTextString(final String atextString) {
		this.textString = atextString;
	}

	/**
	 * Sets the new game.
	 * @param anewGame JMenuItem
	 */
	public final void setNewGame(final JMenuItem anewGame) {
		this.newGame = anewGame;
	}

	/**
	 * Allows for the set of the new version.
	 * @param aversion JMenuItem
	 */
	public final void setVersion(final JMenuItem aversion) {
		this.version = aversion;
	}

	/**
	 * Allows to set the rules.
	 * @param arules JMenuItem
	 */
	public final void setRules(final JMenuItem arules) {
		this.rules = arules;
	}

	/**
	 * Prints out the move that took place.
	 * @param row int
	 * @param col int
	 */
	public final void printMove(final int row, final int col) {
		board[row][col].setBackground(Color.blue);
	}

	/**
	 * returns the boneyard items.
	 * @return boneyard JMenuItem
	 */
	public final JMenuItem getBoneyard() {
		return boneyard;
	}

	/**
	 * sets the boneyard if it is changed.
	 * @param aboneyard JMenuItem
	 */
	public final void setBoneyard(final JMenuItem aboneyard) {
		this.boneyard = aboneyard;
	}
}
