package pr.iceworld.fernando.bean.issue.case01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 构造器的循环依赖（构造器注入）
 */
public class MainCircularDependency {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjConfig.class);

        CircularDependencyA circularDependencyA = context.getBean("circularDependencyA", CircularDependencyA.class);
        CircularDependencyB circularDependencyB = context.getBean("circularDependencyB", CircularDependencyB.class);


        circularDependencyA.doSomething();
    }
}
