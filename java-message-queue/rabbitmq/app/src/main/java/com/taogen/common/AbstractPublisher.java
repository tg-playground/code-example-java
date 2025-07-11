package com.taogen.common;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.taogen.util.Helper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author taogen
 */
@Slf4j
public abstract class AbstractPublisher {
    protected Connection connection;
    // Reuse channels: Create a channel once per thread or once per producer/consumer and reuse it for sending or receiving messages.
    protected Channel channel;

    protected BuiltinExchangeType exchangeType;

    protected AbstractPublisher() {
        if (channel == null) {
            if (connection == null) {
                try {
                    connection = Helper.getConnection();
                } catch (IOException | TimeoutException e) {
                    throw new RuntimeException(e);
                }
            }
            log.debug("To create a RabbitMQ channel...");
            try {
                channel = connection.createChannel();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    protected void declareExchange(String exchangeName) throws IOException {
        this.channel.exchangeDeclare(exchangeName, this.exchangeType, true);
    }

    protected void declareQueue(String queueName, Map<String, Object> argument) throws IOException {
        this.channel.queueDeclare(queueName, true, false, false, argument).getQueue();
    }

    protected void declareQueues(List<String> queueNames, List<Map<String, Object>> argumentList) throws IOException {
        if (queueNames == null || queueNames.isEmpty()) {
            return;
        }
        for (int i = 0; i < queueNames.size(); i++) {
            Map<String, Object> arguments = null;
            if (argumentList != null && !argumentList.isEmpty()) {
                arguments = argumentList.get(i);
            }
            this.channel.queueDeclare(queueNames.get(i), true, false, false, arguments).getQueue();
        }
    }

    protected void bindQueueToExchange(String exchangeName, String queueName, String routingKey) throws IOException {
        this.channel.queueBind(queueName, exchangeName, routingKey);
    }

    protected void bindQueuesToExchange(String exchangeName, List<String> queues, List<String> routingKeys) throws IOException {
        if (queues == null || queues.isEmpty()) {
            return;
        }
        for (int i = 0; i < queues.size(); i++) {
            this.channel.queueBind(queues.get(i), exchangeName, routingKeys.get(i));
        }
    }

    protected void publish(String exchangeName, String routingKey, String message) throws IOException {
        log.debug("To send a message to exchange: {}, routingKey: {}", exchangeName, routingKey);
        // In a fanout exchange, the routing key is ignored.
        this.channel.basicPublish(exchangeName, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
    }
}
