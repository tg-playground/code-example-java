package com.taogen.springbootrabbitmq.publish;

import com.taogen.springbootrabbitmq.config.MyRabbitMqBinding;
import com.taogen.springbootrabbitmq.config.MyRabbitMqExchange;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author taogen
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class Runner implements CommandLineRunner {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Sending message...");
        rabbitTemplate.convertAndSend(MyRabbitMqExchange.DIRECT_EXCHANGE_NAME,
                MyRabbitMqBinding.DIRECT_ROUTING_KEY, "Hello from RabbitMQ!");
    }
}
