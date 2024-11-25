package pr.iceworld.fernando.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pr.iceworld.fernando.demo.model.config.ProjConfig;

public class PostProcessorTest {

    @Test
    public void testPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjConfig.class);

        context.getBean("user");

    }
}
