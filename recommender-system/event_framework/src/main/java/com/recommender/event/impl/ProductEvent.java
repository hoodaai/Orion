package com.recommender.event.impl;

/**
 *
 */
public class ProductEvent extends AbstractEvent {

    private String eventName;
    private String userId;
    private String productId;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        return "ProductEvent{" +
                "eventName='" + eventName + '\'' +
                ", userId='" + userId + '\'' +
                ", productId='" + productId + '\'' +
                '}';
    }

    @Override
    public long getId() {
        return 0;
    }
}
