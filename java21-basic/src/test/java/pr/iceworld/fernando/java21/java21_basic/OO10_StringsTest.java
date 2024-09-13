package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OO10_StringsTest {

    @Test
    void repeat() throws Exception {
        // ① This first example demonstrates using the StringBuilder
        // to repeat a String (can we all collectively get rid of our various StringUtils, yet?)
        var line = new StringBuilder()
                .repeat("-", 10)
                .toString();
        Assertions.assertEquals("----------", line);
    }

    @Test
    void emojis() throws Exception {
        // ② This second example demonstrates detecting an emoji in a String.
        var shockedFaceEmoji = "\uD83E\uDD2F";
        var cp = Character.codePointAt(shockedFaceEmoji.toCharArray(), 0);
        Assertions.assertTrue(Character.isEmoji(cp));
        System.out.println(shockedFaceEmoji);
    }
}