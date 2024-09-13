package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class OO16_StringTemplate {


    @Test
    void test_before21() {
        // Java (using String.format)
        String name = "Sachin P";
        String message = String.format("Welcome %s", name);
        Assertions.assertEquals(message, "Welcome Sachin P");
    }

    @Test
    void test21() {
        String name = "Sachin P";
        String message = STR."Welcome \{name}!";
        Assertions.assertEquals(message, "Welcome Sachin P!");
    }

    @Test
    void test21_variable() {
        int x = 10;
        int y = 20;
        String result = STR."\{x} + \{y} = \{x + y}";
        Assertions.assertEquals(result, "10 + 20 = 30");
    }
}
