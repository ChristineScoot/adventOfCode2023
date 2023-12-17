package Solutions;

import Model.Coord;
import Model.Pipe;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day10 {
    private static final Queue<Coord> queue = new LinkedList<>();
    private static final Map<String, Boolean> visited = new HashMap<>();
    private static int sum = 0;
    private static final Map<Character, Pipe> tiles = new HashMap<>();// Symbol/Pipe with coords eg <'|', (-1,0) lub (1,0)>
    private static final List<Coord> coords = new LinkedList<>();

    public static void initialise() {
        Coord east = new Coord(1, 0);
        Coord west = new Coord(-1, 0);
        Coord north = new Coord(0, -1);
        Coord south = new Coord(0, 1);
        tiles.put('|', new Pipe(north, south));
        tiles.put('-', new Pipe(east, west));
        tiles.put('L', new Pipe(north, east));
        tiles.put('J', new Pipe(north, west));
        tiles.put('7', new Pipe(south, west));
        tiles.put('F', new Pipe(south, east));
        coords.add(east);
        coords.add(south);
        coords.add(west);
        coords.add(north);
    }

    public static int part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 10: Part 1~~~~~~~~~~~");
        initialise();
        Coord coordS = new Coord(0, 0);
        boolean isSFound = false;
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] grid = new char[fileContentString.length][];
        int counter = 0;
        for (String s : fileContentString) {
            grid[counter] = s.toCharArray();
            for (int i = 0; i < grid[counter].length && !isSFound; i++) {
                if (grid[counter][i] == 'S') {
                    coordS = new Coord(i, counter);
                    isSFound = true;
                }
            }
            counter++;
        }
        Coord previous = coordS;
        Coord current = coordS;
        Coord next;
        char currentChar = '.';
        sum = 0;
        for (Coord coord : coords) {
            next = current;
            next = next.add(coord);
            try {
                currentChar = grid[next.getY()][next.getX()];
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
            if (tiles.containsKey(currentChar)) {
                if ((current.equals(next.add(tiles.get(currentChar).getC1())) || current.equals(next.add(tiles.get(currentChar).getC2())))) {//&&previous!=next) {
                    if (!next.equals(previous)) {
                        sum++;
                        previous = current;
                        current = next;
                        break;
                    }
                }
            }
        }
        do {
            next = current.add(tiles.get(currentChar).getC1());
            if (next.equals(previous))
                next = current.add(tiles.get(currentChar).getC2());
            currentChar = grid[next.getY()][next.getX()];
            sum++;
            previous = current;
            current = next;
            if (currentChar == 'S')
                break;
        } while (!next.equals(coordS));
        return ++sum / 2;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 10: Part 2~~~~~~~~~~~~");
        initialise();
        Coord coordS = new Coord(0, 0);
        boolean isSFound = false;
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] grid = new char[fileContentString.length][];
        int counter = 0;
        for (String s : fileContentString) {
            grid[counter] = s.toCharArray();
            for (int i = 0; i < grid[counter].length && !isSFound; i++) {
                if (grid[counter][i] == 'S') {
                    coordS = new Coord(i, counter);
                    isSFound = true;
                }
            }
            counter++;
        }
        char[][] newGrid = new char[grid.length][grid[0].length];
        for (char[] chars : newGrid) {
            Arrays.fill(chars, 'Q');
        }
        newGrid[coordS.getY()][coordS.getX()] = '.';
        newGrid[coordS.getY()][coordS.getX()] = grid[coordS.getY()][coordS.getX()];
        Coord previous = coordS;
        Coord current = coordS;
        Coord next;
        char currentChar;
        sum = 0;
        List<Coord> sNeighbours = new LinkedList<>();
        for (Coord coord : coords) {
            next = current.add(coord);
            try {
                currentChar = grid[next.getY()][next.getX()];
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
            if (tiles.containsKey(currentChar)) {
                if ((current.equals(next.add(tiles.get(currentChar).getC1())) || current.equals(next.add(tiles.get(currentChar).getC2())))) {//&&previous!=next) {
                    sNeighbours.add(coord);
                }
            }
        }
        tiles.put('S', new Pipe(sNeighbours.get(0), sNeighbours.get(1)));

        currentChar = 'S';
        do {
            next = current.add(tiles.get(currentChar).getC1());
            if (next.equals(previous))
                next = current.add(tiles.get(currentChar).getC2());
            currentChar = grid[next.getY()][next.getX()];
            sum++;
            newGrid[current.getY()][current.getX()] = '.';
            newGrid[current.getY()][current.getX()] = grid[current.getY()][current.getX()];
            previous = current;
            current = next;
            if (currentChar == 'S')
                break;
        } while (!next.equals(coordS));
        sum = 0;

        //x3
        char[][] newnewGrid = new char[grid.length * 3][grid[0].length * 3];
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                for (int k = i * 3; k < i * 3 + 3; k++) {
                    for (int l = j * 3; l < j * 3 + 3; l++) {
                        newnewGrid[k][l] = 'o';
                    }
                }
                currentChar = newGrid[i][j];
                newnewGrid[i * 3 + 1][j * 3 + 1] = newGrid[i][j];
                if (tiles.containsKey(currentChar)) {
                    newnewGrid[i * 3 + 1 + tiles.get(currentChar).getC1().getY()][j * 3 + 1 + tiles.get(currentChar).getC1().getX()] = '-';
                    newnewGrid[i * 3 + 1 + tiles.get(currentChar).getC2().getY()][j * 3 + 1 + tiles.get(currentChar).getC2().getX()] = '-';
                }
            }
        }
        queue.add(new Coord(0, 0));
        while (!queue.isEmpty())
            fillOuter1(newnewGrid);
//        HelperFunction.printArray(newnewGrid);
        for (char[] chars : newnewGrid) {
            for (char aChar : chars) {
                if (aChar == 'Q')
                    sum++;
            }
        }
        return sum;
    }

    private static void fillOuter1(char[][] grid) {
        Coord coord = queue.remove();
        visited.put(coord.name, true);
        int i = coord.getX();
        int j = coord.getY();
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || (grid[i][j] != 'o' && grid[i][j] != 'Q'))
            return;
        grid[i][j] = ',';
        if (!visited.containsKey(new Coord(i + 1, j).name))
            queue.add(new Coord(i + 1, j));
        if (!visited.containsKey(new Coord(i - 1, j).name))
            queue.add(new Coord(i - 1, j));
        if (!visited.containsKey(new Coord(i, j + 1).name))
            queue.add(new Coord(i, j + 1));
        if (!visited.containsKey(new Coord(i, j - 1).name))
            queue.add(new Coord(i, j - 1));
    }

    private static void fillOuter(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length || (grid[i][j] != 'o' && grid[i][j] != 'Q'))
            return;
        grid[i][j] = ',';
        fillOuter(grid, i + 1, j);
        fillOuter(grid, i - 1, j);
        fillOuter(grid, i, j + 1);
        fillOuter(grid, i, j - 1);
    }
}
