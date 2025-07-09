package com.taogen.direct;

import com.rabbitmq.client.*;
import com.taogen.util.Helper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author taogen
 */
@Slf4j
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        consume("taxi.1");
    }

    private static void consume(String queueName) throws IOException, TimeoutException {
        Connection connection = Helper.getConnection();
        Channel channel = connection.createChannel();
        channel.basicConsume(queueName, false, "myConsumerTag", new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                // process the message before acknowledge
                log.info("Received message: {}", new String(body));
                channel.basicAck(deliveryTag, false);
            }
        });
    }

}
