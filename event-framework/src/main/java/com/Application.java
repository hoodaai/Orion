package com;

import com.events.framework.Event;
import com.example.NotificationEventDispatcher;
import com.example.events.EmailEvent;
import com.example.events.PushEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by varun on 06/10/15.
 */
public class Application {

    public static void main(String a[]) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("event-beans.xml");
        NotificationEventDispatcher object = (NotificationEventDispatcher)applicationContext.getBean("notificationEventDispatcher");

        Event e1 = new EmailEvent();
        Event e2 = new PushEvent();

        object.processEvents(e1);
        object.processEvents(e2);

    }

    public static void cal(int x) {


    }
}
