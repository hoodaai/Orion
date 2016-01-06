package com.recommender.common;


import com.recommender.event.Event;
import com.recommender.producer.ProducerImpl;

public class EventService {

    ProducerImpl rabbitMQProducer;

    public void pushEvents(String event) {
        rabbitMQProducer.send("recommenderExchange", "recommender.events", event);

    }

    public void setRabbitMQProducer(ProducerImpl rabbitMQProducer) {
        this.rabbitMQProducer = rabbitMQProducer;
    }
}
