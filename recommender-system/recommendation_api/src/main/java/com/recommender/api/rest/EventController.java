package com.recommender.api.rest;

import com.google.gson.Gson;
import com.recommender.common.Constants;
import com.recommender.common.EventService;
import com.recommender.event.impl.ProductEvent;
import com.recommender.objects.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Captures user events i.e. buy and browse.
 */
@RestController
public class EventController {

    @Autowired
    public EventService eventService;
    /**
     * creates event and push into queue.
     * @param eventName name of the event
     * @param userId
     * @param productId
     * @return Response
     */
    @RequestMapping(value="/events/{eventName}", method= RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public Map pushEvent(@PathVariable String eventName,
                         @RequestParam String userId,
                         @RequestParam String productId) {

        Map response = null;
        try {
            ProductEvent productEvent = new ProductEvent();
            if(eventName.equalsIgnoreCase(Constants.Events.BOUGHT.getVal())){
                productEvent.setEventName(Constants.Events.BOUGHT.getVal());
            } else if (eventName.equalsIgnoreCase(Constants.Events.BROWSED.getVal())){
                productEvent.setEventName(Constants.Events.BROWSED.getVal());
            } else {
                response = Response.createResponseMap("false", "500");
                return response;
            }
            productEvent.setProductId(productId);
            productEvent.setUserId(userId);
            // push event into queue.
            Gson gson = new Gson();
            String json = gson.toJson(productEvent);
            eventService.pushEvents(json);
            response = Response.createResponseMap("true", "200");
        } catch (Exception e){
            response = Response.createResponseMap("false", "500");
        }


        return response;
    }
}
