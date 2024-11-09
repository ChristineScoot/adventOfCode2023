package Solutions;

import Model.Coord;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day23 {
    private static final Map<Integer, Coord> directionCoords = new HashMap<>();
    private static final Map<Character, Coord> slopes = new HashMap<>();
    private static final Set<Coord> seen = new HashSet<>();
    private static final Map<Coord, Map<Coord, Integer>> graph = new HashMap<>(); //each neghbour + how far it is
    private static Coord targetPoint;

    private static void initialise() {
        directionCoords.put(0, new Coord(1, 0));
        directionCoords.put(1, new Coord(-1, 0));
        directionCoords.put(2, new Coord(0, -1));
        directionCoords.put(3, new Coord(0, 1));

        slopes.put('>', new Coord(1, 0));
        slopes.put('<', new Coord(-1, 0));
        slopes.put('^', new Coord(0, -1));
        slopes.put('v', new Coord(0, 1));
    }

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 23: Part 1~~~~~~~~~~~~");
        initialise();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] grid = new char[fileContentString.length][];
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].toCharArray();
        }
//        find starting point - assuming it's in a top row
        Coord startingPoint = new Coord(fileContentString[0].indexOf('.'), 0);
//        find target point - assuming it's in a bottom row
        int lastRowIndex = grid.length - 1;
        targetPoint = new Coord(fileContentString[lastRowIndex].indexOf('.'), lastRowIndex);

        List<Coord> points = new LinkedList<>();
        points.add(startingPoint);
        points.add(targetPoint);

        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                Coord current = new Coord(x, y);
                char currentChar = grid[y][x];
                if (currentChar == '#')
                    continue;
                int neighbours = 0;
                for (Map.Entry<Integer, Coord> directionCoord : directionCoords.entrySet()) {
                    Coord nextCoord = current.add(directionCoord.getValue());
                    if (!nextCoord.outOfBounds(grid[0].length, grid.length) && grid[nextCoord.getY()][nextCoord.getX()] != '#')
                        neighbours++;
                }
                if (neighbours >= 3)
                    points.add(current);
            }
        }

        for (int i = 0; i < points.size(); i++) {
            Coord pointOfInterest = points.get(i);
            Map<Coord, Integer> queue = new LinkedHashMap<>();
            List<Coord> visited = new LinkedList<>();
            queue.put(pointOfInterest, 0);
            visited.add(pointOfInterest);
            while (!queue.isEmpty()) {
                Coord currentPoint = queue.entrySet().iterator().next().getKey();
                Integer n = queue.remove(currentPoint);
                if (n != 0 && points.contains(currentPoint)) {
                    Map<Coord, Integer> currentMap;
                    if (graph.containsKey(pointOfInterest)) {
                        currentMap = graph.get(pointOfInterest);
                    } else
                        currentMap = new HashMap<>();
                    currentMap.put(currentPoint, n);
                    graph.put(pointOfInterest, currentMap);
                    continue;
                }

                char currentChar = grid[currentPoint.getY()][currentPoint.getX()];
                Coord nextCoord;
                if (slopes.containsKey(currentChar)) {
                    nextCoord = currentPoint.add(slopes.get(currentChar));
                    if (!nextCoord.outOfBounds(grid[0].length, grid.length) && !visited.contains(nextCoord)) {
                        queue.put(nextCoord, n + 1);
                        visited.add(nextCoord);
                    }
                } else { // '.'
                    for (Map.Entry<Integer, Coord> directionCoord : directionCoords.entrySet()) {
                        nextCoord = currentPoint.add(directionCoord.getValue());
                        if (!nextCoord.outOfBounds(grid[0].length, grid.length) && grid[nextCoord.getY()][nextCoord.getX()] != '#' && !visited.contains(nextCoord)) {
                            queue.put(nextCoord, n + 1);
                            visited.add(nextCoord);
                        }
                    }
                }

            }
        }
        return dfs(startingPoint);
    }

    public static int dfs(Coord point) {
        if (point.equals(targetPoint))
            return 0;
        double max = Double.NEGATIVE_INFINITY;
        seen.add(point);
        for (Map.Entry<Coord, Integer> next : graph.get(point).entrySet()) {
            if (!seen.contains(next.getKey()))
                max = Math.max(max, dfs(next.getKey()) + graph.get(point).get(next.getKey()));
        }
        seen.remove(point);
        return (int) max;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 23: Part 2~~~~~~~~~~~~");
        initialise();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] grid = new char[fileContentString.length][];
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].toCharArray();
        }
//        find starting point - assuming it's in a top row
        Coord startingPoint = new Coord(fileContentString[0].indexOf('.'), 0);
//        find target point - assuming it's in a bottom row
        int lastRowIndex = grid.length - 1;
        targetPoint = new Coord(fileContentString[lastRowIndex].indexOf('.'), lastRowIndex);

        List<Coord> points = new LinkedList<>();
        points.add(startingPoint);
        points.add(targetPoint);

        for (int x = 0; x < grid[0].length; x++) {
            for (int y = 0; y < grid.length; y++) {
                Coord current = new Coord(x, y);
                char currentChar = grid[y][x];
                if (currentChar == '#')
                    continue;
                int neighbours = 0;
                for (Map.Entry<Integer, Coord> directionCoord : directionCoords.entrySet()) {
                    Coord nextCoord = current.add(directionCoord.getValue());
                    if (!nextCoord.outOfBounds(grid[0].length, grid.length) && grid[nextCoord.getY()][nextCoord.getX()] != '#')
                        neighbours++;
                }
                if (neighbours >= 3)
                    points.add(current);
            }
        }
        for (int i = 0; i < points.size(); i++) {
            Coord pointOfInterest = points.get(i);
            Map<Coord, Integer> queue = new LinkedHashMap<>();
            List<Coord> visited = new LinkedList<>();
            queue.put(pointOfInterest, 0);
            visited.add(pointOfInterest);
            while (!queue.isEmpty()) {
                Coord currentPoint = queue.entrySet().iterator().next().getKey();
                Integer n = queue.remove(currentPoint);
                if (n != 0 && points.contains(currentPoint)) {
                    Map<Coord, Integer> currentMap;
                    if (graph.containsKey(pointOfInterest)) {
                        currentMap = graph.get(pointOfInterest);
                    } else
                        currentMap = new HashMap<>();
                    currentMap.put(currentPoint, n);
                    graph.put(pointOfInterest, currentMap);
                    continue;
                }

                Coord nextCoord;

                for (Map.Entry<Integer, Coord> directionCoord : directionCoords.entrySet()) {
                    nextCoord = currentPoint.add(directionCoord.getValue());
                    if (!nextCoord.outOfBounds(grid[0].length, grid.length) && grid[nextCoord.getY()][nextCoord.getX()] != '#' && !visited.contains(nextCoord)) {
                        queue.put(nextCoord, n + 1);
                        visited.add(nextCoord);
                    }
                }
            }
        }
        return dfs(startingPoint);
    }
}
