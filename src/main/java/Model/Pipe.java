package Model;

public class Pipe {
    private final Coord c1;
    private final Coord c2;

    public Pipe(Coord c1, Coord c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    public Coord getC1() {
        return c1;
    }

    public Coord getC2() {
        return c2;
    }
}
