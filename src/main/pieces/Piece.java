package main.pieces;

import main.board.Board;
import main.globals.Globals;
import main.position.Position;
import main.position.Vector;

import java.util.ArrayList;

/**
 * @author Alex Telon
 */
public class Piece implements IPiece {
    private ArrayList<Position> possiblePositions = new ArrayList<Position>();
    private Position position = new Position(-1,-1); // invalid by default
    private Globals.Side side;
    private Board board;


    public Piece(Board board, Globals.Side side, int x, int y) {
        this.board = board;
        this.side = side;
        // TODO, could lead to half changed positions..
        this.position.setX(x);
        this.position.setY(y);
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public boolean tryMove(Position newPosition) {
        if (getPossiblePositions().contains(newPosition)) {
            return board.tryMove(this,newPosition);
        }
        return false;
    }

    @Override
    public void remove() {
        board.remove(this);
    }

    @Override
    public double getValue() {
        return -100; // this way we know if something went wrong
    }

    @Override
    public Globals.Side getSide() {
        return side;
    }

    @Override
    public ArrayList<Position> getPossiblePositions() {
        return possiblePositions;
    }

    public Board getBoard() {
        return board;
    }

    /**
     * Gets possible positions for any piece but the pawn.
     * @param vector direction that the function will look for possible positions
     * @return all possible positions
     */
    public ArrayList<Position> getPossiblePositionsInDirection(Vector vector) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        Position temp = new Position(position);
        boolean blockFound = false;

        // only add empty places, if enemy piece is found add it and then stop.
        while (temp.canMove(vector) && !blockFound) {
            temp.add(vector);
            if (!board.isEmpty(temp))
                blockFound = true;
            if (!board.isSameSidePiece(temp, this.getSide()))
                possiblePositions.add(new Position(temp));
        }
        return possiblePositions;
    }

    /**
     * Gets possible positions for any piece but the pawn.
     * @param vector direction that the function will look for possible positions
     * @return all possible positions
     */
    public Position getPossiblePositionInDirection(Vector vector) {
        Position temp = new Position(position);

        if (temp.canMove(vector)) {
            temp.add(vector);
            if (board.isSameSidePiece(temp, this.getSide())) {
                return null;
            }
            return temp;
        } else {
            return null;
        }
    }

}
