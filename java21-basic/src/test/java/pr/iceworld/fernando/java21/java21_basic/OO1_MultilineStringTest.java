package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class OO1_MultilineStringTest {

    @Test
    void multiline() throws Exception {

        var shakespeare = """
                
                This JEP extends the existing pattern-matching feature to destructure 
                the record class instances, which enables writing sophisticated data queries. 
                Moreover, this JEP adds support for nested patterns for more composable data queries.
                
                """;

        Assertions.assertNotEquals(shakespeare.charAt(0), 'T');
        shakespeare = shakespeare.stripLeading();
        Assertions.assertEquals(shakespeare.charAt(0), 'T');

        Assertions.assertNotEquals(shakespeare.charAt(shakespeare.length() - 1), '.');
        shakespeare = shakespeare.stripTrailing();
        Assertions.assertEquals(shakespeare.charAt(shakespeare.length() - 1), '.');
    }

}