import Solutions.Day1;
import Solutions.Day2;
import Solutions.Day3;
import Solutions.Day4;

import java.io.File;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Day1.part1();
        Day1.part2();
        System.out.println(Day2.part1(new File("src/main/resources/input2_1.txt")));
        System.out.println(Day2.part2(new File("src/main/resources/input2_1.txt")));
        System.out.println(Day3.part1(new File("src/main/resources/input3_1.txt")));
//        System.out.println(Day3.part2(new File("src/main/resources/input3_1.txt")));
        System.out.println(Day4.part1(new File("src/main/resources/input4_1.txt")));
        System.out.println(Day4.part2(new File("src/main/resources/input4_1.txt")));
    }
}
