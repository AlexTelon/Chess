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
public class Bishop extends Piece implements IPiece {

    public Bishop(Board board, Side side, int x, int y) {
        super(board, side, x, y);
    }


    @Override
    public double getValue() {
        return 3.5;
    }


    @Override
    public ArrayList<Position> getPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,-1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,-1)));

        return  possiblePositions;
    }


}
