package Solutions;

import Model.Coord;
import Model.CoordSteps;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static Model.Element.*;

public class Day21 {
    private static final Map<Integer, Coord> directionCoords = new HashMap<>();
    private static Set<Coord> visited = new HashSet<>();
    private static Queue<CoordSteps> queue = new LinkedList<>();

    private static void initialise() {
        visited = new HashSet<>();
        queue = new LinkedList<>();
        directionCoords.put(EAST, new Coord(1, 0));
        directionCoords.put(WEST, new Coord(-1, 0));
        directionCoords.put(NORTH, new Coord(0, -1));
        directionCoords.put(SOUTH, new Coord(0, 1));
    }

    public static long part1(String filePath, int steps) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 21: Part 1~~~~~~~~~~~~");
        initialise();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] map = new char[fileContentString.length][];
        for (int i = 0; i < fileContentString.length; i++) {
            map[i] = fileContentString[i].toCharArray();
        }
//        HelperMethods.printArray(map);
        Coord coordS = findS(map);
        return fill(map, coordS, steps);
    }

    private static Coord findS(char[][] map) {
        Coord coordS = new Coord(map.length / 2, map.length / 2);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] == 'S') {
                    coordS = new Coord(j, i);
                }
            }
        }
        return coordS;
    }

    public static long part2_neat(String filePath, int steps) throws Exception {
        System.out.println("~~~~~~~~~~~~Day 21: Part 2~~~~~~~~~~~~");
        initialise();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] map = new char[fileContentString.length][];
        for (int i = 0; i < fileContentString.length; i++) {
            map[i] = fileContentString[i].toCharArray();
        }

        if (map.length != map[0].length)
            throw new Exception("Map isn't a square"); //we are assuming that it is (and we're starting from the middle)
        int size = map.length;
        int gridWidth = Math.floorDiv(steps, size) - 1;
        double odd = Math.pow((Math.floorDiv(gridWidth, 2) * 2 + 1), 2);
        double even = Math.pow(Math.floorDiv(gridWidth + 1, 2) * 2, 2);

        Coord coordS = findS(map);
        long oddPoints = fill(map, coordS, steps * 2 + 1);
        long evenPoints = fill(map, coordS, steps * 2);

        long cornerTop = fill(map, new Coord(coordS.getX(), size - 1), size - 1);
        long cornerRight = fill(map, new Coord(0, coordS.getY()), size - 1);
        long cornerBottom = fill(map, new Coord(coordS.getX(), 0), size - 1);
        long cornerLeft = fill(map, new Coord(size - 1, coordS.getY()), size - 1);

        long smallTopRight = fill(map, new Coord(0, size - 1), Math.floorDiv(size, 2) - 1);
        long smallBottomRight = fill(map, new Coord(0, 0), Math.floorDiv(size, 2) - 1);
        long smallTopLeft = fill(map, new Coord(size - 1, size - 1), Math.floorDiv(size, 2) - 1);
        long smallBottomLeft = fill(map, new Coord(size - 1, 0), Math.floorDiv(size, 2) - 1);

        long largeTopRight = fill(map, new Coord(0, size - 1), Math.floorDiv(size * 3, 2) - 1);
        long largeBottomRight = fill(map, new Coord(0, 0), Math.floorDiv(size * 3, 2) - 1);
        long largeTopLeft = fill(map, new Coord(size - 1, size - 1), Math.floorDiv(size * 3, 2) - 1);
        long largeBottomLeft = fill(map, new Coord(size - 1, 0), Math.floorDiv(size * 3, 2) - 1);

        return (long) (odd * oddPoints + even * evenPoints +
                cornerRight + cornerLeft + cornerTop + cornerBottom +
                (gridWidth + 1) * (smallTopRight + smallTopLeft + smallBottomRight + smallBottomLeft) +
                gridWidth * (largeTopRight + largeTopLeft + largeBottomRight + largeBottomLeft)
        );
    }

    public static long fill(char[][] map, Coord coordS, int steps) {
        initialise();
        visited.add(coordS);
        queue.add(new CoordSteps(coordS, steps));
        Set<Coord> answer = new HashSet<>();
        while (!queue.isEmpty()) {
            CoordSteps current = queue.remove();
            Coord currentCoord = current.coord();
            int stepsRemaining = current.stepsRemaining();
            if (stepsRemaining % 2 == 0)
                answer.add(currentCoord);
            if (stepsRemaining == 0)
                continue;

            //find neighbours for current
            for (Map.Entry<Integer, Coord> coord : directionCoords.entrySet()) {
                Coord neighbour = currentCoord.add(coord.getValue());
                if (neighbour.outOfBounds(map[0].length, map.length))
                    continue;
                if (map[neighbour.getX()][neighbour.getY()] == '#')
                    continue;
                if (visited.contains(neighbour)) {
                    continue;
                }
                visited.add(neighbour);
                queue.add(new CoordSteps(neighbour, stepsRemaining - 1));
            }
        }
        return answer.size();
    }
}
