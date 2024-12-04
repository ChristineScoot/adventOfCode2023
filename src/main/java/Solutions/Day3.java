package Solutions;

import Model.Coord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    private static final Map<Coord, LinkedList<Integer>> gearMapWithNumbers = new HashMap<>();

    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 3: Part 1~~~~~~~~~~~~");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        String prev = "............................................................................................................................................";
        String current = myReader.nextLine();
        String next = myReader.nextLine();
        while (myReader.hasNextLine()) {
            sum += checkLine(prev, current, next);
            prev = current;
            current = next;
            next = myReader.nextLine();
        }
        sum += checkLine(prev, current, next);
        prev = current;
        current = next;
        next = "............................................................................................................................................";
        return sum + checkLine(prev, current, next);
    }

    private static int checkLine(String prev, String current, String next) {
        int partialSum = 0;
        char[] prevC = prev.toCharArray();
        char[] currentC = current.toCharArray();
        char[] nextC = next.toCharArray();
        int currentNumber = 0;
        boolean isAdjecentToSymbol = false;
        boolean isPreviousANumber = false;
        for (int i = 0; i < current.length(); i++) {
            char right = '.';
            if (!isPreviousANumber) {
                currentNumber = 0;
                isAdjecentToSymbol = false;
            }
            if (Character.isDigit(currentC[i])) {
                char upLeft = (i == 0) ? '.' : prevC[i - 1];
                char downLeft = (i == 0) ? '.' : nextC[i - 1];
                char upRight = (i == current.length() - 1) ? '.' : prevC[i + 1];
                char downRight = (i == current.length() - 1) ? '.' : nextC[i + 1];
                char left = (i == 0) ? '.' : currentC[i - 1];
                right = (i == current.length() - 1) ? '.' : currentC[i + 1];
                if (isSymbol(upLeft) || isSymbol(prevC[i]) || isSymbol(upRight))
                    isAdjecentToSymbol = true;
                if (isSymbol(downLeft) || isSymbol(nextC[i]) || isSymbol(downRight))
                    isAdjecentToSymbol = true;
                if (isSymbol(left) || isSymbol(right))
                    isAdjecentToSymbol = true;
                currentNumber = currentNumber * 10 + Integer.parseInt(String.valueOf(currentC[i]));

                isPreviousANumber = true;
            } else {
                isPreviousANumber = false;
            }
            if (isAdjecentToSymbol && !Character.isDigit(right)) {
                partialSum += currentNumber;
                isAdjecentToSymbol = false;
            }
        }
        return partialSum;
    }

    private static boolean isSymbol(char character) {
        return (!(character == '.') && !Character.isDigit(character));
    }

    public static int part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 3: Part 2~~~~~~~~~~~~");
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] input = fileContent.split("\\r?\\n");
        Pattern gearPattern = Pattern.compile("\\*");
        Pattern numberPattern = Pattern.compile("\\d+");
        int currRow = 0;
        for (String s : input) {
            Matcher matcher = gearPattern.matcher(s);
            while (matcher.find()) {
                gearMapWithNumbers.put(new Coord(currRow, matcher.start()), new LinkedList<>());
            }
            currRow++;
        }
        currRow = 0;
        for (String s : input) {
            Matcher numberMatcher = numberPattern.matcher(s);
            int rowUp = currRow - 1;
            int rowDown = currRow + 1;
            while (numberMatcher.find()) {
                int number = Integer.parseInt(s.substring(numberMatcher.start(), numberMatcher.end()));
                int start = Math.max(numberMatcher.start() - 1, 0);
                int end = Math.min(numberMatcher.end() + 1, s.length() - 1);
                if (rowUp >= 0) {
                    Matcher gearMatcherUp = gearPattern.matcher(input[rowUp].substring(start, end));
                    checkGears(gearMatcherUp, rowUp, number, start);
                }
                Matcher gearMatcher = gearPattern.matcher(s.substring(start, end));
                checkGears(gearMatcher, currRow, number, start);
                if (rowDown < input.length - 1) {
                    Matcher gearMatcherDown = gearPattern.matcher(input[rowDown].substring(start, end));
                    checkGears(gearMatcherDown, rowDown, number, start);
                }
            }
            currRow++;
        }
        int sum = 0;
        for (Map.Entry<Coord, LinkedList<Integer>> entry : gearMapWithNumbers.entrySet()) {
            if (entry.getValue().size() == 2)
                sum += entry.getValue().get(0) * entry.getValue().get(1);
        }
        return sum;
    }

    private static void checkGears(Matcher startMatcher, int startRow, int number, int columnOffset) {
        while (startMatcher.find()) {
            Coord foundGearCoord = new Coord(startRow, startMatcher.start() + columnOffset);
            if (gearMapWithNumbers.containsKey(foundGearCoord)) {
                LinkedList<Integer> list = gearMapWithNumbers.remove(foundGearCoord);
                list.add(number);
                gearMapWithNumbers.put(foundGearCoord, list);
            }
        }
    }
}