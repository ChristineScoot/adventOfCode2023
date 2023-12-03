package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day3 {
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
        return sum += checkLine(prev, current, next);
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
}