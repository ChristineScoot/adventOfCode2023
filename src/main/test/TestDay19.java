import Solutions.Day19;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigInteger;

public class TestDay19 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("Should be 19114", new BigInteger("19114"), new Day19().part1("src/main/resources/input19_1eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("Should be 167409079868000", new BigInteger("167409079868000"), new Day19().part2("src/main/resources/input19_1eg.txt"));
    }
}
