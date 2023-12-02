import Solutions.Day1;
import Solutions.Day2;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Day1.part1();
        Day1.part2();
        System.out.println(Day2.part1(new File("src/main/resources/input2_1.txt")));
        System.out.println(Day2.part2(new File("src/main/resources/input2_1.txt")));
    }
}
