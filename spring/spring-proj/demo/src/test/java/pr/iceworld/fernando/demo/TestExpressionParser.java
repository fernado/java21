package pr.iceworld.fernando.demo;

import org.junit.jupiter.api.Test;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

public class TestExpressionParser {

    @Test
    public void testExpressionParser() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello World'.length");
        System.out.println(expression.getValue());


        expression = parser.parseExpression("T(java.lang.Math).random() * 10");
        System.out.println(expression.getValue());

        expression = parser.parseExpression("3.1415E+23");
        System.out.println(expression.getValue());


        expression = parser.parseExpression("null");
        System.out.println(expression.getValue());


        expression = parser.parseExpression("2 <= 5");
        System.out.println(expression.getValue());
    }
}
