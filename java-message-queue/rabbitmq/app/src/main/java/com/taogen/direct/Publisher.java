package com.taogen.direct;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.taogen.util.Helper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author taogen
 */
@Slf4j
public class Publisher {
    private static Connection connection;
    // Reuse channels: Create a channel once per thread or once per producer/consumer and reuse it for sending or receiving messages.
    private static Channel channel;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        while (true) {
            // publish a message per second
            String message = "hello, world!" + new Date();
            publish("taxi_direct", "taxi.1", "taxi.1", message);
            Thread.sleep(1000);
        }
    }

    private static void publish(String exchangeName, String queueName,
                                String routingKey, String message) throws IOException, TimeoutException, InterruptedException {
        // Create a connection or channel if it's null.
        if (channel == null) {
            if (connection == null) {
                connection = Helper.getConnection();
            }
            log.debug("To create a RabbitMQ channel...");
            channel = connection.createChannel();
        }
        log.debug("To send a message...");
        // This is a common pattern with AMQP when there is no strong happens-before relationship between events.
        // Re-declaration is the way to go. Conversely, the check-then-act pattern is discouraged;
        // trying to check the pre-existence of an exchange or a queue does not guarantee success in the typical distributed environment where AMQP is used.
        // Declare the exchange on every message sent.
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true);
        // Declare the queue on every message sent. It will not do anything if the queue already exists.
        String queue = channel.queueDeclare(queueName, true, false, false, null).getQueue();
        channel.queueBind(queue, exchangeName, routingKey);
        // You send messages to an exchange, which uses the routing key and bindings to determine which queue(s) to deliver the message to.
        // 1. Routing logic in exchanges:
        // - Routing keys are used by exchanges (like direct, topic, headers) to decide which queues get the message.
        // - They’re like labels or tags on the message that guide how it’s routed.
        // 2. Multiple queues can be bound to an exchange:
        // - A single exchange can deliver to many queues, each with different routing key bindings.
        // - The routing key helps target specific queues from among many options.
        // Analogy: Think of RabbitMQ as a post office.
        // - Exchange = Post office
        // - Routing key = Address on the letter
        // - Queue = Mailbox
        // You don’t drop the letter directly into a mailbox — you hand it to the post office (exchange), and the address (routing key) determines where it goes (which queue).
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
    }
}
