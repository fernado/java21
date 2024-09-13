package pr.iceworld.fernando.bean.issue.case01;

import org.springframework.stereotype.Component;

@Component("circularDependencyA")
public class CircularDependencyA {


    private CircularDependencyB circularDependencyB;
    public CircularDependencyA(CircularDependencyB circularDependencyB) {
        this.circularDependencyB = circularDependencyB;
    }

    public void doSomething() {
        System.out.println("doSomething circularDependencyA");
    }

}
