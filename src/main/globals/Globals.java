package main.globals;

import main.graphics.GameFrame;

/**
 * @author Alex Telon
 */
public class Globals {
    private GameFrame gameFrame;

    // size in pixels of one side.
    // All pieces are squares
    private static int pieceSize = 40;

    public Globals(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    public void updateGlobals() {
        pieceSize = gameFrame.getContentSide() / 8;
    }

    public enum Side {
        White, Black;
    }

    public static int getPieceSize() {
        return pieceSize;
    }
}
