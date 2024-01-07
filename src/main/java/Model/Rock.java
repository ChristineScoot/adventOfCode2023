package Model;

import java.util.Objects;

public class Rock implements Comparable<Rock> {
    int x;
    int y;

    public Rock(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int compareTo(Rock o) {
        int byX = Integer.compare(x, o.x);
        if (byX == 0)
            return Integer.compare(y, o.y);
        return byX;
    }

    @Override
    public String toString() {
        return "Rock coords{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rock rock = (Rock) o;
        return x == rock.x && y == rock.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
