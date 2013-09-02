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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        if (y != position.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
