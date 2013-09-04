package main.graphics;

import main.board.Board;
import main.globals.Globals;
import main.pieces.Piece;
import main.position.Position;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: alete471
 * Date: 2012-09-27
 * Time: 18:09
 * The main component of GameFrame, this is what paints the game itself.
 */
public class GraphicalViewer extends JComponent{
    private Board board;
    private GameFrame gameFrame;

    public GraphicalViewer(Board board, GameFrame gameFrame) {
        this.board = board;
        this.gameFrame = gameFrame;
    }

    public Dimension getPreferredSize() {
        return new Dimension(PreferredWidth(),PreferredHeight());
    }

    private int PreferredHeight() {
        return Globals.getPieceSize()*8;
    }
    private int PreferredWidth() {
        return Globals.getPieceSize()*8;
    }

    /**
     * Paints the whole board
     * @param g
     */
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Piece piece;
        Position position;
        g2.setColor(Color.ORANGE);
        g2.fill(new Rectangle(0,0,getWidth(),getHeight()));

        // Paint the pieces
        for (int y = 0; y < 8; y++ ) {
            for (int x = 0; x < 8; x++) {
                position = new Position(x, y);
                g2.setColor(position.isWhitePosition() ? Color.white: Color.ORANGE);
                g2.fill(new Rectangle(position.getX()*Globals.getPieceSize(),
                        position.getY()*Globals.getPieceSize(),
                        Globals.getPieceSize(), Globals.getPieceSize()));

                piece = board.getPiece(new Position(x, y));
                if (piece != null) {
                    g2.drawImage(piece.getImg() ,null, piece.getPosition().getX()*Globals.getPieceSize(),
                            piece.getPosition().getY()*Globals.getPieceSize());
                }
            }
        }



        // paint the highlighted piece
        piece = gameFrame.getHighligtedPiece();
        if (piece != null) {
            g2.setColor(Color.RED);
            g2.drawRect(piece.getPosition().getX()*Globals.getPieceSize(),
                    piece.getPosition().getY()*Globals.getPieceSize(),
                    Globals.getPieceSize(), Globals.getPieceSize());

            // paint the getPossiblePositions
            if (gameFrame.isShowPossiblePositions()) {
                for (Position currentPosition : gameFrame.getPossiblePositions()) {
                    if (currentPosition != null) {
                        g2.setColor(Color.BLUE);
                        g2.fill(new Rectangle(currentPosition.getX()*Globals.getPieceSize(),
                                currentPosition.getY()*Globals.getPieceSize(),
                                Globals.getPieceSize(), Globals.getPieceSize()));
                    }
                }
            }
        }
    }
}


