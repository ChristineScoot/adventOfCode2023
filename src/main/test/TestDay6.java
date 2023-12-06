import Solutions.Day6;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay6 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("Lowest location should be 288", 288, Day6.part1(new File("src/main/resources/input6_1eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("Lowest location should be 71503", 71503, Day6.part2(new File("src/main/resources/input6_1eg.txt")));
    }
}
