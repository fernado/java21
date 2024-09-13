package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

class OO3_EnhancedSwitchTest {

    // ① This is a classic implementation with the older, clunkier switch statement
    int calculateTimeOffClassic(DayOfWeek dayOfWeek) {
        var timeoff = 0;
        switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY:
                timeoff = 16;
                break;
            case SATURDAY, SUNDAY:
                timeoff = 24;
                break;
        }
        return timeoff;
    }

    // ② This is the new switch expression
    int calculateTimeOff(DayOfWeek dayOfWeek) {
        return switch (dayOfWeek) {
            case MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> 16;
            case SATURDAY, SUNDAY -> 24;
        };
    }

    @Test
    void timeoff() {
        Assertions.assertEquals(calculateTimeOffClassic(DayOfWeek.SATURDAY), calculateTimeOff(DayOfWeek.SATURDAY));
        Assertions.assertEquals(calculateTimeOff(DayOfWeek.FRIDAY), 16);
        Assertions.assertEquals(calculateTimeOff(DayOfWeek.FRIDAY), 16);
    }
}