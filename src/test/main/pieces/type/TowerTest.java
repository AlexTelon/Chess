package test.main.pieces.type;

import main.board.Board;
import main.globals.Globals;
import main.pieces.Piece;
import main.pieces.type.Pawn;
import main.pieces.type.Tower;
import main.position.Position;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Tower Tester.
 *
 * @author Alex Telon
 */
public class TowerTest {
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Board board = new Board(pieces); // an empty board
    private Tower piece;

    @Before
    public void before() throws Exception {

        /*
        W_______
        ______B_
        ______W_
        ___B____
        _W_W____
        ________
        _W______
        _______B
         */

        piece = new Tower(board, Globals.Side.White ,0,0);
        board.add(piece);
        piece = new Tower(board, Globals.Side.Black ,7,7);
        board.add(piece);
        piece = new Tower(board, Globals.Side.Black ,3,3);
        board.add(piece);
        piece = new Tower(board, Globals.Side.White ,3,4);
        board.add(piece);
        piece = new Tower(board, Globals.Side.White ,1,6);
        board.add(piece);
        piece = new Tower(board, Globals.Side.Black ,6,1);
        board.add(piece);
        piece = new Tower(board, Globals.Side.White ,6,2);
        board.add(piece);
        piece = new Tower(board, Globals.Side.White ,1,4);
        board.add(piece);
    }

    @After
    public void after() throws Exception {
        board.clear();
    }

    /**
     * Method: getValue()
     */
    @Test
    public void testGetValue() throws Exception {
        assertEquals(5, board.getPiece(new Position(0,0)).getValue());
    }

    /**
     * Method: getPossiblePositions()
     */
    @Test
    public void testGetPossiblePositions() throws Exception {

        // VERY fragile test since the order matters..
        // TODO make this more robust

        // testing normal behaviour, no collisions
        ArrayList<Position> newPositions = new ArrayList<Position>();
        newPositions.add(new Position(1, 0));
        newPositions.add(new Position(2, 0));
        newPositions.add(new Position(3, 0));
        newPositions.add(new Position(4, 0));
        newPositions.add(new Position(5, 0));
        newPositions.add(new Position(6, 0));
        newPositions.add(new Position(7, 0));
        newPositions.add(new Position(0, 1));
        newPositions.add(new Position(0, 2));
        newPositions.add(new Position(0, 3));
        newPositions.add(new Position(0, 4));
        newPositions.add(new Position(0, 5));
        newPositions.add(new Position(0, 6));
        newPositions.add(new Position(0, 7));

        assertEquals(newPositions, board.getPiece(new Position(0,0)).getPossiblePositions());

        // testing behaviour when colliding with both with and black pieces
        newPositions.clear();
        newPositions.add(new Position(2, 4));
        newPositions.add(new Position(4, 4));
        newPositions.add(new Position(5, 4));
        newPositions.add(new Position(6, 4));
        newPositions.add(new Position(7, 4));
        newPositions.add(new Position(3, 5));
        newPositions.add(new Position(3, 6));
        newPositions.add(new Position(3, 7));
        newPositions.add(new Position(3, 3));

        assertEquals(newPositions ,board.getPiece(new Position(3,4)).getPossiblePositions());
    }


} 
