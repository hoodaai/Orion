package com.events.framework;

import org.springframework.beans.factory.annotation.Required;

import java.util.List;

/**
 * Created by varun on 07/10/15.
 */
public abstract class AbstractEventDispatcher {

    protected List<EventHandler> handlers;

    public EventHandler determineHandler(Class<? extends Event> eventClass) {
        for (EventHandler eventHandler : handlers) {
            if (eventHandler.eventClass().isAssignableFrom(eventClass)) {
                return eventHandler;
            }

        }
        return null;
    }

    @Required
    public void setHandlers(List<EventHandler> handlers) {
        this.handlers = handlers;
    }
}
