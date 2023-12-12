package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Day9 {
    private static int sum = 0;

    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 9: Part 1~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        while (scanner.hasNext()) {
            String[] sensorString = scanner.nextLine().split(" ");
            int[] sensorLine = new int[sensorString.length];
            for (int i = 0; i < sensorString.length; i++) {
                sensorLine[i] = Integer.parseInt(sensorString[i]);
            }
            readLastNumber(sensorLine);
        }
        return sum;
    }

    private static void readLastNumber(int[] array) {
        int[] newArray = new int[array.length - 1];
        boolean isAllZeroes = true;
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i + 1] - array[i];
            if (newArray[i] != 0)
                isAllZeroes = false;
        }
        if (!isAllZeroes)
            readLastNumber(newArray);
        sum += array[array.length - 1];
    }

    private static int readFirstNumber(int[] array) {
        int[] newArray = new int[array.length - 1];
        boolean isAllZeroes = true;
        for (int i = 0; i < newArray.length; i++) {
            newArray[i] = array[i + 1] - array[i];
            if (newArray[i] != 0)
                isAllZeroes = false;
        }
        if (!isAllZeroes)
            readFirstNumber(newArray);
        sum = array[0] - sum;
        return sum;
    }

    public static long part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 9: Part 2~~~~~~~~~~~~");
        int resultSum = 0;
        Scanner scanner = new Scanner(myObj);
        while (scanner.hasNext()) {
            sum = 0;
            String[] sensorString = scanner.nextLine().split(" ");
            int[] sensorLine = new int[sensorString.length];
            for (int i = 0; i < sensorString.length; i++) {
                sensorLine[i] = Integer.parseInt(sensorString[i]);
            }
            resultSum += readFirstNumber(sensorLine);
        }
        return resultSum;
    }
}
