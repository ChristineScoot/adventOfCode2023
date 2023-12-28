import Solutions.Day12;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class TestDay12 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 21", BigInteger.valueOf(21), Day12.part1("src/main/resources/input12_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 525152", BigInteger.valueOf(525152), Day12.part2("src/main/resources/input12_1eg.txt"));
    }
}
