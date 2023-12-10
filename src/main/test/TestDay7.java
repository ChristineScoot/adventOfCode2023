import Solutions.Day7;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay7 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("Total winning should be 6440", 6440, Day7.part1(new File("src/main/resources/input7_1eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("Total winning should be 5905", 5905, Day7.part2(new File("src/main/resources/input7_1eg.txt")));
    }
}
