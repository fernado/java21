package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Test;

public class OO4_InstanceofTest {

    // ① We have an explicitly sealed interactive that only permits three types.
    // The enhanced switch expression below will fail if we add a new class.
    sealed interface Animal permits Bird, Cat, Dog {
    }

    // ② Classes that implement that sealed interface must either be declared sealed
    // and thus declare which classes it permits as subclasses, or it must be declared as final.
    final class Cat implements Animal {
        String meow() {
            return "meow";
        }
    }

    final class Dog implements Animal {
        String bark() {
            return "woof";
        }
    }

    final class Bird implements Animal {
        String chirp() {
            return "chirp";
        }
    }

    @Test
    void testInstanceofClassic() {
        var animal = (Object) new Dog();
        if (animal instanceof Dog) {
            var fido = (Dog) animal;
            fido.bark();
        }
    }

    @Test
    void testInstanceof() {
        var animal = (Object) new Dog();
        if (animal instanceof Dog fido) {
            fido.bark();
        }
    }
}
