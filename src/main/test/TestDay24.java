import Solutions.Day24;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

public class TestDay24 {
    @Test
    public void part1() throws IOException {
        Assert.assertEquals("2 hailstones' future paths cross inside the boundaries.", 2, Day24.part1("src/main/resources/input24_1eg.txt", new BigDecimal("7"), new BigDecimal("27")));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("1 hailstones' future paths cross inside the boundaries.", 1, Day24.part1("src/main/resources/input24_2eg.txt", new BigDecimal("0"), new BigDecimal("5")));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("Rock's position should add up 47.", 47, Day24.part2("src/main/resources/input24_1eg.txt"));
    }
}
