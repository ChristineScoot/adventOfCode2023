package Model;

import java.util.Objects;

public class Coord implements Comparable<Coord> {
    int x;
    int y;
    public String name;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
        name = "(" + x + ", " + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coord{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coord add(Coord coord) {
        int newX = this.x + coord.getX();
        int newY = this.y + coord.getY();
        return new Coord(newX, newY);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Coord newCoord = (Coord) obj;
        return x == newCoord.getX() && y == newCoord.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public boolean outOfBounds(int width, int height) {
        return !(x < height && x >= 0 && y < width && y >= 0);
    }

    @Override
    public int compareTo(Coord newCoord) {
        if (newCoord.getX() == x && newCoord.getY() == y)
            return 0;
        return -1;    }
}
