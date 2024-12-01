package Solutions;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day24 {
    public static long part1(String filePath, BigDecimal boundryLow, BigDecimal boundryHigh) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 23: Part 1~~~~~~~~~~~~");
        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");
        Map<String, BigDecimal[]> equations = new HashMap<>();
        for (String s : fileContentString) {
            String[] line = s.split("[,@]");
            BigDecimal x0 = new BigDecimal(line[0].trim());
            BigDecimal y0 = new BigDecimal(line[1].trim());
            BigDecimal vx = new BigDecimal(line[3].trim());
            BigDecimal vy = new BigDecimal(line[4].trim());
//            through velocity calculate hailstone position after one nanosecond x1,y1
            BigDecimal x1 = x0.add(vx);
            BigDecimal y1 = y0.add(vy);
//            function from 2 points - y=ax+b
//            a = (y0-y1) / (x0-x1);
            BigDecimal upper = y0.subtract(y1);
            BigDecimal lower = x0.subtract(x1);
            BigDecimal a = upper.divide(lower, 5, RoundingMode.HALF_UP);
            BigDecimal b = y1.subtract(a.multiply(x1));
            equations.put(s, new BigDecimal[]{a, b});
        }

        long count = 0;

        for (int i = 0; i < fileContentString.length - 1; i++) {
            for (int j = i + 1; j < fileContentString.length; j++) {
                String hailstoneA = fileContentString[i];
                String hailstoneB = fileContentString[j];
                System.out.println("Hailstone A: " + hailstoneA.replaceAll(" {2}", " "));
                System.out.println("Hailstone B: " + hailstoneB);
                if (equations.get(hailstoneA)[0].equals(equations.get(hailstoneB)[0])) {
                    System.out.println("Hailstones' paths are parallel; they never intersect.\n");
                    continue;
                }
//                resolve a1b1 with a2b2 to get a point where they will intersect
//                Cramer's Rule
//                a1x+b1y=c1
//                a2x+b2y=c2
                BigDecimal c1 = equations.get(hailstoneA)[1].multiply(new BigDecimal("-1"));
                BigDecimal c2 = equations.get(hailstoneB)[1].multiply(new BigDecimal("-1"));
                BigDecimal b1 = new BigDecimal("-1");
                BigDecimal b2 = new BigDecimal("-1");
                BigDecimal a1 = equations.get(hailstoneA)[0];
                BigDecimal a2 = equations.get(hailstoneB)[0];
                BigDecimal xUpper = c1.multiply(b2).subtract(c2.multiply(b1)); //c1*b2 - c2*b1
                BigDecimal xLower = a1.multiply(b2).subtract(a2.multiply(b1));

                BigDecimal yUpper = a1.multiply(c2).subtract(a2.multiply(c1)); //a1*c2 - a2*c1
                BigDecimal yLower = a1.multiply(b2).subtract(a2.multiply(b1));

                BigDecimal x = xUpper.divide(xLower, 5, RoundingMode.HALF_UP);
                BigDecimal y = yUpper.divide(yLower, 5, RoundingMode.HALF_UP);

                //Taking past into considereation
                String vsA = hailstoneA.substring(hailstoneA.indexOf('@') + 1);
                int vxA = Integer.parseInt(vsA.substring(0, vsA.indexOf(',')).trim());
                String vsB = hailstoneB.substring(hailstoneB.indexOf('@') + 1);
                int vxB = Integer.parseInt(vsB.substring(0, vsB.indexOf(',')).trim());
                BigDecimal xStartA = new BigDecimal(hailstoneA.substring(0, hailstoneA.indexOf(',')));
                BigDecimal xStartB = new BigDecimal(hailstoneB.substring(0, hailstoneB.indexOf(',')));
                boolean conditionA = ((x.subtract(xStartA).compareTo(BigDecimal.ZERO) > 0 && vxA < 0) ||
                        (x.subtract(xStartA).compareTo(BigDecimal.ZERO) < 0 && vxA > 0));
                boolean conditionB = ((x.subtract(xStartB).compareTo(BigDecimal.ZERO) > 0 && vxB < 0) ||
                        (x.subtract(xStartB).compareTo(BigDecimal.ZERO) < 0 && vxB > 0));

                if (conditionA) {
                    if (conditionB) {
                        System.out.println("Hailstones' paths crossed in the past for both hailstones." + "(at x=" + x + ", y=" + y + ").\n");
                        continue;
                    }
                    System.out.println("Hailstones' paths crossed in the past for hailstone A." + "(at x=" + x + ", y=" + y + ").\n");
                    continue;
                }
                if (conditionB) {
                    System.out.println("Hailstones' paths crossed in the past for hailstone B." + "(at x=" + x + ", y=" + y + ").\n");
                    continue;
                }

                if (x.compareTo(boundryHigh) <= 0 && x.compareTo(boundryLow) >= 0
                        && y.compareTo(boundryHigh) <= 0 && y.compareTo(boundryLow) >= 0) {
                    System.out.println("Hailstones' paths will cross inside the test area (at x=" + x + ", y=" + y + ").\n");
                    count++;
                    continue;
                }

                System.out.println("Hailstones' paths will cross outside the test area (at x=" + x + ", y=" + y + ").\n");
            }
        }
        return count;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 24: Part 2~~~~~~~~~~~~");

        return 0;
    }
}
