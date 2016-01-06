package com.recommender.producer;


import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Objects;

/**
 * Producer implementation class. It uses Spring's RabbitTemplate to communicate with RabbitMQ
 */
public class ProducerImpl implements Producer {

    private RabbitTemplate amqpTemplate;

    public void send(String exchange, String routingKey, String message)
            throws IllegalArgumentException,
            IllegalStateException {

        if (StringUtils.isBlank(exchange)) {
            throw new IllegalArgumentException("The default exchange is empty. To use this method, "
                    + "the default exchange must exist");
        }

        if (StringUtils.isBlank(routingKey)) {
            throw new IllegalArgumentException("The default routing key is empty. To use this method, "
                    + "the default routing key must exist");
        }

        amqpTemplate.setExchange(exchange);
        amqpTemplate.setRoutingKey(routingKey);
        amqpTemplate.convertAndSend(message);
        System.out.println(" [x] Sent '" + message + "'");

    }

    public void setAmqpTemplate(RabbitTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }
}
