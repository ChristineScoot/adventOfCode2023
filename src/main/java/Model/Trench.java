package Model;

public class Trench {
    String colour;
    Coord coord;

    public Trench(Coord coord, String colour) {
        this.colour = colour;
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

}
