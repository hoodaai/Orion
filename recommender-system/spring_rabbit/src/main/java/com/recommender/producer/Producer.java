package com.recommender.producer;

/**
 * Interface for Producer. It will contain all queue related methods
 */
public interface Producer {
    /**
     * This method is used to submit messages to rabbitmq.
     *
     * @param exchange
     *           - {@link String} representing exchange name of rabbitmq.
     * @param routingKey
     *           - {@link String} representing routing key for the message.
     * @param message
     *           - {@link String} message to be posted to rabbit mq.
     * @throws IllegalArgumentException
     *            -If the exchange is not specified OR if the routingKey is not
     *            specified
     * @throws IllegalStateException
     *            - If producer is not enabled to send messages.
     */
    public void send(String exchange, String routingKey, String message)
            throws IllegalArgumentException, IllegalStateException;
}

