package Solutions;

import Model.Box;
import Model.Lense;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

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
                currentValue = hash(currentValue, character);
            }
            sum += currentValue;
        }
        return sum;
    }

    private static int hash(int currentValue, char character) {
        return (currentValue + character) * 17 % 256;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 15: Part 2~~~~~~~~~~~~");
        initialise();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        String line = scanner.nextLine();
        String[] sequence = line.split(",");
        Map<Integer, Box> boxes = new TreeMap<>(); //box nr / box
        for (String seq : sequence) {
            String label;
            String focalLength = "0";
            boolean toBeRemoved = false;
            if (seq.contains("-")) {
                label = seq.replace("-", "");
                toBeRemoved = true;
            } else {
                String[] lll = seq.split("=");
                label = lll[0];
                focalLength = lll[1];
            }
            int boxNumber = 0;
            for (char labelChar : label.toCharArray()) {
                boxNumber = hash(boxNumber, labelChar);
            }

            if (toBeRemoved && boxes.containsKey(boxNumber)) {
                //remove lense from box boxNumber
                boxes.get(boxNumber).removeLense(new Lense(label));
            } else {
                if (!boxes.containsKey(boxNumber))
                    boxes.put(boxNumber, new Box());
                boxes.get(boxNumber).addLense(new Lense(label, Integer.parseInt(focalLength)));
            }
        }
        for (Integer boxNr : boxes.keySet()) {
            sum += boxes.get(boxNr).countFocalLength(boxNr);
        }
        return sum;
    }
}
