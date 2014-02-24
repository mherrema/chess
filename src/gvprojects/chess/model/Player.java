package gvprojects.chess.model;

/**
 * This class defines a player
 */
public enum Player {
	
	/**
	 * Both player colors
	 */
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 * 
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() {
		return this == BLACK ? WHITE : BLACK;
	}
}
	
