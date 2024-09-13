package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

class OO8_FutureTest {

    @Test
    void futureTest() throws Exception {
        try (var executor = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            var future = executor.submit(() -> "hello, world!");
            Thread.sleep(100);
            // ① This returns a state object that lets us enumerate the submitted Thread states.
            // It pairs nicely with the enhanced switch feature.
            var result = switch (future.state()) {
                case CANCELLED, FAILED -> throw new IllegalStateException("couldn't finish the work!");
                case SUCCESS -> future.resultNow();
                default -> null;
            };
            Assertions.assertEquals(result, "hello, world!");
        }
    }
}