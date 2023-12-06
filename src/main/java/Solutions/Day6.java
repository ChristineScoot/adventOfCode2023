package Solutions;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day6 {

    public static int part1(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 6: Part 1~~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        int margin = 1;

        String[] timesString = scanner.nextLine().split(": ")[1].trim().split("\\s+");
        List<Integer> times = new ArrayList<>();
        for (String t : timesString) {
            times.add(Integer.parseInt(t));
        }
        String[] distancesString = scanner.nextLine().split(": ")[1].trim().split("\\s+");
        List<Integer> distances = new ArrayList<>();
        for (String t : distancesString) {
            distances.add(Integer.parseInt(t));
        }
        List<Integer> winningTimes;
//        List<Integer> winningDist;
        for (int i = 0; i < times.size(); i++) {
            winningTimes = new ArrayList<>();
//            winningDist = new ArrayList<>();
            double deltaSqrt = Math.sqrt(Math.pow(times.get(i), 2) - 4 * distances.get(i));
            int t1 = (int) (times.get(i) - deltaSqrt) / 2;
            int t2 = (int) (times.get(i) + deltaSqrt) / 2;
            //v - time for holding a button and velocity
            for (int v = t1 + 1; v < t2 + 1; v++) { //2 3 4 5 should be ok
                if (v * (times.get(i) - v) > distances.get(i)) {
                    winningTimes.add(v);
//                    winningDist.add(v * (times.get(i) - v));
                }
            }
            margin *= winningTimes.size();
        }
        return margin;
    }

    public static int part2(File myObj) throws FileNotFoundException {
        System.out.println("~~~~~~~~~~~~Day 6: Part 2~~~~~~~~~~~~");
        Scanner scanner = new Scanner(myObj);
        int margin = 0;
        long times = Long.parseLong(scanner.nextLine().split(": ")[1].trim().replaceAll(" ", ""));
        long distances = Long.parseLong(scanner.nextLine().split(": ")[1].trim().replaceAll(" ", ""));

        double deltaSqrt = Math.sqrt(Math.pow(times, 2) - 4 * distances);
        int t1 = (int) (times - deltaSqrt) / 2;
        int t2 = (int) (times + deltaSqrt) / 2;
        //v - time for holding a button and velocity
        for (int v = t1 + 1; v < t2 + 1; v++) { //2 3 4 5 should be ok
            if (v * (times - v) > distances) {
                margin++;
            }
        }
        return margin;
    }
}
