package circular_dependency;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring_dependency_injection.StringConverter;

/**
 * Created by varun on 19/09/15.
 */
public class Application {

    public static void main(String a[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("circular-beans.xml");
        Object object = applicationContext.getBean("creator");

        if (object instanceof Builder) {
            Builder stringConverter = ((Builder) object);
            stringConverter.sayHello();
        }

    }
}
