package main.position;

/**
 * @author Alex Telon
 * The vector class is a lot like the Position class, but no checks. Used for directions.
 */
public class Vector {
    private int x,y;

    public Vector(Vector vector) {
        setX(vector.getX());
        setY(vector.getY());
    }

    /**
     * Convert a point to a vector.
     * @param position
     */
    public Vector(Position position) {
        setX(position.getX());
        setY(position.getY());
    }

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
            this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
            this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector position = (Vector) o;

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


    public void addY(int y) {
       setY(this.y+y);
    }

    public void addX(int x) {
        setX(this.x + x);
    }


}
