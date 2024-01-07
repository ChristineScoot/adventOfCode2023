import Solutions.Day15;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay15 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("The sum should be 52", 52, Day15.part1("src/main/resources/input15_1eg.txt"));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("The sum should be 1320", 1320, Day15.part1("src/main/resources/input15_1eg2.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("The sum should be 145", 145, Day15.part2("src/main/resources/input15_1eg.txt"));
    }
}
