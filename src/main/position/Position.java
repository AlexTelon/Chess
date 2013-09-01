package main.position;

/**
 * @author Alex Telon
 */
public class Position {
    private int x,y;
    private static int WIDTH = 8;
    private static int HEIGHT = 8;

    public Position(Position position) {
        setX(position.getX());
        setY(position.getY());
    }

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public boolean setX(int x) {
        if (x >= 0 && x <= WIDTH) {
            this.x = x;
            return true;
        }
        return false;
    }

    public int getY() {
        return y;
    }

    public boolean setY(int y) {
        if (y >= 0 && y <= HEIGHT) {
            this.y = y;
            return true;
        }
        return false;
    }
}
