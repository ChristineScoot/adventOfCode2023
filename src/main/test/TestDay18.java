import Solutions.Day18;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class TestDay18 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("Should be 62", new BigInteger("62"), Day18.part1("src/main/resources/input18_1eg.txt"));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("Should be 16", new BigInteger("16"), Day18.part1("src/main/resources/input18_2eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("Should be 952408144115", new BigInteger("952408144115"), Day18.part2("src/main/resources/input18_1eg.txt"));
    }
}
