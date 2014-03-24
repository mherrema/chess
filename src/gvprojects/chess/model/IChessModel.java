/**
 *  File:               IChessModel.java 
 *  Author:             mumfordr, herremam, vassalty
 *  Date:               2009-03-02 15:30:24
 *  Version:            1.0
 */
package gvprojects.chess.model;


/**
 * This is an interface for the model class.
 * 
 * @author Mitch Herrema
 * @version April 10, 2013
 */
public interface IChessModel {

	   /**
	    * Returns whether the game is complete.
	    *
	    * @return {@code true} if complete, {@code false} otherwise.
	    */
	   boolean isComplete();

	   /**
	    * Returns whether the piece at location {@code [move.fromRow, move.
	    * fromColumn]} is allowed to move to location
	    * {@code [move.fromRow, move.fromColumn]}.
	    *
	    * @param move a {@link W13project3.Move} object describing the move 
	    * 				to be made.
	    * @return {@code true} if the proposed move is valid, 
	    * 				{@code false} otherwise.
	    */
	   boolean isValidMove(Move move);

	   /**
	    * Moves the piece from location {@code [move.fromRow, move.
	    * 			fromColumn]} to location {@code [move.fromRow,
	    * 			move.fromColumn]}.
	    *
	    * @param move a {@link W13project3.Move} object describing 
	    * 			the move to be made.
	    */
	   void move(Move move);

	   /**
	    * Report whether the current player p is in check.
	    * @param  p {@link W13project3.Move} the Player being checked
	    * @return {@code true} if the current player is in check, 
	    * 		  {@code false} otherwise.
	    */
	   boolean inCheck(Player p);

	   /**
	    * Return the current player.
	    *
	    * @return the current player
	    */
	   Player currentPlayer();

	}

