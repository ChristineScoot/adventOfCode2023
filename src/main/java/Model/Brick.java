package Model;

public class Brick {
    private final int x1;
    private final int y1;
    private int z1;
    private final int x2;
    private final int y2;
    private int z2;

    public Brick(int x1, int y1, int z1, int x2, int y2, int z2) {
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
    }

    public static int compareMinZ(Brick o1, Brick o2) {
        return o1.getMinZ() - o2.getMinZ();
    }

    public void setZ(int deltaZ) {
        this.z1 += deltaZ;
        this.z2 += deltaZ;
    }

    public int getMinZ() {
        return Math.min(z1, z2);
    }

    public int getMaxZ() {
        return Math.max(z1, z2);
    }

    @Override
    public String toString() {
        return x1 + "," + y1 + "," + z1 + "~" + x2 + "," + y2 + "," + z2;
    }

    public boolean intersects(Brick o) {
        int o1 = orientation(new Coordinate(x1, y1, z1), new Coordinate(x2, y2, z2), new Coordinate(o.x1, o.y1, o.z1));
        int o2 = orientation(new Coordinate(x1, y1, z1), new Coordinate(x2, y2, z2), new Coordinate(o.x2, o.y2, o.z2));
        int o3 = orientation(new Coordinate(o.x1, o.y1, o.z1), new Coordinate(o.x2, o.y2, o.z2), new Coordinate(x1, y1, z1));
        int o4 = orientation(new Coordinate(o.x1, o.y1, o.z1), new Coordinate(o.x2, o.y2, o.z2), new Coordinate(x2, y2, z2));

        if (o1 != o2 && o3 != o4)
            return true;

        if (o1 == 0 && onSegment(new Coordinate(x1, y1, z1), new Coordinate(o.x1, o.y1, o.z1), new Coordinate(x2, y2, z2)))
            return true;

        if (o2 == 0 && onSegment(new Coordinate(x1, y1, z1), new Coordinate(o.x2, o.y2, o.z2), new Coordinate(x2, y2, z2)))
            return true;

        if (o3 == 0 && onSegment(new Coordinate(o.x1, o.y1, o.z1), new Coordinate(x1, y1, z1), new Coordinate(o.x2, o.y2, o.z2)))
            return true;

        return o4 == 0 && onSegment(new Coordinate(o.x1, o.y1, o.z1), new Coordinate(x2, y2, z2), new Coordinate(o.x2, o.y2, o.z2));
    }

    private int orientation(Coordinate p, Coordinate q, Coordinate r) {
        int val = (q.y() - p.y()) * (r.x() - q.x()) -
                (q.x() - p.x()) * (r.y() - q.y());

        if (val == 0)
            return 0;

        return (val > 0) ? 1 : 2;
    }

    private boolean onSegment(Coordinate p, Coordinate q, Coordinate r) {
        return q.x() <= Math.max(p.x(), r.x()) && q.x() >= Math.min(p.x(), r.x()) &&
                q.y() <= Math.max(p.y(), r.y()) && q.y() >= Math.min(p.y(), r.y());
    }

    public record Coordinate(int x, int y, int z) {
    }
}
