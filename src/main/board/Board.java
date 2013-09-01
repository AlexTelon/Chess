package main.board;


import main.globals.Globals;
import main.globals.Globals.Side;
import main.pieces.Piece;
import main.pieces.type.Pawn;
import main.position.Position;

import java.awt.*;
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
          board[6][i] = new Pawn(this, Side.White, i, 6);
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

    /**
     * Returns what is on the position. null or Piece
     * @param position
     * @return null or Piece
     */
    public Piece getPiece(Position position) {
        return board[position.getY()][position.getX()];
    }

    public Position getPosOnGrid(Position pos) {
        int xPixel = pos.getX();
        int yPixel = pos.getY();
        int xtmp = Globals.getPieceSize();
        int ytmp = Globals.getPieceSize();
        int xGrid = 0;
        int yGrid = 0;

        while (xPixel >= xtmp) {
            xtmp += Globals.getPieceSize();
            xGrid++;
        }
        while (yPixel >= ytmp) {
            ytmp += Globals.getPieceSize();
            yGrid++;
        }

        return new Position(xGrid,yGrid);
    }
}
