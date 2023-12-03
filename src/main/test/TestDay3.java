import Solutions.Day3;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay3 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("Sum should be 4361", 4361, Day3.part1(new File("src/main/resources/input3_1eg.txt")));
    }

//    @Test
//    public void part2() throws FileNotFoundException {
//        Assert.assertEquals("Sum should be 467835", 467835, Day3.part2(new File("src/main/resources/input3_1eg.txt")));
//    }

}
