package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Day1 {
    public static void part1() throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 1: Part 1~~~~~~~~~~~~");
        File myObj = new File("src/main/resources/input1_1.txt");
//        File myObj = new File("src/main/resources/input1_1eg.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        while (myReader.hasNextLine()) {
            String instructions = myReader.nextLine();
            int firstDigit = calculateFirstDigit(instructions);
            int secondFigit = calculateSecondDigit(instructions);
            sum += (firstDigit * 10 + secondFigit);
        }
        System.out.println(sum);
    }

    private static int calculateFirstDigit(String instructions) {
        char[] array = instructions.toCharArray();
        int pointer_a = 0;
        int firstDigit = 0;
        while (pointer_a < instructions.length()) {
            try {
                firstDigit = Integer.parseInt(String.valueOf(array[pointer_a]));
                break;
            } catch (Exception e) {
                //throws an Exception when array[a] is not a digit
            }
            pointer_a++;
        }
        return firstDigit;
    }

    private static int calculateSecondDigit(String instructions) {
        char[] array = instructions.toCharArray();
        int pointer_b = instructions.length() - 1;
        int secondDigit = 0;
        while (pointer_b >= 0) {
            try {
                secondDigit = Integer.parseInt(String.valueOf(array[pointer_b]));
                break;
            } catch (Exception e) {
                //throws an Exception when array[b] is not a digit
            }
            pointer_b--;
        }
        return secondDigit;
    }

    public static void part2() throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 1: Part 2~~~~~~~~~~~~");
        File myObj = new File("src/main/resources/input1_1.txt");
//        File myObj = new File("src/main/resources/input1_1eg1.txt");
        Scanner myReader = new Scanner(myObj);
        int sum = 0;
        List<String> wordsNumbers = new ArrayList<>();
        wordsNumbers.add("one");
        wordsNumbers.add("two");
        wordsNumbers.add("three");
        wordsNumbers.add("four");
        wordsNumbers.add("five");
        wordsNumbers.add("six");
        wordsNumbers.add("seven");
        wordsNumbers.add("eight");
        wordsNumbers.add("nine");
        while (myReader.hasNextLine()) {
            String instructions = myReader.nextLine();
            instructions = instructions.toLowerCase();
            for (int k = 0; k < instructions.length(); k++) {
                HashMap<Integer, Integer> map = new HashMap<>(); //<what digit is a word, what's it's index>
                int indexOfFirstFind;
                for (int i = 0; i < wordsNumbers.size(); i++) {
                    if (instructions.contains(wordsNumbers.get(i))) {
                        indexOfFirstFind = instructions.indexOf(wordsNumbers.get(i));
                        map.put(i + 1, indexOfFirstFind);
                    }
                }

                String smallestWord = ""; //first word from the left, of the smallest index
                int smallestIndex;
                while (!map.isEmpty()) {
                    smallestIndex = 999999999;
                    for (int i = 1; i <= 9; i++) {
                        if (!map.containsKey(i) || map.get(i) == -1)
                            continue;
                        if (smallestIndex > map.get(i)) {
                            smallestIndex = map.remove(i);
                            smallestWord = wordsNumbers.get(i - 1);
                        }
                    }
                    for (int i = 0; i < wordsNumbers.size(); i++) {
                        if (wordsNumbers.get(i).equals(smallestWord)) {
                            smallestWord = smallestWord.substring(0, 1);
                            if (smallestIndex + 1 >= instructions.length() - 1)
                                instructions = instructions.substring(0, smallestIndex) + (i + 1);
                            else
                                instructions = instructions.substring(0, smallestIndex) + (i + 1) + instructions.substring(smallestIndex + 1);
                            break;
                        }
                    }
                }
            }
            int firstDigit = calculateFirstDigit(instructions);
            int secondDigit = calculateSecondDigit(instructions);
            sum += (firstDigit * 10 + secondDigit);
        }
        System.out.println(sum);
    }
}
