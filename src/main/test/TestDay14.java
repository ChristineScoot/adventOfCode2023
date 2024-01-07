import Solutions.Day14;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay14 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("The north load should be 136", 136, Day14.part1("src/main/resources/input14_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("The north load should be 64", 64, Day14.part2("src/main/resources/input14_1eg.txt"));
    }
}
