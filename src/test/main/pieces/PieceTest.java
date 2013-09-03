package test.main.pieces;

import main.board.Board;
import main.globals.Globals;
import main.pieces.Piece;
import main.pieces.type.Pawn;
import main.position.Position;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Piece Tester.
 *
 * @author Alex Telon
 */
public class PieceTest {
    private ArrayList<Piece> pieces = new ArrayList<Piece>();
    private Board board = new Board(pieces); // an empty board
    private Pawn piece;

    @Before
    public void before() throws Exception {
        piece = new Pawn(board, Globals.Side.White ,0,0);
        piece.setFirstMove(false);
        board.add(piece);
        piece = new Pawn(board, Globals.Side.Black ,7,7);
        piece.setFirstMove(false);
        board.add(piece);
    }

    @After
    public void after() throws Exception {
        board.clear();
    }

    /**
     *
     * Method: remove()
     *
     */
    @Test
    public void testRemove() throws Exception {
//TODO: Test goes here... 
    }

    /**
     *
     * Method: getSide()
     *
     */
    @Test
    public void testGetSide() throws Exception {
        assertEquals(board.getPiece(new Position(0,0)).getSide(), Globals.Side.White);
        assertEquals(board.getPiece(new Position(7,7)).getSide(), Globals.Side.Black);
    }

} 
