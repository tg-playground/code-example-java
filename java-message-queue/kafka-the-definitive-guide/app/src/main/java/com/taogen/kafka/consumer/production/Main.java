package com.taogen.kafka.consumer.production;

import java.util.Properties;

public class Main {

    public static void main(String[] args) {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "production-group");

        props.put("key.deserializer",
            "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer",
            "org.apache.kafka.common.serialization.StringDeserializer");

        props.put("enable.auto.commit", "false");
        props.put("auto.offset.reset", "earliest");

        props.put("max.poll.records", "500");
        props.put("session.timeout.ms", "10000");
        props.put("heartbeat.interval.ms", "3000");

        ProductionConsumer consumer =
            new ProductionConsumer(props, "my-topic");

        Runtime.getRuntime().addShutdownHook(
            new Thread(consumer::shutdown)
        );

        consumer.run();
    }
}
