package Solutions;

import Model.Coord;
import Model.CoordInput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Day16 {
    private static Integer sum;
    private static char[][] grid;
    private static final Map<Character, Coord> directionCoords = new HashMap<>();
    private static Map<Coord, Integer> energized = new HashMap<>();
    private static final Queue<CoordInput> coordsToCheck = new LinkedList<>();
    private static Map<CoordInput, Boolean> visited = new HashMap<>();

    public static void initialise() {
        sum = 0;
        directionCoords.put('r', new Coord(1, 0));
        directionCoords.put('l', new Coord(-1, 0));
        directionCoords.put('u', new Coord(0, -1));
        directionCoords.put('d', new Coord(0, 1));
    }

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 16: Part 1~~~~~~~~~~~");
        initialise();

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        grid = new char[fileContentString.length][];
        int counter = 0;
        for (String s : fileContentString) {
            grid[counter] = s.toCharArray();
            counter++;
        }

        coordsToCheck.add(new CoordInput(new Coord(0, 0), 'r'));
        while (!coordsToCheck.isEmpty()) {
            CoordInput coordInput = coordsToCheck.poll();
            if (visited.containsKey(coordInput))
                continue;
            visited.put(coordInput, true);
            moveBeam(coordInput.currentPosition(), coordInput.direction());
        }
        return energized.size();
    }

    public static void moveBeam(Coord currentPosition, Character direction) {
        if (currentPosition.outOfBounds(grid[0].length, grid.length))
            return;
        energized.merge(currentPosition, 1, Integer::sum);
        char currentChar = grid[currentPosition.getY()][currentPosition.getX()];
        if (currentChar == '.'
                || (currentChar == '-' && (direction == 'r' || direction == 'l'))
                || (currentChar == '|' && (direction == 'u' || direction == 'd'))) {
            Coord nextPosition = currentPosition.add(directionCoords.get(direction));
            coordsToCheck.add(new CoordInput(nextPosition, direction));
        } else if (currentChar == '-') {
            coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('r')), 'r'));
            coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('l')), 'l'));
        } else if (currentChar == '|') {
            coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('u')), 'u'));
            coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('d')), 'd'));
        } else if (currentChar == '/') {
            switch (direction) {
                case 'r' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('u')), 'u'));
                case 'u' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('r')), 'r'));
                case 'l' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('d')), 'd'));
                case 'd' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('l')), 'l'));
            }
        } else { // '\'
            switch (direction) {
                case 'r' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('d')), 'd'));
                case 'u' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('l')), 'l'));
                case 'l' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('u')), 'u'));
                case 'd' -> coordsToCheck.add(new CoordInput(currentPosition.add(directionCoords.get('r')), 'r'));
            }
        }
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 16: Part 2~~~~~~~~~~~~");
        initialise();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        grid = new char[fileContentString.length][];
        int counter = 0;
        for (String s : fileContentString) {
            grid[counter] = s.toCharArray();
            counter++;
        }

        // top & bottom edge
        Queue<CoordInput> initialCoordsToCheck = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            initialCoordsToCheck.add(new CoordInput(new Coord(i, 0), 'd'));
            initialCoordsToCheck.add(new CoordInput(new Coord(i, grid[i].length - 1), 'u'));
        }
        // left & right edge
        for (int i = 0; i < grid[0].length; i++) {
            initialCoordsToCheck.add(new CoordInput(new Coord(0, i), 'r'));
            initialCoordsToCheck.add(new CoordInput(new Coord(grid.length - 1, i), 'l'));
        }

        while (!initialCoordsToCheck.isEmpty()) {
            energized = new HashMap<>();
            visited = new HashMap<>();
            CoordInput initialCoord = initialCoordsToCheck.poll();
            coordsToCheck.add(initialCoord);
            while (!coordsToCheck.isEmpty()) {
                CoordInput coordInput = coordsToCheck.poll();
                if (visited.containsKey(coordInput))
                    continue;
                visited.put(coordInput, true);
                moveBeam(coordInput.currentPosition(), coordInput.direction());
            }
            sum = Math.max(sum, energized.size());
        }
        return sum;
    }
}
