package Model;

public class Coord {
    int x;
    int y;
    public String name;

    public Coord(int x, int y) {
        this.x = x;
        this.y = y;
        name = "(" + x +", "+ y+")";
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
        Coord newCoord = (Coord) obj;
        return this.x==newCoord.getX() && this.y == newCoord.getY();
    }
}
