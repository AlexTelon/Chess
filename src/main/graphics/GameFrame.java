package main.graphics;

import main.board.Board;
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
    private static Board board;
    private GraphicalViewer graphicalViewer;
    private Piece highlightedPiece = null;
    private ArrayList<Position> possiblePositions = new ArrayList<Position>();
    private boolean showPossiblePositions = false;
    private ArrayList<Position> invalidPossiblePositions = new ArrayList<Position>();


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

        if (highlightedPiece != null && highlightedPiece != piece) {
            highlightedPiece.tryMove(mouseGridPosition);
            repaint();
            highlightedPiece = null;
        }



/*
        for (Enemy currentEnemy : board.getAllEnemiesInCurrentWave()) {
            if( currentEnemy.isWithinObject(new main.position.Point(e.getPoint())) && currentEnemy.isAlive()) {
                graphicalInformationViewer.currentObject(currentEnemy);
                graphicalViewer.higlight(currentEnemy);
                return;
            }
        }

        // this part handels where to build towers
        for (Tower currentTower : board.getAllTowers()) {
            if (currentTower.getPosition().getX() == mouseGridPosition.getX() &&
                    currentTower.getPosition().getY() == mouseGridPosition.getY() ) {
                graphicalInformationViewer.currentObject(currentTower);
                graphicalViewer.higlight(currentTower);
                lastClickedPosition = mouseGridPosition;
                return;
            }
        }
*/
        repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Position mouseGridPosition = board.getPosOnGridFromPixelPos(new Position(e.getPoint().x, e.getPoint().y));
        Piece piece = board.getPiece(mouseGridPosition);
        if (piece != null) {
            highlightedPiece = piece;
            showPossiblePositions = true;
            possiblePositions = piece.returnPossiblePositions();
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        showPossiblePositions = false;
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        return;
    }

    @Override
    public void mouseExited(MouseEvent e) {
        return;
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

    public ArrayList<Position> getInvalidPossiblePositions() {
        invalidPossiblePositions.clear();
        for (Position currentPosition : possiblePositions) {
            if (!board.isEmpty(currentPosition)) {
                if (!board.isSameSidePiece(currentPosition, board.getPiece(currentPosition).getSide())) {
                    invalidPossiblePositions.add(currentPosition);
                }
            }
        }
        return invalidPossiblePositions;
    }
}
