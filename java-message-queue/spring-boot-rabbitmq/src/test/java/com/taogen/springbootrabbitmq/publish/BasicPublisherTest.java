package com.taogen.springbootrabbitmq.publish;

import com.taogen.springbootrabbitmq.config.MyRabbitMqBinding;
import com.taogen.springbootrabbitmq.config.MyRabbitMqExchange;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.PooledChannelConnectionFactory;
import org.springframework.amqp.rabbit.connection.ThreadChannelConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author taogen
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class BasicPublisherTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    ConnectionFactory connectionFactory;

    @Test
    void testConnection() {
        assertTrue(connectionFactory instanceof CachingConnectionFactory);
        assertFalse(connectionFactory instanceof PooledChannelConnectionFactory);
        assertFalse(connectionFactory instanceof ThreadChannelConnectionFactory);
    }

    @Test
    void directExchangeTest() {
        String exchange = MyRabbitMqExchange.DIRECT_EXCHANGE_NAME;
        String routingKey = MyRabbitMqBinding.DIRECT_ROUTING_KEY;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange + " with routing key: " + routingKey);
        }
    }

    @Test
    void topicExchangeTest() {
        String exchange = MyRabbitMqExchange.TOPIC_EXCHANGE_NAME;
        String routingKey = MyRabbitMqBinding.TOPIC_ROUTING_KEY;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, routingKey, message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange + " with routing key: " + routingKey);
        }
    }

    @Test
    void fanoutExchangeTest() {
        String exchange = MyRabbitMqExchange.FANOUT_EXCHANGE_NAME;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, "", message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange);
        }
    }
}
