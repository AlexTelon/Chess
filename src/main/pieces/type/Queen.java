package main.pieces.type;

import main.board.Board;
import main.globals.Globals;
import main.globals.Globals.Side;
import main.pieces.IPiece;
import main.pieces.Piece;
import main.position.Position;
import main.position.Vector;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Alex Telon
 */
public class Queen extends Piece implements IPiece {
    private BufferedImage img = null;

    public Queen(Board board, Side side, int x, int y) {
        super(board, side, x, y);

        try {
            if (super.getSide() == Globals.Side.White) {
                img = ImageIO.read(new File("media/queen.png"));
            } else {
                img = ImageIO.read(new File("media/bqueen.png"));
            }
        } catch (IOException e) {
            System.out.println("ERROR IN READING PICTURE");
        }
    }

    @Override
    public BufferedImage getImg() {
        return img;
    }

    @Override
    public double getValue() {
        return 9;
    }


    @Override
    public ArrayList<Position> getPossiblePositions() {
        ArrayList<Position> possiblePositions = new ArrayList<Position>();

        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,0)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,0)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(0,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(0,-1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(-1,-1)));
        possiblePositions.addAll(getPossiblePositionsInDirection(new Vector(1,-1)));

        return  possiblePositions;
    }


}
