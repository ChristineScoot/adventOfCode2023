import Solutions.Day8;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay8 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("It takes 6 steps", 6, Day8.part1(new File("src/main/resources/input8_1eg.txt")));
    }

    @Test
    public void part1_2() throws FileNotFoundException {
        Assert.assertEquals("It takes 2 steps", 2, Day8.part1(new File("src/main/resources/input8_2eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("It takes 6 steps", 6, Day8.part2(new File("src/main/resources/input8_3eg.txt")));
    }
}
