package com.example;

import com.events.framework.AbstractEventDispatcher;
import com.events.framework.Event;
import com.events.framework.EventHandler;

/**
 *
 */
public class NotificationEventDispatcher extends AbstractEventDispatcher {

    public void processEvents(Event event) {
        EventHandler handler = determineHandler(event.getClass());
        handler.handle(event);
    }

}
