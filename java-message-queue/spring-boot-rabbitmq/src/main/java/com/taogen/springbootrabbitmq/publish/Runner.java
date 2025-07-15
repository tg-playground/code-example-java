package com.taogen.springbootrabbitmq.publish;

import com.taogen.springbootrabbitmq.config.RabbitMqBinding;
import com.taogen.springbootrabbitmq.config.RabbitMqExchange;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author taogen
 */
@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending message...");
        rabbitTemplate.convertAndSend(RabbitMqExchange.DIRECT_EXCHANGE_NAME,
                RabbitMqBinding.DIRECT_ROUTING_KEY, "Hello from RabbitMQ!");
    }
}
