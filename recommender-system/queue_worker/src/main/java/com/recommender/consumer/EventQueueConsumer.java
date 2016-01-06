package com.recommender.consumer;

import com.google.gson.Gson;
import com.recommender.event.impl.ProductEvent;
import com.recommender.eventhandler.EventHandler;
import com.recommender.eventhandler.impl.ProductEventHandler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *

 */
public class EventQueueConsumer {

    @Autowired
    public ProductEventHandler productEventHandler;

    public void listen(String message) {
        Gson gson = new Gson();
        ProductEvent productEvent = gson.fromJson(message, ProductEvent.class);

        // calling event handler
        try {
            productEventHandler.handle(productEvent);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void setProductEventHandler(ProductEventHandler productEventHandler) {
        this.productEventHandler = productEventHandler;
    }
}
