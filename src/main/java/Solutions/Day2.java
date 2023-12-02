package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Day2 {
    private static int sum;

    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 2: Part 1~~~~~~~~~~~~");
        Scanner myReader = new Scanner(myObj);
        sum = 0;
        while (myReader.hasNextLine()) {
            HashMap<String, Integer> maxColours = readMax(myReader.nextLine());
            if (maxColours.get("blue") <= 14 && maxColours.get("red") <= 12 && maxColours.get("green") <= 13)
                sum += maxColours.get("game");
        }
        return sum;
    }

    public static int part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 2: Part 2~~~~~~~~~~~~");
        Scanner myReader = new Scanner(myObj);
        sum = 0;
        while (myReader.hasNextLine()) {
            HashMap<String, Integer> maxColours = readMax(myReader.nextLine());
            sum += maxColours.get("green") * maxColours.get("blue") * maxColours.get("red");
        }
        return sum;
    }

    private static HashMap<String, Integer> readMax(String instructions) {
        HashMap<String, Integer> map = new HashMap<>();
        String[] splitedGameAndSets = instructions.split(":");
        int gameNumber = Integer.parseInt(splitedGameAndSets[0].replace("Game ", ""));
        String[] sets = splitedGameAndSets[1].split("; ");
        int maxBlue = 0;
        int maxGreen = 0;
        int maxRed = 0;
        for (String set : sets) {
            String[] cubes = set.replaceAll(" ", "").split(",");
            for (String cube : cubes) {
                if (cube.contains("blue")) {
                    maxBlue = Math.max(maxBlue, Integer.parseInt(cube.replace("blue", "")));
                } else if (cube.contains("red")) {
                    maxRed = Math.max(maxRed, Integer.parseInt(cube.replace("red", "")));
                } else if (cube.contains("green")) {
                    maxGreen = Math.max(maxGreen, Integer.parseInt(cube.replace("green", "")));
                }
            }
        }
        map.put("blue", maxBlue);
        map.put("red", maxRed);
        map.put("green", maxGreen);
        map.put("game", gameNumber);
        return map;
    }
}
