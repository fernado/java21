package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class OO6_RecordsTest {

    record User(String name, long accountNumber) {
    }

    record UserDeletedEvent(User user) {
    }

    record UserCreatedEvent(String name) {
    }

    record ShutdownEvent(Instant instant) {
    }

    @Test
    void respondToEvents() throws Exception {
        String name = "fernando";
        Assertions.assertEquals(
                respond(new UserCreatedEvent(name)), String.format("the new user with %s has been created", name)
        );
        Assertions.assertEquals(
                respond(new UserDeletedEvent(new User(name, 1))),
                String.format("the user %s has been deleted", name)
        );

        Instant instant = Instant.now();
        Assertions.assertNull(respond(new ShutdownEvent(instant)));
    }

    String respond(Object o) {
        // ① We have a special case where if we get a particular event, we want to shut down,
        // not produce a String, so we’ll use the new pattern matching support with an if statement.
        if (o instanceof ShutdownEvent(Instant instant)) {
            System.out.println(
                String.format("going to to shutdown the system at %s", instant.toEpochMilli()));
        }
        return switch (o) {
            // ② Here, we’re matching not just the type but extracting out the User user of the UserDeletedEvent.
            case UserDeletedEvent(var user) -> String.format("the user %s has been deleted", user.name);
            // ③ Here, we’re matching not just the type, but we’re extracting out the String name of the UserCreatedEvent.
            case UserCreatedEvent(var name) -> String.format("the new user with %s has been created", name);
            default -> null;
        };
    }

}