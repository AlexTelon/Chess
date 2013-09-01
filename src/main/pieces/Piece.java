package main.pieces;

import main.position.Position;

/**
 * @author Alex Telon
 */
public class Piece implements IPiece {
    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public boolean tryMove(Position newPosition) {
        return false;
    }

    @Override
    public void remove() {

    }
}
