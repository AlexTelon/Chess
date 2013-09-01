package main.board;


import main.globals.Globals.Side;
import main.pieces.Piece;
import main.pieces.type.Pawn;
import main.position.Position;

import java.util.ArrayList;

/**
 * @author Alex Telon
 */
public class Board {
    private ArrayList<Piece> pieces = new ArrayList<Piece>();


    // A map of the board with all its pieces
    private Piece[][] board = new Piece[8][8];

    /**
     * Default chess board is created
     */
    public Board() {
      // white:
      for (int i = 0; i < 8; i++) {
          board[6][i] = new Pawn(this, Side.White, 6, i);
      }
    }

    /**
     * Removes piece from the board
     * @param piece piece to be removed
     */
    public void remove(Piece piece) {
        this.pieces.remove(piece);

        int x = piece.getPosition().getX();
        int y = piece.getPosition().getY();
        board[y][x] = null;
    }

    /**
     * Sees if the new position is occupied by an piece from the same side
     * @param newPosition possible position of a piece
     * @param side side to compare the (possible) other piece with
     * @return true if on the same side
     */
    public boolean isSameSidePiece(Position newPosition, Side side) {
        int x  = newPosition.getX();
        int y  = newPosition.getY();
        Piece piece = board[y][x];

        return ( piece != null &&  piece.getSide() != side);
    }
}
