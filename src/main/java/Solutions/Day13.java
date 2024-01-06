package Solutions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day13 {
    private static long sum;

    public static void initialise() {
        sum = 0;
    }

    public static long part1(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 13: Part 1~~~~~~~~~~~");
        initialise();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<String> vertical = new ArrayList<>();
        List<String> horizontal = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                sum += 100 * calculateMirror(vertical)
                        + calculateMirror(horizontal);
                vertical = new ArrayList<>();
                horizontal = new ArrayList<>();
                continue;
            }
            vertical.add(line);
            for (int i = 0; i < line.length(); i++) { //flip vertical to horizontal
                String hor = "";
                try {
                    hor = horizontal.remove(i);
                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("There's no such line");
                }
                hor = hor.concat(String.valueOf(line.toCharArray()[i]));
                horizontal.add(i, hor);
            }

        }
        sum += 100 * calculateMirror(vertical)
                + calculateMirror(horizontal);
        return sum;
    }

    public static long part2(String filePath) throws IOException {
        System.out.println("~~~~~~~~~~~~Day 13: Part 2~~~~~~~~~~~~");
        initialise();

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);
        List<String> vertical = new ArrayList<>();
        List<String> horizontal = new ArrayList<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) {
                sum += 100 * calculateMirror2(vertical)
                        + calculateMirror2(horizontal);
                vertical = new ArrayList<>();
                horizontal = new ArrayList<>();
                continue;
            }
            vertical.add(line);
            for (int i = 0; i < line.length(); i++) { //flip vertical to horizontal
                String hor = "";
                try {
                    hor = horizontal.remove(i);
                } catch (IndexOutOfBoundsException e) {
//                    System.out.println("There's no such line");
                }
                hor = hor.concat(String.valueOf(line.toCharArray()[i]));
                horizontal.add(i, hor);
            }

        }
        sum += 100 * calculateMirror2(vertical)
                + calculateMirror2(horizontal);
        return sum;
    }

    private static long calculateMirror(List<String> pattern) {
        int i;
        int j;
        for (i = 0; i < pattern.size(); i++) {
            for (j = pattern.size() - 1; j > i; j--) {
                if (pattern.get(i).equals(pattern.get(j))) {
                    long line = checkIfAllMatch(pattern, i, j);
                    if (line != -1)
                        return line;
                }
            }
        }
        return 0;
    }

    private static long calculateMirror2(List<String> pattern) {
        int smudges;
        int i;
        for (i = 1; i < pattern.size(); i++) {
            //check if first pair 'around mirror' is ok a.k.a. is the same or has one smudge
            smudges = getNrSmudges(pattern.get(i - 1), pattern.get(i));
            if (smudges == 0 || smudges == 1) {
                //go away from the mirror
                int pointer_a = i - 2;
                int pointer_b = i + 1;
                //check number of outer smudges
                while (pointer_b < pattern.size() && pointer_a >= 0) {
                    smudges += getNrSmudges(pattern.get(pointer_a), pattern.get(pointer_b));
                    pointer_a--;
                    pointer_b++;
                    if (smudges > 1)
                        break;
                }
            }
            if (smudges == 1)
                return i;
        }
        return 0;
    }

    private static int getNrSmudges(String line1, String line2) {
        int smudges = 0;
        for (int i = 0; i < line1.length(); i++) {
            if (line1.charAt(i) != line2.charAt(i))
                smudges++;
        }
        return smudges;
    }

    private static long checkIfAllMatch(List<String> pattern, int i, int j) {
        int iTemp = i;
        int jTemp = j;
        int result = -1;
        while (pattern.get(i).equals(pattern.get(j))) {
            if (j < i) {
                result = i;
                break;
            }
            i++;
            j--;
            if (i == j) {
                break;
            }
        }
        if (result != -1) {
            while (iTemp >= 0 && jTemp < pattern.size() && pattern.get(iTemp).equals(pattern.get(jTemp))) {
                iTemp--;
                jTemp++;
            }
            if (iTemp < 0 || jTemp >= pattern.size()) //all outer were equals
                return result;
            if (!pattern.get(iTemp).equals(pattern.get(jTemp)))
                result = -1;
        }
        return result;
    }
}
