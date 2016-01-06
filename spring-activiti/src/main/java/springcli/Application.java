package springcli;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */


public class Application {


    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
                "spring-beans.xml");

        RepositoryService repositoryService =
                (RepositoryService) applicationContext.getBean("repositoryService");

        String deploymentId = repositoryService
                .createDeployment()
                .addClasspathResource("hello.bpmn20.xml")
                .deploy()
                .getId();
        System.out.println("process deployed"+deploymentId);


        RuntimeService runtimeService = (RuntimeService) applicationContext.getBean("runtimeService");
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("myProcess");



    }
}
