package com.taogen.kafka.consumer.production;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.WakeupException;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

public class ProductionConsumer {

    private final KafkaConsumer<String, String> consumer;
    private final AtomicBoolean running = new AtomicBoolean(true);

    // Track offsets manually per partition
    private final Map<TopicPartition, OffsetAndMetadata> currentOffsets =
        new ConcurrentHashMap<>();

    public ProductionConsumer(Properties props, String topic) {
        this.consumer = new KafkaConsumer<>(props);

        consumer.subscribe(
            Collections.singletonList(topic),
            new HandleRebalance()
        );
    }

    public void run() {
        try {
            while (running.get()) {

                ConsumerRecords<String, String> records =
                    consumer.poll(Duration.ofMillis(500));

                for (ConsumerRecord<String, String> record : records) {
                    process(record);

                    // Store offset after successful processing
                    TopicPartition partition =
                        new TopicPartition(record.topic(), record.partition());

                    currentOffsets.put(
                        partition,
                        new OffsetAndMetadata(record.offset() + 1)
                    );
                }

                // Commit only processed offsets
                if (!currentOffsets.isEmpty()) {
                    consumer.commitSync(currentOffsets);
                }
            }
        } catch (WakeupException e) {
            // Expected during shutdown
            if (running.get()) throw e;
        } finally {
            try {
                consumer.commitSync(currentOffsets);
            } finally {
                consumer.close();
            }
        }
    }

    private void process(ConsumerRecord<String, String> record) {
        try {
            System.out.printf("Processing: topic=%s partition=%d offset=%d value=%s%n",
                record.topic(),
                record.partition(),
                record.offset(),
                record.value());

            // Simulate business logic
            // Example: database update, REST call, etc.

        } catch (Exception e) {
            handleFailure(record, e);
        }
    }

    private void handleFailure(ConsumerRecord<String, String> record, Exception e) {
        System.err.println("Error processing record: " + record.value());

        // In production:
        // 1. Retry with backoff
        // 2. Send to DLQ
        // 3. Alert monitoring system
    }

    public void shutdown() {
        running.set(false);
        consumer.wakeup();
    }

    private class HandleRebalance implements ConsumerRebalanceListener {

        @Override
        public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
            consumer.commitSync(currentOffsets);
        }

        @Override
        public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
            // No-op
        }
    }
}
