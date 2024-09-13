package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Test;

public class OO17_SwitchTest {

    @Test
    void test() {
        Object obj = 1; // Use Object type to accommodate different types

        String result = switch (obj) {

            case Byte _, Short _, Integer _, Long _ -> "Input is a Number";
            case Float _, Double _ -> "Input is a floating-point number";
            case String _ -> "Input is a string";
            default -> "Object type not expected";
        };

        System.out.println(result);
    }

    record Point(int x, int y) {
    }

    public void print(Object o) {
        switch (o) {
            case Point(int x, int _) -> System.out.printf("The x position is : %d%n", x);  // Prints only x
            default -> System.out.println("Object type not expected");
        }
    }

    @Test
    void testUnname() {
        print(new Point(5, 0));
    }
}
