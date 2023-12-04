import Solutions.Day4;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay4 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("There should be 13 points", 13, Day4.part1(new File("src/main/resources/input4_1eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("There should be 30 scratchcards", 30, Day4.part2(new File("src/main/resources/input4_1eg.txt")));
    }

}
