package static_factory;

import circular_dependency.Builder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by varun on 20/09/15.
 */
public class Application {

    public static void main(String a[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("staticfactory-beans.xml");
        Object object = applicationContext.getBean("ukCurrency");

        if (object instanceof Currency) {
            Currency currency = ((Currency) object);
            currency.sayHello();
        }

    }
}
