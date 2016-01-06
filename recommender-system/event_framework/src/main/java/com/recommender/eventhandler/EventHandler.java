package com.recommender.eventhandler;

import com.recommender.event.Event;

/**
 *
 */
public interface EventHandler {

    /**
     * Perform whatever action is necessary to process the provided event.
     * @param event -
     * @throws Exception any exception is valid here - it's up to "onFailure" to
     * interpret what they mean.
     */
    public void handle(Event event) throws Exception;


}
