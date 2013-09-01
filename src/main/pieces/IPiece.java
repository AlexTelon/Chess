package main.pieces;

import main.Position.Position;

/**
 * @author Alex Telon
 */
public interface IPiece {

    /**
     * Getter for the position of the piece
     * @return
     */
    public Position getPosition();

    /**
     * Try to move to newPosition, if invalid move return false
     * @param newPosition the new position of the piece
     * @return true if move was valid
     */
    public boolean tryMove(Position newPosition);

    /**
     * A kind of destructor for every piece, it cleans itself up from
     * the board.
     */
    public void remove();

}
