import Solutions.Day9;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class TestDay9 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("Extrapolated sum should be 114", 114, Day9.part1(new File("src/main/resources/input9_1eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("Extrapolated sum should be 2", 2, Day9.part2(new File("src/main/resources/input9_1eg.txt")));
    }
}
