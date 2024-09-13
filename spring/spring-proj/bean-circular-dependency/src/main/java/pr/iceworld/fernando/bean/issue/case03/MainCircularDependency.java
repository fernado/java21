package pr.iceworld.fernando.bean.issue.case03;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Prototype 原型 Bean 循环依赖
 * 当 Bean 作用域为 prototype 时，每次请求会创建新的实例。如果两个 prototype Bean 相互依赖，那么会出现循环依赖，Spring 无法解决这个问题，
 * 因为 prototype Bean 不会像 singleton Bean 那样通过三级缓存来解决循环依赖。
 */
public class MainCircularDependency {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjConfig.class);

        CircularDependencyE circularDependencyE = context.getBean("circularDependencyE", CircularDependencyE.class);
        CircularDependencyF circularDependencyF = context.getBean("circularDependencyF", CircularDependencyF.class);


        circularDependencyE.doSomething();
        circularDependencyF.doSomething();
    }
}
