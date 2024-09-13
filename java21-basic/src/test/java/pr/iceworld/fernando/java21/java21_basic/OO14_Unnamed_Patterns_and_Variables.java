package pr.iceworld.fernando.java21.java21_basic;

import org.junit.jupiter.api.Test;

public class OO14_Unnamed_Patterns_and_Variables {

    @Test
    void test_before21() {
        String userInput = "User Input";
        try {
            int number = Integer.parseInt(userInput);
            // Use 'number'
        } catch (NumberFormatException ex) {
            System.out.println("Invalid input: " + userInput);
        }
    }

    @Test
    void test21() {
        String userInput = "User Input";
        try {
            int number = Integer.parseInt(userInput);
            // Use 'number'
        } catch (NumberFormatException _) {
            System.out.println("Invalid input: " + userInput);
        }
    }
}
