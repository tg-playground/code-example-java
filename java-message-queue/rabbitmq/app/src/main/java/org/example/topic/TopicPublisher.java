package org.example.topic;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;
import org.example.util.Helper;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author taogen
 */
@Slf4j
public class TopicPublisher {
    private static Connection connection;
    // Reuse channels: Create a channel once per thread or once per producer/consumer and reuse it for sending or receiving messages.
    private static Channel channel;

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        while (true) {
            // publish a topic message per second
            String message = "hello, world!" + new Date();
            publish("taxi_topic", "taxi.*", message);
            Thread.sleep(1000);
        }
    }

    private static void publish(String exchangeName,
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
        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.TOPIC, true);
        String queue1 = channel.queueDeclare("taxi.1", true, false, false, null).getQueue();
        channel.queueBind(queue1, exchangeName, "taxi.*");
        String queue2 = channel.queueDeclare("taxi.2", true, false, false, null).getQueue();
        channel.queueBind(queue2, exchangeName, "taxi.*");
        // routingKey: taxi.*, taxi.1, taxi.2 all of keys match the routingKey in queueBind
        channel.basicPublish(exchangeName, routingKey, null, message.getBytes(StandardCharsets.UTF_8));
    }
}
