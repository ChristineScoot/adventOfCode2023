package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Element implements Comparable<Element> {
    public static final int NORTH = 0;
    public static final int EAST = 1;
    public static final int SOUTH = 2;
    public static final int WEST = 3;
    private final Map<Integer, Coord> directionCoords = new HashMap<>();

    private final Node node;
    private final int heatLoss;

    public Element(Node node, int heatLoss) {
        this.node = node;
        this.heatLoss = heatLoss;
        directionCoords.put(EAST, new Coord(1, 0));
        directionCoords.put(WEST, new Coord(-1, 0));
        directionCoords.put(NORTH, new Coord(0, -1));
        directionCoords.put(SOUTH, new Coord(0, 1));
    }

    public List<Element> getNeighbours(int[][] grid) {
        List<Element> neighbours = new ArrayList<>();

        Element left = getNextElement(Math.floorMod(node.direction() - 1, 4), grid, 1);
        if (left != null) {
            neighbours.add(left);
        }

        Element right = getNextElement((node.direction() + 1) % 4, grid, 1);
        if (right != null) {
            neighbours.add(right);
        }
        if (node.blocks() < 3) {
            Element straight = getNextElement(node.direction(), grid, node.blocks() + 1);
            if (straight != null) {
                neighbours.add(straight);
            }
        }
        return neighbours;
    }

    public List<Element> getNeighbours2(int[][] grid) {
        List<Element> neighbours = new ArrayList<>();

        if (node.blocks() >= 4) {
            Element left = getNextElement(Math.floorMod(node.direction() - 1, 4), grid, 1);
            if (left != null) {
                neighbours.add(left);
            }

            Element right = getNextElement((node.direction() + 1) % 4, grid, 1);
            if (right != null) {
                neighbours.add(right);
            }
        }
        if (node.blocks() < 10) {
            Element straight = getNextElement(node.direction(), grid, node.blocks() + 1);
            if (straight != null) {
                neighbours.add(straight);
            }
        }
        return neighbours;
    }

    private Element getNextElement(int direction, int[][] grid, int blocks) {
        Coord nextCoords = new Coord(node.x(), node.y()).add(directionCoords.get(direction));
        int x = nextCoords.getX();
        int y = nextCoords.getY();
        if (x >= 0 && x < grid[0].length && y >= 0 && y < grid.length) {
            Node nextNode = new Node(x, y, blocks, direction);
            return new Element(nextNode, heatLoss + grid[y][x]);
        }
        return null;
    }

    @Override
    public int compareTo(Element o) {
        if (heatLoss != o.getHeatLoss()) {
            return Integer.compare(heatLoss, o.getHeatLoss());
        } else if (node.direction() == o.getNode().direction() && node.blocks() != o.getNode().blocks()) {
            return Integer.compare(node.blocks(), o.getNode().blocks());
        } else if (node.y() != o.getNode().y()) {
            return Integer.compare(node.y(), o.getNode().y());
        } else {
            return Integer.compare(node.x(), o.getNode().x());
        }
    }

    public Node getNode() {
        return node;
    }

    public int getHeatLoss() {
        return heatLoss;
    }
}
