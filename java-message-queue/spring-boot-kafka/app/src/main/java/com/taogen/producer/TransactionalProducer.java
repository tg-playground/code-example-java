package com.taogen.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class TransactionalProducer implements CommandLineRunner {
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Transactional
    public void sendInTransaction(String msg) {
        kafkaTemplate.executeInTransaction(operations -> {
            operations.send("my-topic", msg);
            int i = 1 / 0; // simulate failure
            operations.send("my-topic-2", msg);
            return true;
        });
    }

    public void sendNoTransaction(String msg) {
        kafkaTemplate.send("my-topic", "", msg);
        int i = 1 / 0; // simulate failure
        kafkaTemplate.send("my-topic-2", "", msg);
    }

    @Override
    public void run(String... args) throws Exception {
        try {
            sendInTransaction("Transactional message " + System.currentTimeMillis());
        } catch (Exception e) {
            System.err.println("Transaction failed: " + e.getMessage());
        }
    }
}
