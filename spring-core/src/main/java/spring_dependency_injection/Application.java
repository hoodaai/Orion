package spring_dependency_injection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * main class
 */
public class Application {

    public static void main(String a[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("dependency-injection-beans.xml", "circular-beans.xml");
        Object object = applicationContext.getBean("stringConverter");

        if (object instanceof StringConverter) {
            StringConverter stringConverter = ((StringConverter) object);
           // stringConverter.setString("convert me to uppercase");
            stringConverter.converter();
        }

    }
}
