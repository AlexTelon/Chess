package test.main.pieces.type;

import main.board.Board;
import main.globals.Globals.Side;
import main.pieces.Piece;
import main.pieces.type.Pawn;
import main.position.Position;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Pawn Tester.
 *
 * @author Alex Telon
 * @version 1.0
 */
public class PawnTest {
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Board board = new Board(pieces); // an empty board
    private Piece piece;

    @Before
    public void before() throws Exception {

        /*
        W_______
        ______B_
        ______W_
        ___B____
        _W__W___
        ________
        _W______
        _______B
         */

        piece = new Pawn(board, Side.White ,0,0);
        board.add(piece);
        piece = new Pawn(board, Side.Black ,7,7);
        board.add(piece);
        piece = new Pawn(board, Side.Black ,3,3);
        board.add(piece);
        piece = new Pawn(board, Side.White ,4,4);
        board.add(piece);
        piece = new Pawn(board, Side.White ,1,6);
        board.add(piece);
        piece = new Pawn(board, Side.Black ,6,1);
        board.add(piece);
        piece = new Pawn(board, Side.White ,6,2);
        board.add(piece);
        piece = new Pawn(board, Side.White ,1,4);
        board.add(piece);
    }

    @After
    public void after() throws Exception {
        board.clear();
    }


    /**
     *
     * Method: tryMove(Position newPosition)
     *
     */
    @Test
    public void testTryMove() throws Exception {

        // Try to go outside board
        assertFalse(board.tryMove(board.getPiece(new Position(0, 0)), new Position(-1, 0)));
        assertFalse(board.tryMove(board.getPiece(new Position(0, 0)), new Position(0, -1)));
        assertFalse(board.tryMove(board.getPiece(new Position(7, 7)), new Position(7, 8)));
        assertFalse(board.tryMove(board.getPiece(new Position(7, 7)), new Position(8, 7)));

        // attack another piece
        Piece piece = board.getPiece(new Position(4, 4));
        if (board.tryMove(piece, new Position(3, 3))) {
            assertSame(board.getPiece(new Position(3,3)), piece);
        }

        // try to make some invalid moves
        assertFalse(board.tryMove(board.getPiece(new Position(6, 2)), new Position(6, 1)));
        assertFalse(board.tryMove(board.getPiece(new Position(6, 1)), new Position(6, 2)));
        assertFalse(board.tryMove(board.getPiece(new Position(6, 1)), new Position(6, 3)));

    }

    /**
     *
     * Method: getValue()
     *
     */
    @Test
    public void testGetValue() throws Exception {
        assertEquals(board.getPiece(new Position(0,0)).getValue(),1);
    }

    /**
     *
     * Method: getSide()
     *
     */
    @Test
    public void testGetSide() throws Exception {
        assertEquals(board.getPiece(new Position(0,0)).getSide(), Side.White);
        assertEquals(board.getPiece(new Position(7,7)).getSide(),Side.Black);
    }

    /**
     *
     * Method: getPossiblePositions()
     *
     */
    @Test
    public void testReturnPossiblePositions() throws Exception {
        assertNull(board.getPiece(new Position(0,0)).getPossiblePositions());
        assertNull(board.getPiece(new Position(7,7)).getPossiblePositions());
        assertNull(board.getPiece(new Position(6,1)).getPossiblePositions());
        assertNull(board.getPiece(new Position(6,2)).getPossiblePositions());

        // testing the movement of the middle white pieces options to move
        ArrayList<Position> newPositions = new ArrayList<Position>();
        newPositions.add(new Position(4, 4));
        newPositions.add(new Position(4, 3));

        assertEquals(board.getPiece(new Position(4,4)).getPossiblePositions(), newPositions);

        // testing normal starting movement for white piece position
        newPositions.clear();
        newPositions.add(new Position(1, 5));
        newPositions.add(new Position(1, 4));

        assertEquals(board.getPiece(new Position(1,6)).getPossiblePositions(), newPositions);

        // testing normal movement in the middle of the board
        newPositions.clear();
        newPositions.add(new Position(1, 3));

        assertEquals(board.getPiece(new Position(1,4)).getPossiblePositions(), newPositions);
    }
}

