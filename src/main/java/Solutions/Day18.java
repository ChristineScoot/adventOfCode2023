package Solutions;

import Model.Coord;
import Model.Trench;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.*;

public class Day18 {
    private static BigInteger sum;
    private static final Map<Character, Coord> directionCoords = new HashMap<>();

    public static void initialise() {
        sum = BigInteger.ZERO;
        directionCoords.put('R', new Coord(1, 0));
        directionCoords.put('L', new Coord(-1, 0));
        directionCoords.put('U', new Coord(0, 1));
        directionCoords.put('D', new Coord(0, -1));
    }

    public static BigInteger part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 18: Part 1~~~~~~~~~~~");
        initialise();

        int currentX = 0;
        int currentY = 0;
        List<Trench> list = new LinkedList<>();
        Trench trench = new Trench(new Coord(currentX, currentY), "colour");
        list.add(trench);

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineSplit = line.split(" ");
            Character direction = lineSplit[0].charAt(0);
            int number = Integer.parseInt(lineSplit[1]);
            String colour = lineSplit[2];

            Coord directionCoord = directionCoords.get(direction);
            Coord coord = new Coord(currentX, currentY).add(new Coord(directionCoord.getX() * number, directionCoord.getY() * number));
            currentX = coord.getX();
            currentY = coord.getY();
            trench = new Trench(coord, colour);
            list.add(trench);
        }

        return calculateArea(list);
    }

    public static BigInteger part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 18: Part 2~~~~~~~~~~~~");
        initialise();

        int currentX = 0;
        int currentY = 0;
        List<Trench> list = new LinkedList<>();
        Trench trench = new Trench(new Coord(currentX, currentY), "colour");
        list.add(trench);

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            String[] lineSplit = line.split(" ");
            char direction = lineSplit[0].charAt(0);
            String colour = lineSplit[2].replaceAll("\\(?\\)?#?", "");
//            #70c710 - first 5 chars - distance
//            last char - direction; 0 means R, 1 means D, 2 means L, and 3 means U
            int number = Integer.parseInt(colour.substring(0, 5), 16);
            switch (colour.charAt(5)) {
                case '0' -> direction = 'R';
                case '1' -> direction = 'D';
                case '2' -> direction = 'L';
                case '3' -> direction = 'U';
            }

            Coord directionCoord = directionCoords.get(direction);
            Coord coord = new Coord(currentX, currentY).add(new Coord(directionCoord.getX() * number, directionCoord.getY() * number));
            currentX = coord.getX();
            currentY = coord.getY();
            trench = new Trench(coord, colour);
            list.add(trench);
        }

        return calculateArea(list);
    }

    private static BigInteger calculateArea(List<Trench> list) {
        //Pick's theorem A=I + B/2 - 1, I-interior lattice points, B-boundary
        BigInteger b = BigInteger.ZERO;
        BigInteger i;
        for (int j = 0; j < list.size() - 1; j++) {
            Coord current = list.get(j).getCoord();
            Coord next = list.get((j + 1)).getCoord();
            BigInteger a1 = new BigInteger(String.valueOf(current.getX() - next.getX())).abs();
            BigInteger b1 = new BigInteger(String.valueOf((current.getY() - next.getY()))).abs();
            b = b.add(gcd(a1, b1));
        }
        i = shoeLace(list);
        b = b.divide(BigInteger.TWO);
        return new BigInteger(String.valueOf(i.add(b).add(BigInteger.valueOf(1))));
    }

    private static BigInteger gcd(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) return a;
        return gcd(b, a.mod(b));
    }

    private static BigInteger shoeLace(List<Trench> list) {
        BigInteger x2;
        BigInteger y2;
        for (int i = 0; i < list.size() - 1; i++) {
            Trench trench1 = list.get(i);
            Trench trench2 = list.get(i + 1);
            BigInteger x1 = BigInteger.valueOf(trench1.getCoord().getX());
            BigInteger y1 = BigInteger.valueOf(trench1.getCoord().getY());
            x2 = BigInteger.valueOf(trench2.getCoord().getX());
            y2 = BigInteger.valueOf(trench2.getCoord().getY());
            sum = sum.add(x1.multiply(y2).subtract(x2.multiply(y1)));
        }
        sum = sum.abs();
        sum = sum.divide(BigInteger.TWO);
        return sum;
    }
}
