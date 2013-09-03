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
    private boolean firstMove = true;

    public Pawn(Board board, Side side, int x, int y) {
       super(board, side, x, y);
    }

    @Override
    public boolean tryMove(Position newPosition) {

        if (getPossiblePositions().contains(newPosition)) {
            if (super.getBoard().tryMove(this,newPosition)) {
                firstMove = false;
                return true;
            }
        }
        return false;
    }

    @Override
    public int getValue() {
        return 1;
    }


    @Override
    public ArrayList<Position> getPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        int x = super.getPosition().getX();
        int y = super.getPosition().getY();
        int direction;
        if (this.getSide() == Side.White) {
            direction = -1;
        } else {
            direction = 1;
        }

        possiblePositions.addAll(getBasicForwardMovement(direction));

        // if we are on the top/bottom row then turn into other piece.
        // so no possible movement is possible.
        if (y != 0 && y != 7) {
            // Show possible left attacking movement
            if (x != 0) {
                Position attackingLeft = new Position(x-1,y+direction);
                if (!super.getBoard().isEmpty(attackingLeft)) {
                    if (!super.getBoard().isSameSidePiece(attackingLeft, this.getSide())) {
                        possiblePositions.add(attackingLeft);
                    }
                }
            }
            // Show possible right attacking movement
            if (x != 7) {
                Position attackingRight = new Position(x+1,y+direction);
                if (!super.getBoard().isEmpty(attackingRight)) {
                    if (!super.getBoard().isSameSidePiece(attackingRight, this.getSide())) {
                        possiblePositions.add(attackingRight);
                    }
                }
            }
        }
        return  possiblePositions;
    }

    private ArrayList<Position> getBasicForwardMovement(int direction) {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        int x = super.getPosition().getX();
        int y = super.getPosition().getY();
        Position temp = new Position(0,0);
        Position temp2 = new Position(0,0);
        // add the positions for basic movement forward
        if (y != 0 && y != 7) {
            if (temp.setX(x) && temp.setY(y+direction)) {
                if (super.getBoard().isEmpty(temp)) {
                    possiblePositions.add(temp);
                } else // if a piece is in the way don't continue past
                    return possiblePositions;
            }
            if (firstMove) {
                if (temp2.setX(x) && temp2.setY(y+direction*2)) {
                    if (super.getBoard().isEmpty(temp2))
                        possiblePositions.add(temp2);
                }
            }
        }
        return possiblePositions;
    }

    /**
     * Set/unset if this is the pieces first move.
     * Only used in tests.
     * @param firstMove
     */
    public void setFirstMove(boolean firstMove) {
        this.firstMove = firstMove;
    }

}
