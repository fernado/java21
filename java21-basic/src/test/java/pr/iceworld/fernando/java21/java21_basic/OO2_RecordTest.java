package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OO2_RecordTest {

    record HoldTheParty(String name) {
    }

    @Test
    void records() throws Exception {
        var holdTheParty = new HoldTheParty("John");
        Assertions.assertEquals(holdTheParty.name(), "John");
        System.out.println(holdTheParty);
    }
}