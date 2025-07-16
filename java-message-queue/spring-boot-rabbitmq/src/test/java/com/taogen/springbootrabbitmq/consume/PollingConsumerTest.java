package com.taogen.springbootrabbitmq.consume;

import com.taogen.springbootrabbitmq.config.MyRabbitMqQueue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PollingConsumerTest {

    @Autowired
    private PollingConsumer consumer;

    @Test
    void consumeMessage() {
        consumer.consumeMessage(MyRabbitMqQueue.QUEUE_2_NAME);
    }
}
