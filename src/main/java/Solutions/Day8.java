package Solutions;

import Model.MapNode;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class Day8 {

    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 8: Part 1~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        int result = 0;
        Map<String, MapNode> map = new LinkedHashMap<>();
        char[] instructions = scanner.nextLine().toCharArray();
        int instruction = -1;
        scanner.nextLine(); //empty line
        while (scanner.hasNext()) {
            addToMap(scanner, map);
        }
        String current = "AAA"; //starting point
        while (!current.equals("ZZZ")) {
            instruction++;
            if (instruction >= instructions.length) {
                instruction = 0;
            }
            if (instructions[instruction] == 'L') {
                current = map.get(current).getLeft();
            } else if (instructions[instruction] == 'R') {
                current = map.get(current).getRight();
            }
            result++;
        }
        return result;
    }

    private static void addToMap(Scanner scanner, Map<String, MapNode> map) {
        String[] line = scanner.nextLine().split(" = ");
        String[] node = line[1].split(", ");
        map.put(line[0], new MapNode(node[0].replace("(", ""), node[1].replace(")", "")));
    }

    public static long part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 8: Part 2~~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        Map<String, MapNode> map = new LinkedHashMap<>();
        char[] instructions = scanner.nextLine().toCharArray();
        int instruction = -1;
        scanner.nextLine(); //empty line
        while (scanner.hasNext()) {
            addToMap(scanner, map);
        }
        Set<String> firstNode = map.keySet()
                .stream()
                .filter(s -> s.endsWith("A"))
                .collect(Collectors.toSet());
        String[] current = new String[firstNode.size()];
        int j = 0;
        for (String first : firstNode) {
            current[j] = first;
            j++;
        }
        int[] results = new int[current.length];
        
        for (int i = 0; i < current.length; i++) {
            while (!(current[i].charAt(current[i].length() - 1) == 'Z')) {
                instruction++;
                if (instruction >= instructions.length) {
                    instruction = 0;
                }
                if (instructions[instruction] == 'L') {
                    current[i] = map.get(current[i]).getLeft();
                } else if (instructions[instruction] == 'R') {
                    current[i] = map.get(current[i]).getRight();
                }
                results[i]++;
            }
        }

        for (int k : results) {
            System.out.print(k + ", ");
        }
        System.out.println();
        return lcm(results);
    }

    public static long lcm(int[] results) { //Least Common Multiple
        long result = 1;
        int divisor = 2;
        while (true) {
            int counter = 0;
            boolean divisible = false;
            for (int i = 0; i < results.length; i++) {
                if (results[i] == 0) {
                    return 0;
                } else if (results[i] < 0) {
                    results[i] = results[i] * (-1);
                }
                if (results[i] == 1) {
                    counter++;
                }
                if (results[i] % divisor == 0) {
                    divisible = true;
                    results[i] = results[i] / divisor;
                }
            }
            if (divisible) {
                result = result * divisor;
            } else {
                divisor++;
            }
            if (counter == results.length) {
                return result;
            }
        }
    }
}
