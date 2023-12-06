import Solutions.*;

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
        System.out.println(Day5.part1(new File("src/main/resources/input5_1.txt")));
        //Part 2 is a brute force, takes almost 2h for the input provided
//        System.out.println(Day5.part2(new File("src/main/resources/input5_1.txt")));
    }
}
