import Solutions.Day17;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay17 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("There should be 102 energized tiles", 102, Day17.part1("src/main/resources/input17_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("There should be 94 energized tiles", 94, Day17.part2("src/main/resources/input17_1eg.txt"));
    }
}
