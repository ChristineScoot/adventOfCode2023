import Solutions.Day5;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;

public class TestDay5 {

    @Test
    public void part1() throws FileNotFoundException {
        Assert.assertEquals("Lowest location should be 35", new BigInteger("35"), Day5.part1(new File("src/main/resources/input5_1eg.txt")));
    }

    @Test
    public void part2() throws FileNotFoundException {
        Assert.assertEquals("Lowest location should be 46", new BigInteger("46"), Day5.part2(new File("src/main/resources/input5_1eg.txt")));
    }
//    @Test
//    public void part2_input() throws FileNotFoundException {
//        Assert.assertEquals("Lowest location should be 37384986", new BigInteger("37384986"), Day5.part2(new File("src/main/resources/input5_1.txt")));
//    }
}
