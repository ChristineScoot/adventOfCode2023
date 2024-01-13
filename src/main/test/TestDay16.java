import Solutions.Day16;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay16 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("There should be 46 energized tiles", 46, Day16.part1("src/main/resources/input16_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("There should be 51 energized tiles", 51, Day16.part2("src/main/resources/input16_1eg.txt"));
    }
}
