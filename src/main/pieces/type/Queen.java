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
public class Queen extends Piece implements IPiece {

    public Queen(Board board, Side side, int x, int y) {
        super(board, side, x, y);
    }


    @Override
    public double getValue() {
        return 9;
    }


    @Override
    public ArrayList<Position> getPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,0)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,0)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(0,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(0,-1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,-1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,-1)));

        return  possiblePositions;
    }


}
