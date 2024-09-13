package pr.iceworld.fernando.bean.issue.case01;

import org.springframework.stereotype.Component;

@Component("circularDependencyB")
public class CircularDependencyB {


    private CircularDependencyA circularDependencyA;
    public CircularDependencyB(CircularDependencyA circularDependencyA) {
        this.circularDependencyA = circularDependencyA;
    }

    public void doSomething() {
        System.out.println("doSomething circularDependencyB");
    }
}
