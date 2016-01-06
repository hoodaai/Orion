package com.events.framework;

/**
 * This interface has methods required to handle an event
 */
public interface EventHandler {
    public Class<? extends Event> eventClass();
    public abstract void handle(Event event);
}
