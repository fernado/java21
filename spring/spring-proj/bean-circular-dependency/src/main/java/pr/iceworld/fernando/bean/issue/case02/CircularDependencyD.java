package pr.iceworld.fernando.bean.issue.case02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyD {


    private CircularDependencyC circularDependencyC;
    @Autowired
    public void setCircularDependencyC(CircularDependencyC circularDependencyC) {
        this.circularDependencyC = circularDependencyC;
    }

    public void doSomething() {
        System.out.println("doSomething circularDependencyA");
    }

}
