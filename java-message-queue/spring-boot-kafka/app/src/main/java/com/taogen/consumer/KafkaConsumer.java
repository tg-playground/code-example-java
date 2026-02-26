package com.taogen.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(
            topics = "my-topic",
            groupId = "my-group"
    )
    public void consume(String in, Acknowledgment ack) {
        try {
            System.out.println("Received: " + in);
            ack.acknowledge(); // manual ack after successful processing
        } catch (Exception e) {
            // do not ack → will retry
        }
    }

}
