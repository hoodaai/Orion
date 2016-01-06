package factory_bean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import static_factory.Currency;

/**
 * Created by varun on 20/09/15.
 */
public class Application {


    public static void main(String a[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("factroybean-beans.xml");
        Object object = applicationContext.getBean("socialEngine-1");

        if (object instanceof SocialEngine) {
            SocialEngine socialEngine = ((SocialEngine) object);
            socialEngine.sayHello();
        }

    }
}
