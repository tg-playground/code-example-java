package com.taogen.springbootrabbitmq.publish;

import com.taogen.springbootrabbitmq.config.RabbitMqBinding;
import com.taogen.springbootrabbitmq.config.RabbitMqExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

/**
 * @author taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BasicPublisherTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void directExchangeTest() {
        String exchange = RabbitMqExchange.DIRECT_EXCHANGE_NAME;
        String routingKey = RabbitMqBinding.DIRECT_ROUTING_KEY;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange + " with routing key: " + routingKey);
        }
    }

    @Test
    void topicExchangeTest() {
        String exchange = RabbitMqExchange.TOPIC_EXCHANGE_NAME;
        String routingKey = RabbitMqBinding.TOPIC_ROUTING_KEY;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange + " with routing key: " + routingKey);
        }
    }

    @Test
    void fanoutExchangeTest() throws InterruptedException {
        String exchange = RabbitMqExchange.FANOUT_EXCHANGE_NAME;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, "", message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange);
        }
    }
}
