package Solutions;

import Model.Coord;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Day11 {
    private static int sum = 0;

    public static void initialise() {
        sum = 0;
    }

    public static int part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 11: Part 1~~~~~~~~~~~");
        initialise();
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        //calculate how many rows are "empty"
        int nOfRows = fileContentString.length;
        for (String s : fileContentString) {
            if (!s.contains("#"))
                nOfRows++;
        }
        char[][] grid = new char[nOfRows][];
        int counter = 0;
        for (String s : fileContentString) {
            grid[counter++] = s.toCharArray();
            if (!s.contains("#"))
                grid[counter++] = s.toCharArray();
        }
        int nOfColumns = fileContentString.length;
        boolean columnHasStar;
        for (int i = 0; i < grid[0].length; i++) {
            columnHasStar = false;
            for (char[] chars : grid) {
                if (chars[i] == '#') {
                    columnHasStar = true;
                    break;
                }
            }
            if (!columnHasStar)
                nOfColumns++;
        }
        //cosmic expantion
        char[][] newGrid = new char[grid.length][nOfColumns];
        counter = 0;
        for (int i = 0; i < grid[0].length; i++) {
            columnHasStar = false;
            for (char[] chars : grid)
                if (chars[i] == '#') {
                    columnHasStar = true;
                    break;
                }
            for (int j = 0; j < grid.length; j++)
                newGrid[j][counter] = grid[j][i];
            counter++;
            if (!columnHasStar) {
                for (int j = 0; j < grid.length; j++)
                    newGrid[j][counter] = grid[j][i];
                counter++;
            }
        }
//        HelperMethods.printArray(grid);
//        HelperMethods.printArray(newGrid);

        //find all pairs
        List<Coord> allCoords = new ArrayList<>();
        for (int i = 0; i < newGrid.length; i++) {
            for (int j = 0; j < newGrid[i].length; j++) {
                if (newGrid[i][j] == '#')
                    allCoords.add(new Coord(i, j));
            }
        }
        for (int i = 0; i < allCoords.size(); i++) {
            for (int j = i; j < allCoords.size(); j++) {
                sum += Math.abs(allCoords.get(i).getX() - allCoords.get(j).getX())
                        + Math.abs(allCoords.get(i).getY() - allCoords.get(j).getY());
            }
        }
        return sum;
    }

    public static BigInteger part2(String filePath, int totalSize) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 11: Part 2~~~~~~~~~~~~");
        initialise();
        BigInteger bigIntegerSum = BigInteger.ZERO;
        int size = totalSize - 1; //expand this amount of times

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        int nOfRows = fileContentString.length;
        List<Integer> rowsMultiplied = new ArrayList<>();
        for (int i = 0; i < fileContentString.length; i++) {
            if (!fileContentString[i].contains("#"))
                rowsMultiplied.add(i);
        }
        char[][] grid = new char[nOfRows][];
        for (int i = 0; i < fileContentString.length; i++) {
            grid[i] = fileContentString[i].toCharArray();
        }
        List<Integer> columnsMultiplied = new ArrayList<>();

        boolean columnHasStar;
        for (int i = 0; i < grid[0].length; i++) {
            columnHasStar = false;
            for (char[] chars : grid) {
                if (chars[i] == '#') {
                    columnHasStar = true;
                    break;
                }
            }
            if (!columnHasStar)
                columnsMultiplied.add(i);
        }
//        HelperMethods.printArray(grid);

        //find all pairs
        List<Coord> allCoords = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '#')
                    allCoords.add(new Coord(i, j));
            }
        }
        for (int i = 0; i < allCoords.size(); i++) {
            for (int j = i; j < allCoords.size(); j++) {
                //how many in columnsMultiplied between i and j
                int columnsCount = 0;
                int rowsCount = 0;
                int p1x = allCoords.get(i).getX(); //0
                int p2x = allCoords.get(j).getX(); //1
                int p1y = allCoords.get(i).getY();
                int p2y = allCoords.get(j).getY();
                for (int col : columnsMultiplied) {
                    if (Math.max(p1y, p2y) > col && Math.min(p1y, p2y) < col)
                        columnsCount++;
                }

                for (int row : rowsMultiplied) {
                    if (Math.max(p1x, p2x) > row && Math.min(p1x, p2x) < row)
                        rowsCount++;
                }
                sum = Math.abs(p1x - p2x) + (size * rowsCount)
                        + Math.abs(p1y - p2y) + (size * columnsCount);
                bigIntegerSum = bigIntegerSum.add(BigInteger.valueOf(sum));
            }
        }
        return bigIntegerSum;
    }
}
