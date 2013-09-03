package main.pieces.type;

import main.board.Board;
import main.globals.Globals.Side;
import main.pieces.IPiece;
import main.pieces.Piece;
import main.position.Position;
import main.position.Vector;

import java.util.ArrayList;

/**
 * @author Alex Telon
 */
public class Tower extends Piece implements IPiece {
    private Position position = new Position(-1,-1); // invalid by default
    private Side side;
    private Board board;

    public Tower(Board board, Side side, int x, int y) {
        this.board = board;
        this.side = side;
        position.setX(x);
        position.setY(y);
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
    public int getValue() {
        return 5;
    }

    @Override
    public Side getSide() {
        return side;
    }


    @Override
    public ArrayList<Position> getPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        int x = position.getX();
        int y = position.getY();

        Position temp = new Position(this.position);

        /**
         * Could make a subclass for the below. But then we need a way to get if we are moving in
         * x or y. The most intuetive way would be to have vectors but then that
         */

        // Find vertical movement
        while(temp.addY(1) && (board.isEmpty(temp) || !board.isSameSidePiece(temp, this.getSide()))) {
            // If there is a friendly piece in the way stop.
            possiblePositions.add(new Position(temp));
        }

        temp = new Position(this.position);
        // Find vertical movement
        while(temp.addY(-1) && (board.isEmpty(temp) || !board.isSameSidePiece(temp, this.getSide()))) {
            // If there is a friendly piece in the way stop.
            possiblePositions.add(new Position(temp));
        }

        temp = new Position(this.position);
        // Find horizontal movement
        while(temp.addX(1) && (board.isEmpty(temp) || !board.isSameSidePiece(temp, this.getSide()))) {
            // If there is a friendly piece in the way stop.
            possiblePositions.add(new Position(temp));
        }

        temp = new Position(this.position);
        // Find horizontal movement
        while(temp.addX(-1) && (board.isEmpty(temp) || !board.isSameSidePiece(temp, this.getSide()))) {
            // If there is a friendly piece in the way stop.
            possiblePositions.add(new Position(temp));
        }

        return  possiblePositions;
    }

    private ArrayList<Position> getPossiblePositionsInDirection(Vector vector) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();
        Position temp = new Position(this.position);
        boolean blockFound = false;

        // only add empty pieces, if enemy piece is found add it and then stop.
        while (temp.canMove(vector) && !blockFound) {
            if (!board.isEmpty(temp))
                blockFound = true;
            if (!board.isSameSidePiece(temp, this.getSide()))
                possiblePositions.add(new Position(temp));
        }
        return possiblePositions;
    }


}
