package main.board;


import main.globals.Globals;
import main.globals.Globals.Side;
import main.pieces.Piece;
import main.pieces.type.*;
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
        add(new Tower(this, Side.White, 0, 7));
        add(new Knight(this, Side.White, 1, 7));
        add(new Bishop(this, Side.White, 2, 7));
        add(new Queen(this, Side.White, 3, 7));
        add(new King(this, Side.White, 4, 7));
        add(new Bishop(this, Side.White, 5, 7));
        add(new Knight(this, Side.White, 6, 7));
        add(new Tower(this, Side.White, 7, 7));
        for (int i = 0; i < 8; i++) {
            add(new Pawn(this, Side.White, i, 6));
        }

        // black
        add(new Tower(this, Side.Black, 0, 0));
        add(new Knight(this, Side.Black, 1, 0));
        add(new Bishop(this, Side.Black, 2, 0));
        add(new King(this, Side.Black, 3, 0));
        add(new Queen(this, Side.Black, 4, 0));
        add(new Bishop(this, Side.Black, 5, 0));
        add(new Knight(this, Side.Black, 6, 0));
        add(new Tower(this, Side.Black, 7, 0));
        for (int i = 0; i < 8; i++) {
            add(new Pawn(this, Side.Black, i, 1));
        }

    }

    /**
     * Creates a custom starting point. Used in testing.
     * @param pieces all pieces that are to be placed on the board
     */
    public Board(ArrayList<Piece> pieces) {
        this.pieces = pieces;
        int x,y;
        for (Piece currentPiece : pieces) {
            x = currentPiece.getPosition().getX();
            y = currentPiece.getPosition().getY();
            board[y][x] = currentPiece;
        }
    }

    /**
     * adds a piece to the board. Verification should be done by the caller!
     * Used for testing purposes.
     * @param piece piece to be added
     */
    public void add(Piece piece) {
        this.pieces.add(piece);

        int x = piece.getPosition().getX();
        int y = piece.getPosition().getY();
        board[y][x] = piece;
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
     * @return true if on the same side, false if null
     */
    public boolean isSameSidePiece(Position newPosition, Side side) {
        int x  = newPosition.getX();
        int y  = newPosition.getY();
        Piece piece = board[y][x];

        return ( piece != null &&  piece.getSide() == side);
    }

    /**
     * Returns what is on the position. null or Piece
     * @param position where to seek a Piece
     * @return null or Piece
     */
    public Piece getPiece(Position position) {
        return board[position.getY()][position.getX()];
    }

    /**
     * Turns a pixel position into a board position.
     * @param pos in pixels
     * @return the position on the board
     */
    public Position getPosOnGridFromPixelPos(Position pos) {
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

    /**
     * Moves a piece and removes the piece it lands on.
     * @param piece to be moved
     * @param newPosition where the piece is to be moved
     */
    public void movePieceTo(Piece piece, Position newPosition) {
        int x  = newPosition.getX();
        int y  = newPosition.getY();
        if (board[y][x] != null) {
            board[y][x].remove();
        }
        board[y][x] = piece;
    }

    /**
     * Empties a position
     * @param position position to be emptied
     */
    public void empty(Position position) {
        int x  = position.getX();
        int y  = position.getY();
        board[y][x] = null;
    }

    /**
     *
     * @param position position to be examined
     * @return true if empty)
     */
    public boolean isEmpty(Position position) {
        int x  = position.getX();
        int y  = position.getY();
        return (board[y][x] == null);
    }


    /**
     * Tries to move a piece. If a friendly piece is on the new position already or
     * if new position is invalid then return false. If new position is occupied by
     * enemy piece then remove it and move to that position.
     * @param piece piece to be moved
     * @param newPosition new position for the piece
     * @return true if successful, false otherwise
     */
    public boolean tryMove(Piece piece, Position newPosition) {
        if (newPosition.isValid()) {
            if (isSameSidePiece(newPosition, piece.getSide()))
                return false;
        }

        Position originalPosition = new Position(piece.getPosition());

        // Try to give a new position to the piece
        if (piece.getPosition().setY(newPosition.getY()) &&
                piece.getPosition().setX(newPosition.getX()) ) {

            // old piece is removed
            if (board[newPosition.getY()][newPosition.getX()] != null) {
                board[newPosition.getY()][newPosition.getX()].remove();
            }

            empty(originalPosition);
            movePieceTo(piece, newPosition);
            return true;
        }
        return false;
    }

    /**
     * Clears the entire board
     */
    public void clear() {
        pieces.clear();
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                board[y][x] = null;
            }
        }
    }
}
