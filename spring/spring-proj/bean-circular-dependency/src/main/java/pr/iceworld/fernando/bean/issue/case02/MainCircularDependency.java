package pr.iceworld.fernando.bean.issue.case02;

import org.springframework.beans.BeanInstantiationException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Field 属性的循环依赖（setter 注入）
 * 对于 singleton Bean，Spring 会通过三级缓存机制解决这个循环依赖问题，因此这个例子不会抛出异常。
 */
public class MainCircularDependency {

    public static void main(String[] args) {
//        validate();


        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProjConfig.class);

        CircularDependencyC circularDependencyC = context.getBean("circularDependencyC", CircularDependencyC.class);
        CircularDependencyD circularDependencyD = context.getBean("circularDependencyD", CircularDependencyD.class);


        circularDependencyC.doSomething();
    }


    public static void createAObjByReflection() {
        Constructor<CircularDependencyC> ctor = null;
        Class<CircularDependencyC> clazz = CircularDependencyC.class;
        try {
            ctor = clazz.getDeclaredConstructor();
        }
        catch (NoSuchMethodException ex) {
               if (ctor == null) {
                throw new BeanInstantiationException(clazz, "No default constructor found", ex);
            }
        }
        catch (LinkageError err) {
            throw new BeanInstantiationException(clazz, "Unresolvable class definition", err);
        }

        CircularDependencyC circularDependencyC = null;
        try {
            circularDependencyC = ctor.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        circularDependencyC.doSomething();
    }
}
