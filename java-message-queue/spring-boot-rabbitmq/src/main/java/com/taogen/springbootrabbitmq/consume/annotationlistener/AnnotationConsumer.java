package com.taogen.springbootrabbitmq.consume.annotationlistener;

import com.taogen.springbootrabbitmq.config.MyRabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * @author taogen
 */
//@Component
@Slf4j
public class AnnotationConsumer {
    @RabbitListener(queues = MyRabbitMqQueue.QUEUE_3_NAME)
    public void receiveMessage(String message) {
        // Process the received message
        log.info("Received message from {}: {}", MyRabbitMqQueue.QUEUE_3_NAME, message);
    }
}
