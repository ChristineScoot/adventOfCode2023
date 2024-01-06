import Solutions.Day13;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay13 {

    @Test
    public void part1() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 405", 405, Day13.part1("src/main/resources/input13_1eg.txt"));
    }

    @Test
    public void part1a() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 0", 0, Day13.part1("src/main/resources/input13_2eg.txt"));
    }

    @Test
    public void part2() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 400", 400, Day13.part2("src/main/resources/input13_1eg.txt"));
    }

    @Test
    public void part2a() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 200", 200, Day13.part2("src/main/resources/input13_3eg.txt"));
    }

    @Test
    public void part2b() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 200", 200, Day13.part2("src/main/resources/input13_4eg.txt"));
    }

    @Test
    public void part2c() throws IOException {
        Assert.assertEquals("The sum of the possible arrangements should be 100", 100, Day13.part2("src/main/resources/input13_5eg.txt"));
    }
}
