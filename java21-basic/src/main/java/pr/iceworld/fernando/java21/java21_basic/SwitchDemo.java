package pr.iceworld.fernando.java21.java21_basic;

public class SwitchDemo {

    void main(String[] args) {
        switch2();
    }

    public void print(Object o) {

        switch (o) {

            case Point(int x, int y) 		-> System.out.printf("o is a position: %d/%d%n", x, y);
            case String s               -> System.out.printf("o is a string: %s%n", s);
            default                     -> System.out.printf("o is something else: %s%n", o);
        }
    }

    public void switch2() {
        Object obj = 1; // Use Object type to accommodate different types

        String result = switch (obj) {

            case Byte _, Short _, Integer _, Long _ -> "Input is a Number ";
            case Float _, Double _ -> "Input is a floating-point number";
            case String _ -> "Input is a string";
            default -> "Object type not expected";
        };

        System.out.println(result);
    }
}
