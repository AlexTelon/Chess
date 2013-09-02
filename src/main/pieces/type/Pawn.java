package main.pieces.type;

import main.board.Board;
import main.globals.Globals.Side;
import main.pieces.IPiece;
import main.pieces.Piece;
import main.position.Position;

import java.util.ArrayList;

/**
 * @author Alex Telon
 */
public class Pawn  extends Piece implements IPiece {
    private Position position = new Position(-1,-1); // invalid by default
    private Side side;
    private Board board;
    private boolean firstMove = true;

    public Pawn(Board board, Side side,int x, int y) {
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

        if (returnPossiblePositions().contains(newPosition)) {
            if (board.tryMove(this,newPosition)) {
                firstMove = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public void remove() {
        board.remove(this);
    }

    @Override
    public int getValue() {
        return 1;
    }

    @Override
    public Side getSide() {
        return side;
    }

    @Override
    public ArrayList<Position> returnPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        int x = position.getX();
        int y = position.getY();
        Position temp = new Position(0,0);
        Position temp2 = new Position(0,0);

        if (this.getSide() == Side.White) {
            if (temp.setX(x) && temp.setY(y-1)) {
                possiblePositions.add(temp);
            }
            if (firstMove) {
                if (temp2.setX(x) && temp2.setY(y-2)) {
                    possiblePositions.add(temp2);
                }
            }
        } else {
            if (temp.setX(x) && temp.setY(y+1)) {
                possiblePositions.add(temp);
            }
            if (firstMove) {
                if (temp2.setX(x) && temp2.setY(y+2)) {
                    possiblePositions.add(temp2);
                }
            }
        }
        return  possiblePositions;
    }

}
