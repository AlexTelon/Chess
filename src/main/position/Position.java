package main.position;

/**
 * @author Alex Telon
 */
public class Position {
    private int x,y;
    private static int WIDTH = 8;
    private static int HEIGHT = 8;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        if (x >= 0 && x <= WIDTH)
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        if (y >= 0 && y <= HEIGHT)
        this.y = y;
    }
}
