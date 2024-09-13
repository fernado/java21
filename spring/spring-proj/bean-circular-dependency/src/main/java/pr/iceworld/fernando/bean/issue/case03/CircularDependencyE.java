package pr.iceworld.fernando.bean.issue.case03;

import jakarta.annotation.Resource;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import pr.iceworld.fernando.bean.issue.case02.CircularDependencyD;

@Component("circularDependencyE")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CircularDependencyE {

    @Resource
    private CircularDependencyF circularDependencyF;


    public void doSomething() {
        System.out.println("doSomething circularDependencyE");
    }

}
