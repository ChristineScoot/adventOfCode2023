import Solutions.Day2;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay2 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("Should be 8 games", 8, Day2.part1(new File("src/main/resources/input2_1eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("Should be 2286 games", 2286, Day2.part2(new File("src/main/resources/input2_1eg.txt")));
    }
}
