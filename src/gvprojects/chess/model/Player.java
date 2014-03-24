/**
 *  File:               Player.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;

/**
 * This class defines a player.
 */
public enum Player {
	
	/**
	 * Both player colors.
	 */
	BLACK, WHITE;

	/**
	 * Return the {@code Player} whose turn is next.
	 * 
	 * @return the {@code Player} whose turn is next
	 */
	public Player next() {
		if (this == BLACK) {
			return WHITE;
		}
		return BLACK;
	}
}
	
