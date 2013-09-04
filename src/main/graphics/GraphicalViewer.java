package main.graphics;

import main.board.Board;
import main.globals.Globals;
import main.pieces.Piece;
import main.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

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
        BufferedImage originalImage;
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
                    originalImage = piece.getImg();

                    int width = Globals.getPieceSize();
                    int height = Globals.getPieceSize();

                    int w = originalImage.getWidth();
                    int h = originalImage.getHeight();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

                   while (!g2.drawImage(originalImage, piece.getPosition().getX()*Globals.getPieceSize(),
                           piece.getPosition().getY()*Globals.getPieceSize(), piece.getPosition().getX()*Globals.getPieceSize()+ width,
                           piece.getPosition().getY()*Globals.getPieceSize()+ height, 0, 0, w, h, null))
                    {
                       System.out.println("image is drawing...");
                   }
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

        if (board.getWinner() != null) {
            winnerGraphics(g2);
            //GameFrame.resetGame();
        }

    }

    private void winnerGraphics(Graphics2D g2) {
        g2.setColor(Color.ORANGE);
        g2.fill(new Rectangle(0,0,getWidth(),getHeight()));
        g2.setColor(Color.black);
        if (board.getWinner() == Globals.Side.White) {
            g2.drawString("White side won!",100, 100);
        } else {
            g2.drawString("Black side won!",100, 100);
        }

    }

}


