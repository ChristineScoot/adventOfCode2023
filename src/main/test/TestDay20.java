import Solutions.Day20;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay20 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("Should be 32000000", 32000000, Day20.part1("src/main/resources/input20_1eg.txt"));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("Should be 11687500", 11687500, Day20.part1("src/main/resources/input20_1eg2.txt"));
    }

    //    @Test
    public void part2() throws IOException {
        Assert.assertEquals("Should be 19114", 19114, Day20.part2("src/main/resources/input20_1eg.txt"));
    }
}
