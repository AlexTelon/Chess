package main.graphics;

import main.board.Board;
import main.globals.Globals.Side;
import main.pieces.Piece;
import main.position.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**

 * Main class for GUI, handels what is shown and also placing and selling towers.
 */
public class GameFrame extends JFrame implements MouseListener {
    private final JMenu menu = new JMenu("Menu");
    private Board board;
    private GraphicalViewer graphicalViewer;
    private Piece highlightedPiece = null;
    private ArrayList<Position> possiblePositions = new ArrayList<Position>();
    private boolean showPossiblePositions = false;


    public GameFrame(Board board) throws HeadlessException {
        this.board = board;
        this.graphicalViewer = new GraphicalViewer(board, this);
        this.graphicalViewer.addMouseListener(this);

        this.add(graphicalViewer, BorderLayout.CENTER);

        final JMenuBar bar = new JMenuBar();
        bar.add(menu);

        this.setJMenuBar(bar);

        this.setLayout(new FlowLayout());
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new GameFrame(new Board());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Position mouseGridPosition = board.getPosOnGridFromPixelPos(new Position(e.getPoint().x, e.getPoint().y));
        Piece piece = board.getPiece(mouseGridPosition);
        Side turn = board.getTurn();

        if (highlightedPiece != null && highlightedPiece != piece) {
            highlightedPiece.tryMove(mouseGridPosition);
            highlightedPiece = null;
        } else {
            if (piece != null && piece.getSide() == turn) {
                highlightedPiece = piece;
                showPossiblePositions = true;
                possiblePositions = piece.getPossiblePositions();
            }
        }
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public Piece getHighligtedPiece() {
        return highlightedPiece;
    }

    public ArrayList<Position> getPossiblePositions() {
        return possiblePositions;
    }

    public boolean isShowPossiblePositions() {
        return showPossiblePositions;
    }

}
