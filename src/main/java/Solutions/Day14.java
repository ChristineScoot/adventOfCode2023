package Solutions;

import Model.HelperMethods;
import Model.Rock;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Day14 {
    private static Integer sum;
    private static List<Rock> rocksBasedOnColumnsList = new LinkedList<>();
    private final static List<Rock> cubeshapedRocks = new LinkedList<>();
    private final static List<List<Rock>> savedStatesList = new LinkedList<>();

    public static void initialise() {
        sum = 0;
    }

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 14: Part 1~~~~~~~~~~~");
        initialise();

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] platform = new char[fileContentString.length][];
        int i = 0;
        for (String s : fileContentString) {
            platform[i++] = s.toCharArray();
        }
        HelperMethods.printArray(platform);

        //tilt to the north
        char[][] tiltedPlatform = tiltPlatformNorth(platform);
        System.out.println();
        HelperMethods.printArray(tiltedPlatform);

        setSum(tiltedPlatform);
        return sum;
    }

    private static char[][] tiltPlatformNorth(char[][] platform) {
        int available;
        for (int column = 0; column < platform[0].length; column++) {
            available = 0;
            for (int row = 0; row < platform.length; row++) {
                while (available < platform.length && platform[available][column] != '.')
                    available++;
                char current = platform[row][column];
                if (current == 'O' && available < row) {
                    platform[row][column] = '.';
                    platform[available][column] = 'O';
                    available++;
                } else if (current == '#') {
                    available = row + 1;
                    while (available < platform.length && platform[available][column] != '.')
                        available++;
                }
            }
        }
        return platform;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 14: Part 2~~~~~~~~~~~~");
        initialise();

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        char[][] platform = new char[fileContentString.length][];
        int i = 0;
        for (String s : fileContentString) {
            platform[i++] = s.toCharArray();
        }
//        HelperMethods.printArray(platform);

        //tilt to the north
        for (int column = 0; column < platform[0].length; column++) {
            for (int row = 0; row < platform.length; row++) {
                if (platform[row][column] == 'O') {
                    rocksBasedOnColumnsList.add(new Rock(column, row));
                } else if (platform[row][column] == '#') {
                    cubeshapedRocks.add(new Rock(column, row));
                }
            }
        }

        int times = 1000000000 - 1;
        int firstCycleIndex;
        int lastCycleIndex;
        for (int j = 0; j < times; j++) {
            tiltOneFullCycleOnList(platform);

            if (savedStatesList.contains(new LinkedList<>(rocksBasedOnColumnsList))) { //there is a cycle
                firstCycleIndex = savedStatesList.indexOf(rocksBasedOnColumnsList);
                lastCycleIndex = savedStatesList.size() - 1;
                int x = (times - firstCycleIndex) / (lastCycleIndex - firstCycleIndex + 1); //how many times cycle has to go over
                int indexOfADesiredElement = times - firstCycleIndex - x * (lastCycleIndex - firstCycleIndex + 1);
                rocksBasedOnColumnsList = savedStatesList.get(indexOfADesiredElement + firstCycleIndex);
                break;
            }
            savedStatesList.add(new LinkedList<>(rocksBasedOnColumnsList));
        }

        platform = new char[platform.length][platform[0].length];
        for (char[] chars : platform) {
            Arrays.fill(chars, '.');
        }
        for (Rock r : rocksBasedOnColumnsList) {
            platform[r.getY()][r.getX()] = 'O';
        }
        for (Rock r : cubeshapedRocks) {
            platform[r.getY()][r.getX()] = '#';
        }
//        HelperMethods.printArray(platform);

        setSum(platform);
        return sum;
    }

    private static void setSum(char[][] platform) {
        sum = 0;
        int weight = platform.length;
        for (char[] chars : platform) {
            String line = Arrays.toString(chars);
            int rocks = line.length() - line.replaceAll("O", "").length();
            sum += rocks * weight;
            weight--;
        }
    }

    private static void tiltOneFullCycleOnList(char[][] platform) {
        //tiltNorth
        List<Rock> rocksBasedOnRowsList = new LinkedList<>(rocksBasedOnColumnsList);
        rocksBasedOnColumnsList.sort(Rock::compareTo);
        for (Rock r : rocksBasedOnColumnsList) {
            int x = r.getX();
            int oldY = r.getY();
            int newY = oldY;
            while ((!rocksBasedOnRowsList.contains(new Rock(x, newY - 1)) && !cubeshapedRocks.contains(new Rock(x, newY - 1))) && newY > 0) {
                newY--;
            }
            if (oldY != newY) {
                rocksBasedOnRowsList.remove(r);
                rocksBasedOnRowsList.add(new Rock(x, newY));
            }
        }

        //tiltWest
        rocksBasedOnColumnsList = new LinkedList<>(rocksBasedOnRowsList);
        rocksBasedOnRowsList.sort(Rock::compareTo);
        for (Rock r : rocksBasedOnRowsList) {
            int y = r.getY();
            int oldX = r.getX();
            int newX = oldX;
            while ((!rocksBasedOnColumnsList.contains(new Rock(newX - 1, y)) && !cubeshapedRocks.contains(new Rock(newX - 1, y))) && newX > 0) {
                newX--;
            }
            if (oldX != newX) {
                rocksBasedOnColumnsList.remove(r);
                rocksBasedOnColumnsList.add(new Rock(newX, y));
            }
        }

        //tiltSouth
        rocksBasedOnRowsList = new LinkedList<>(rocksBasedOnColumnsList);
        rocksBasedOnColumnsList.sort(Collections.reverseOrder());
        for (Rock r : rocksBasedOnColumnsList) {
            int x = r.getX();
            int oldY = r.getY();
            int newY = oldY;
            while ((!rocksBasedOnRowsList.contains(new Rock(x, newY + 1)) && !cubeshapedRocks.contains(new Rock(x, newY + 1))) && newY < platform.length - 1) {
                newY++;
            }
            if (oldY != newY) {
                rocksBasedOnRowsList.remove(r);
                rocksBasedOnRowsList.add(new Rock(x, newY));
            }
        }

        //tiltEast
        rocksBasedOnColumnsList = new LinkedList<>(rocksBasedOnRowsList);
        rocksBasedOnRowsList.sort(Collections.reverseOrder());
        for (Rock r : rocksBasedOnRowsList) {
            int y = r.getY();
            int oldX = r.getX();
            int newX = oldX;
            while ((!rocksBasedOnColumnsList.contains(new Rock(newX + 1, y)) && !cubeshapedRocks.contains(new Rock(newX + 1, y))) && newX < platform[0].length - 1) {
                newX++;
            }
            if (oldX != newX) {
                rocksBasedOnColumnsList.remove(r);
                rocksBasedOnColumnsList.add(new Rock(newX, y));
            }
        }
    }
}
