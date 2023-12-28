package Solutions;

import Model.Input;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Day12 {
    private static BigInteger sum;
    private final static Map<Input, Long> memoizationMap = new HashMap<>(); // spring part/current

    public static void initialise() {
        sum = BigInteger.ZERO;
    }

    public static BigInteger part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 12: Part 1~~~~~~~~~~~");
        initialise();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String springs;
        List<Integer> numbers;
        while (scanner.hasNext()) {
            numbers = new LinkedList<>();
            String line = scanner.nextLine();
            String[] lineSplit = line.split(" ");
            springs = lineSplit[0];
            String[] numbersString = lineSplit[1].split(",");
            for (String num : numbersString) {
                numbers.add(Integer.parseInt(num));
            }
            long permutations = countPermutations(springs, new LinkedList<>(numbers));
            System.out.println(springs + " - " + permutations + " arrangements");
            sum = sum.add(BigInteger.valueOf(permutations));
        }
        return sum;
    }

    public static BigInteger part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 12: Part 2~~~~~~~~~~~~");
        initialise();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String springs;
        List<Integer> numbers;
        while (scanner.hasNext()) {
            numbers = new LinkedList<>();
            String line = scanner.nextLine();
            String[] lineSplit = line.split(" ");
            springs = lineSplit[0];
            String[] numbersString = lineSplit[1].split(",");
            for (String num : numbersString) {
                numbers.add(Integer.parseInt(num));
            }
            springs = unfoldSprings(springs);
            numbers = unfoldNumbers(numbers);
            long permutations = countPermutations(springs, new LinkedList<>(numbers));
            System.out.print(springs + " ");
            for (int i = 0; i < numbers.size() - 1; i++) {
                System.out.print(numbers.get(i) + ",");
            }
            System.out.print(numbers.get(numbers.size() - 1));
            System.out.println(" - " + permutations + " arrangements");
            sum = sum.add(BigInteger.valueOf(permutations));
        }
        return sum;
    }

    private static long countPermutations(String springs, List<Integer> numbers) {
        Input input = new Input(springs, numbers);
        if (memoizationMap.containsKey(input)) {
            return memoizationMap.get(input);
        }

        long permutations = 0;
        if (springs.length() == 0) {
            return numbers.isEmpty() ? 1 : 0;
        }
        if (springs.charAt(0) == '.') //skip the dot
            permutations = countPermutations(springs.substring(1), new LinkedList<>(numbers));
        else if (springs.charAt(0) == '?') { //replace ? with . and #
            permutations = countPermutations(springs.replaceFirst("\\?", "."), new LinkedList<>(numbers))
                    + countPermutations(springs.replaceFirst("\\?", "#"), new LinkedList<>(numbers));
        } else { //first char is a #
            if (!numbers.isEmpty()) {
                int nextNumber = numbers.get(0);
                if (nextNumber <= springs.length() && springs.chars().limit(nextNumber).allMatch(c -> c == '#' || c == '?')) { //are there enough # or ? in a row?
                    List<Integer> newNumbers = numbers.subList(1, numbers.size());
                    if (nextNumber == springs.length())
                        permutations = newNumbers.isEmpty() ? 1 : 0;
                    else if (springs.charAt(nextNumber) == '?')
                        permutations = countPermutations(springs.substring(nextNumber).replaceFirst("\\?", "."), newNumbers);
                    else if (springs.charAt(nextNumber) == '.') //next char is a dot
                        permutations += countPermutations(springs.substring(nextNumber + 1), newNumbers);
                }
            }
        }
        memoizationMap.put(input, permutations);
        return permutations;
    }

    private static String unfoldSprings(String springs) {
        StringBuilder stringBuilder = new StringBuilder(springs);
        for (int i = 0; i < 4; i++) {
            stringBuilder.append("?");
            stringBuilder.append(springs);
        }
        return stringBuilder.toString();
    }

    private static LinkedList<Integer> unfoldNumbers(List<Integer> numbers) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < 5; i++) {
            list.addAll(numbers);
        }
        return list;
    }
}
