import Solutions.Day23;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay23 {
    @Test
    public void part1() throws IOException {
        Assert.assertEquals("This hike contains 94 steps.", 94, Day23.part1("src/main/resources/input23_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("This hike contains 154 steps.", 154, Day23.part2("src/main/resources/input23_1eg.txt"));
    }
}
