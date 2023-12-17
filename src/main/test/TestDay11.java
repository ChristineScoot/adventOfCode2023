import Solutions.Day11;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class TestDay11 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("The sum of shortest paths should be 374", 374, Day11.part1("src/main/resources/input11_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("The sum of shortest paths should be 1030", new BigInteger("1030"), Day11.part2("src/main/resources/input11_1eg.txt", 10));
    }

    @Test
    public void part2b() throws IOException {
        Assert.assertEquals("The sum of shortest paths should be 22", new BigInteger("22"), Day11.part2("src/main/resources/input11_2eg.txt", 10));
    }

    @Test
    public void part2a() throws IOException {
        Assert.assertEquals("The sum of shortest paths should be 8410", new BigInteger("8410"), Day11.part2("src/main/resources/input11_1eg.txt", 100));
    }
}
