package com.example.handlers;

import com.events.framework.AbstractEventHandler;
import com.events.framework.Event;
import com.events.framework.EventHandler;
import com.example.events.EmailEvent;
import com.example.events.PushEvent;

/**
 * Created by varun on 06/10/15.
 */
public class PushEventHandler extends AbstractEventHandler<PushEvent> {

    @Override
    public void handle(Event event) {
        if (event instanceof PushEvent) {
            System.out.println("Processing push event");
        }
    }

    @Override
    public Class<PushEvent> eventClass() {
        return PushEvent.class;
    }
}
