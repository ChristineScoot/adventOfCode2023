package Solutions;

import Model.Rule;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Day19 {
    private BigInteger sum;
    private final Map<String, List<Rule>> workflows = new HashMap<>();

    public void initialise() {
        sum = BigInteger.ZERO;
    }

    public BigInteger part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 19: Part 1~~~~~~~~~~~");
        initialise();

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");

        int counter = 0;
        for (String workflow : fileContentString) {
            counter++;
            if (workflow.isEmpty()) break;
            List<Rule> rules = new LinkedList<>();
            String[] a = workflow.split("[{,}]");

            for (int i = 1; i < a.length; i++) {
                if (i == a.length - 1) {
                    rules.add(new Rule(a[i])); //last rule aka destination
                    break;
                }
                rules.add(new Rule(a[i].substring(0, 1), a[i].substring(1, 2), a[i].substring(2, a[i].indexOf(":")), a[i].substring(a[i].indexOf(":") + 1)));
            }
            String name = a[0];
            workflows.put(name, rules);
        }
        List<Xmas> allXmas = new LinkedList<>();
        for (int i = counter; i < fileContentString.length; i++) {
            String xmas = fileContentString[i];
            String[] xmasSplit = xmas.split("[{x=,mas}]");
            xmasSplit = Arrays.stream(xmasSplit).filter((String s) -> !s.isEmpty()).toArray(String[]::new);
            allXmas.add(new Xmas(Integer.parseInt(xmasSplit[0]), Integer.parseInt(xmasSplit[1]), Integer.parseInt(xmasSplit[2]), Integer.parseInt(xmasSplit[3])));
        }
        for (Xmas xmas : allXmas) {
            calc(xmas.x, xmas.m, xmas.a, xmas.s);
        }

        return sum;
    }

    private void calc(int x, int m, int a, int s) {
        String currRule = "in";
        while (!(currRule.equals("A") || currRule.equals("R"))) {
            List<Rule> current = workflows.get(currRule);
            String nextRule = "";
            for (Rule curr : current) {
                nextRule = curr.getNextRule(x, m, a, s);
                if (nextRule != null)
                    break;
            }
            currRule = nextRule;
        }
        if (currRule.equals("A")) {
            int localSum = x + m + a + s;
            sum = sum.add(new BigInteger(String.valueOf(localSum)));
        }
    }

    public BigInteger part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 19: Part 2~~~~~~~~~~~");
        initialise();

        String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
        String[] fileContentString = fileContent.split("\\r?\\n");

        int counter = 0;
        for (String workflow : fileContentString) {
            counter++;
            if (workflow.isEmpty()) break;
            List<Rule> rules = new LinkedList<>();
            String[] a = workflow.split("[{,}]");

            for (int i = 1; i < a.length; i++) {
                if (i == a.length - 1) {
                    rules.add(new Rule(a[i])); //last rule aka destination
                    break;
                }
                rules.add(new Rule(a[i].substring(0, 1), a[i].substring(1, 2), a[i].substring(2, a[i].indexOf(":")), a[i].substring(a[i].indexOf(":") + 1)));
            }
            String name = a[0];
            workflows.put(name, rules);
        }
        Map<String, MinMax> dictionary = new HashMap<>(); //"in" + range
        dictionary.put("x", new MinMax(1, 4000));
        dictionary.put("m", new MinMax(1, 4000));
        dictionary.put("a", new MinMax(1, 4000));
        dictionary.put("s", new MinMax(1, 4000));

        sum = BigInteger.ONE;
        sum = count(dictionary, "in");

        return sum;
    }

    public BigInteger count(Map<String, MinMax> ranges, String name) {
        if (name.equals("R"))
            return BigInteger.ZERO;
        if (name.equals("A")) {
            BigInteger product = BigInteger.ONE;
            for (MinMax xmas : ranges.values()) {
                String range = String.valueOf(xmas.max - xmas.min + 1);
                product = product.multiply(new BigInteger(range));
            }
            return product;
        }
        List<Rule> rules = workflows.get(name);
        BigInteger total = BigInteger.ZERO;
        for (Rule curr : rules) {
            if (curr.getPart() == null) { //last 'rule'
                total = total.add(count(ranges, curr.getDestination()));
                break;
            }
            MinMax trueRange, falseRange;
            if (curr.getSign().equals("<")) {
                trueRange = new MinMax(ranges.get(curr.getPart()).min, curr.getValue() - 1);
                falseRange = new MinMax(curr.getValue(), ranges.get(curr.getPart()).max);
            } else {
                trueRange = new MinMax(curr.getValue() + 1, ranges.get(curr.getPart()).max);
                falseRange = new MinMax(ranges.get(curr.getPart()).min, curr.getValue());
            }
            if (trueRange.min <= trueRange.max) {
                Map<String, MinMax> ranges2 = new HashMap<>(ranges);
                ranges2.put(curr.getPart(), trueRange);
                total = total.add(count(ranges2, curr.getDestination()));
            }
            if (falseRange.min <= falseRange.max) {
                ranges.put(curr.getPart(), falseRange);
            } else {
                break;
            }
        }
        return total;
    }

    private record Xmas(int x, int m, int a, int s) {
    }

    private record MinMax(int min, int max) {
    }
}

