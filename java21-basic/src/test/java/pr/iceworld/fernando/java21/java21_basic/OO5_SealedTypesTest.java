package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OO5_SealedTypesTest {

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
    void doLittleTest() {
        Assertions.assertEquals(makeSound(new Dog()), "woof");
        Assertions.assertEquals(makeSound(new Cat()), "meow");
    }

    // ③ We could use the new instance of check to make shorter work of working with each possible type,
    // but we get no compiler help here.
    String makeSoundClassic(Animal animal) {
        var message = (String) null;
        if (animal instanceof Dog dog) {
            message = dog.bark();
        }
        if (animal instanceof Cat cat) {
            message = cat.meow();
        }
        if (animal instanceof Bird bird) {
            message = bird.chirp();
        }
        return message;
    }

    // ④ unless we use the enhahced switch with pattern matching, as we do here.
    String makeSound(Animal animal) {
        return switch (animal) {
            case Cat cat -> cat.meow();
            case Dog dog -> dog.bark();
            case Bird bird -> bird.chirp();
        };
    }

}