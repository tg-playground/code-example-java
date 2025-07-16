package com.taogen.springbootrabbitmq.consume;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

/**
 * @author taogen
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class PollingConsumer {
    private final AmqpTemplate amqpTemplate;

    void consumeMessage(String queueName) {
        String message = (String) amqpTemplate.receiveAndConvert(queueName);
        if (message != null) {
            log.info("Received <" + message + ">");
        } else {
            log.info("No message received");
        }
    }
}
