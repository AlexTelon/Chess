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
        if (x >= 0 && x < WIDTH) {
            this.x = x;
            return true;
        }
        return false;
    }

    public int getY() {
        return y;
    }

    public boolean setY(int y) {
        if (y >= 0 && y < HEIGHT) {
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

    /**
     * Checks if the point is outside of the board bounds
     * @return true if valid
     */
    public boolean isValid() {
        if (x < 0 || x > 7) return false;
        if (y < 0 || y > 7) return false;
        return true;
    }

    /**
     * Sees if a vector can be applied to the Position
     * @param vector to be applied
     * @return true if move can  be made.
     */
    public boolean canMove(Vector vector) {
        int x = this.x + vector.getX();
        int y = this.y + vector.getY();

        if (x < 0 || x > 7) return false;
        if (y < 0 || y > 7) return false;
        return true;
    }

    /**
     * Add a vector to the position. If new possible position is invalid
     * the Position is not changed at all.
     * Example: vector (1,1) adds 1 to both x and y in the Position.
     * @param vector to be added to the current position.
     * @return
     */
    public boolean add(Vector vector) {
        if (canMove(vector)) {
            setY(this.y+vector.getY());
            setX(this.x+vector.getX());
            return true;
        }
        return false;
    }

    /**
     * @return if position represents a white square
     */
    public boolean isWhitePosition() {
        return ((getX()+getY()) % 2 == 0);
    }


}
