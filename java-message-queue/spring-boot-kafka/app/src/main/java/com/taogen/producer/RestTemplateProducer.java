package com.taogen.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;

//@Component
@RequiredArgsConstructor
public class RestTemplateProducer implements CommandLineRunner {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String key, String value) {
        kafkaTemplate.send(topic, key, value)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Sent successfully");
                    } else {
                        System.err.println("Failed: " + ex.getMessage());
                    }
                });
    }

    @Override
    public void run(String... args) throws Exception {
        sendMessage("my-topic", "", "Hello" + System.currentTimeMillis());
//        while (true) {
//            sendMessage("my-topic", "key" + System.currentTimeMillis(), "Hello" + System.currentTimeMillis());
//            Thread.sleep(10);
//        }
    }
}
