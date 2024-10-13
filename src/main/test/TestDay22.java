import Solutions.Day22;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay22 {
    @Test
    public void part1() throws IOException {
        Assert.assertEquals("5 bricks could be safely disintegrated", 5, Day22.part1("src/main/resources/input22_1eg.txt"));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("3 bricks could be safely disintegrated", 3, Day22.part1("src/main/resources/input22_2eg.txt"));
    }

    @Test
    public void part1b() throws IOException {
        Assert.assertEquals("2 bricks could be safely disintegrated", 2, Day22.part1("src/main/resources/input22_3eg.txt"));
    }

    @Test
    public void part1c() throws IOException {
        Assert.assertEquals("1 bricks could be safely disintegrated", 1, Day22.part1("src/main/resources/input22_4eg.txt"));
    }

    @Test
    public void part1d() throws IOException {
        Assert.assertEquals("2 bricks could be safely disintegrated", 2, Day22.part1("src/main/resources/input22_5eg.txt"));
    }

    @Test
    public void part1e() throws IOException {
        Assert.assertEquals("1 bricks could be safely disintegrated", 1, Day22.part1("src/main/resources/input22_6eg.txt"));
    }
}
