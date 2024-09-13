package pr.iceworld.fernando.bean.issue.case02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CircularDependencyC {


    private CircularDependencyD circularDependencyD;
    @Autowired
    public void setCircularDependencyD(CircularDependencyD circularDependencyD) {
        this.circularDependencyD = circularDependencyD;
    }

    public void doSomething() {
        System.out.println("doSomething circularDependencyC");
    }

}
