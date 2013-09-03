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
    private Pawn piece;

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
        piece.setFirstMove(false);
        board.add(piece);
        piece = new Pawn(board, Side.Black ,7,7);
        piece.setFirstMove(false);
        board.add(piece);
        piece = new Pawn(board, Side.Black ,3,3);
        piece.setFirstMove(false);
        board.add(piece);
        piece = new Pawn(board, Side.White ,4,4);
        piece.setFirstMove(false);
        board.add(piece);
        piece = new Pawn(board, Side.White ,1,6);
        board.add(piece);
        piece = new Pawn(board, Side.Black ,6,1);
        board.add(piece);
        piece = new Pawn(board, Side.White ,6,2);
        piece.setFirstMove(false);
        board.add(piece);
        piece = new Pawn(board, Side.White ,1,4);
        piece.setFirstMove(false);
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

    }

    /**
     *
     * Method: getPossiblePositions()
     *
     */
    @Test
    public void testReturnPossiblePositions() throws Exception {
        // no possible positions
        assertEquals(new ArrayList<Position>(), board.getPiece(new Position(0, 0)).getPossiblePositions());
        assertEquals(new ArrayList<Position>(), board.getPiece(new Position(7, 7)).getPossiblePositions());
        assertEquals(new ArrayList<Position>(), board.getPiece(new Position(6,1)).getPossiblePositions());
        assertEquals(new ArrayList<Position>(), board.getPiece(new Position(6,2)).getPossiblePositions());

        // testing the movement of the middle white pieces options to move
        // A VERY fragile test since the order matters..
        // TODO make this more robust
        ArrayList<Position> newPositions = new ArrayList<Position>();
        newPositions.add(new Position(4, 3));
        newPositions.add(new Position(3, 3));

        assertEquals(newPositions, board.getPiece(new Position(4,4)).getPossiblePositions());

        // testing normal starting movement for white piece position
        newPositions.clear();
        newPositions.add(new Position(1, 5));

        assertEquals(newPositions ,board.getPiece(new Position(1,6)).getPossiblePositions());

        // testing normal movement in the middle of the board
        newPositions.clear();
        newPositions.add(new Position(1, 3));

        assertEquals(board.getPiece(new Position(1,4)).getPossiblePositions(), newPositions);
    }
}

