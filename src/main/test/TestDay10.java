import Solutions.Day10;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay10 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("The longest path should be 8", 8, Day10.part1("src/main/resources/input10_1eg.txt"));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("The longest path should be 8", 8, Day10.part1("src/main/resources/input10_1eg2.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("The longest path should be 4", 4, Day10.part2("src/main/resources/input10_1eg3.txt"));
    }
}
