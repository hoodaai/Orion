package com.example.handlers;

import com.events.framework.AbstractEventHandler;
import com.events.framework.Event;
import com.example.events.EmailEvent;

/**
 *
 */
public class EmailEventHandler extends AbstractEventHandler <EmailEvent> {
    @Override
    public void handle(Event event) {

        if (event instanceof EmailEvent) {
            System.out.println("Processing email event");
        }

    }

    @Override
    public Class<EmailEvent> eventClass() {
        return EmailEvent.class;
    }
}
