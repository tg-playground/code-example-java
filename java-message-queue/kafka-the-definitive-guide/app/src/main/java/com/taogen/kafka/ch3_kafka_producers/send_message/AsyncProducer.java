package com.taogen.kafka.ch3_kafka_producers.send_message;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 *
 * @author taogen
 */
public class AsyncProducer {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        try (KafkaProducer<String, String> producer = new KafkaProducer<>(props)) {
            ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", "key1", "Hello Kafka!");
            Future<RecordMetadata> future = producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.printf("Asynchronous callback: Message sent to topic %s partition %d with offset %d%n",
                        metadata.topic(), metadata.partition(), metadata.offset());
                }
            });
            System.out.println("Do other work here if needed");
            RecordMetadata recordMetadata = future.get();
            System.out.println("recordMetadata" + recordMetadata);
        }
        System.out.println("Done");
    }
}
