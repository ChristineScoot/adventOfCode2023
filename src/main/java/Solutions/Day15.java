package Solutions;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Day15 {
    private static Integer sum;

    public static void initialise() {
        sum = 0;
    }

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 15: Part 1~~~~~~~~~~~");
        initialise();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] sequence = line.split(",");
        for (String seq : sequence) {
            int currentValue = 0;
            for (char character : seq.toCharArray()) {
                currentValue = (currentValue + character) * 17 % 256;
            }
            sum += currentValue;
        }
        return sum;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 15: Part 2~~~~~~~~~~~~");
        initialise();

        return sum;
    }
}
