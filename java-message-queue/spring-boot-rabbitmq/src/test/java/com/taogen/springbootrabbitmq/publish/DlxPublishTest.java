package com.taogen.springbootrabbitmq.publish;

import com.taogen.springbootrabbitmq.config.MyRabbitMqExchange;
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
class DlxPublishTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    void dlxExchangeTest() {
        String exchange = MyRabbitMqExchange.TTL_EXCHANGE;
        for (int i = 0; i < 10; i++) {
            String message = "hello " + UUID.randomUUID();
            rabbitTemplate.convertAndSend(exchange, "", message);
            System.out.println("Sent message: " + message + " to exchange: " + exchange);
        }
    }
}
