package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class OO7_MathematicsTest {

    @Test
    void divisions() throws Exception {
        //<1> This first operation is one of several overloads that make division safer and more predictable
        var five = Math.divideExact(10, 2);
        Assertions.assertEquals(five, 5);
    }

    @Test
    void multiplication() throws Exception {
        var start = BigInteger.valueOf(10);
        // ② There’s new support for parallelized multiplication with BigInteger instances. Remember
        // that this is only really useful if the BigInteger has thousands of bits...
        var result = start.parallelMultiply(BigInteger.TWO);
        Assertions.assertEquals(BigInteger.valueOf(10 * 2), result);
    }
}