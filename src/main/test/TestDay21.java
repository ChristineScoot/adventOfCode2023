import Solutions.Day21;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class TestDay21 {
    @Test
    public void part1() throws IOException {
        Assert.assertEquals("Should be 16 garden plots", 16, Day21.part1("src/main/resources/input21_1eg.txt", 6));
    }
}
