package com.recommender.eventhandler.impl;

import com.recommender.common.Constants;
import com.recommender.event.Event;
import com.recommender.event.impl.ProductEvent;
import com.recommender.common.memeroydb.MemoryDB;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ProductEventHandler extends AbstractEventHandler<ProductEvent> {

    @Override
    public void handle(Event event) throws Exception {
        ProductEvent productEvent = null;
        if (event instanceof ProductEvent)
            productEvent = (ProductEvent)event;
        System.out.println(event);
        // check if product exist in DB
        if(MemoryDB.database.get(productEvent.getProductId())!=null){
            // check if product along with user's product rating exist in DB
            Map userProductRatingMap = MemoryDB.database.get(productEvent.getProductId());

            if(userProductRatingMap.get(productEvent.getUserId()) != null){
                //get ratings from event type
                Integer ratings = (Integer) userProductRatingMap.get(productEvent.getUserId());
                if(ratings == 1) {
                    if(productEvent.getEventName().equalsIgnoreCase(Constants.Events.BOUGHT.getVal())){
                        ratings =  2;
                    }
                }
                // update product in DB
                userProductRatingMap.put(productEvent.getUserId(), ratings);
                MemoryDB.database.put(productEvent.getProductId(), userProductRatingMap);
            } else {
                userProductRatingMap.put(productEvent.getUserId(), calculateRatings(productEvent));
                MemoryDB.database.put(productEvent.getProductId(), userProductRatingMap);
            }

        }else{
            // if product not in DB then put new
            Map userProductRatingMap = new HashMap<String, String>();
            userProductRatingMap.put(productEvent.getUserId(), calculateRatings(productEvent));
            MemoryDB.database.put(productEvent.getProductId(), userProductRatingMap);
        }

    // Save or update data in database for the purpose of model training.

        //printing DB
        for (Map.Entry<String, Map<String, Integer>> entry: MemoryDB.database.entrySet()) {
            System.out.println(entry.getKey());
            for(Map.Entry<String, Integer> pmap: entry.getValue().entrySet()){
                System.out.println(pmap.getKey() + " : " + pmap.getValue());
            }
        }
    }

    private Integer calculateRatings(ProductEvent productEvent) {
        Integer score;
        if(productEvent.getEventName().equalsIgnoreCase(Constants.Events.BOUGHT.getVal())){
            score =  2;
        } else {
            score = 1;
        }
        return score;
    }
}
