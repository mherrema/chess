/**
				 * If user clicks "Boneyard" in the menu, display a dialog box
				 * showing the pieces that have been captured
				 */
//				if (e.getSource() == boneyard) {
//					ImageIcon tempIcon = new ImageIcon("skull.png");
//					String fin = "";
//					if (game.getBoneyard().size() == 0) {
//						fin = "Empty";
//					}
//					for (int i = 0; i < game.getBoneyard().size(); i++) {
//						if (i == (game.getBoneyard().size() % 3)) {
//							fin = fin + "\n";
//						}
//						if (i == (game.getBoneyard().size() - 1)) {
//							fin = fin + game.getBoneyard().get(i).toString();
//						} else {
//							fin = fin + game.getBoneyard().get(i).toString() + ", ";
//						}
//					}
//					JOptionPane.showMessageDialog(frame, fin, "Boneyard",
//							JOptionPane.INFORMATION_MESSAGE, tempIcon);
//				}

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


	/******************************************************************
	 * Updates the gameboard
	 * dont have to use this
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
