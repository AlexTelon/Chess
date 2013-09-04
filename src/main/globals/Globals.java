package main.globals;

/**
 * @author Alex Telon
 */
public class Globals {

    // size in pixels of one side.
    // All pieces are squares
    private static int pieceSize = 40;

    public enum Side {
        White, Black;
    }

    public static int getPieceSize() {
        return pieceSize;
    }
}
