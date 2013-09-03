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
public class Knight extends Piece implements IPiece {

    public Knight(Board board, Side side, int x, int y) {
        super(board, side, x, y);
    }


    @Override
    public double getValue() {
        return 3.5;
    }


    @Override
    public ArrayList<Position> getPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        possiblePositions.add(getPossiblePositionInDirection(new Vector(2, 1)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(2, -1)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(-2, 1)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(-2, -1)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(1,2)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(-1,2)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(1,-2)));
        possiblePositions.add(getPossiblePositionInDirection(new Vector(-1,-2)));

        return  possiblePositions;
    }


}
