package pr.iceworld.fernando.demo;

import org.junit.jupiter.api.Test;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

public class TestI18n {

    @Test
    public void testI18n() {
        ResourceBundleMessageSource resourceBundleMessageSource = new ResourceBundleMessageSource();
        resourceBundleMessageSource.setBasename("i18n/message");
        resourceBundleMessageSource.setDefaultEncoding("UTF-8");
        String message = resourceBundleMessageSource.getMessage("welcome", new Object[]{"Fernando"}, Locale.CHINA);
        System.out.println(message);

        message = resourceBundleMessageSource.getMessage("welcome", new Object[]{"Fernando"}, Locale.US);
        System.out.println(message);
    }
}
