package com.taogen.producer;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 *
 * @author taogen
 */
@Component
@RequiredArgsConstructor
public class KafkaConfigVerifier implements CommandLineRunner {
    private final ProducerFactory<String, String> producerFactory;

    @Override
    public void run(String... args) throws Exception {
        Map<String, Object> config = producerFactory.getConfigurationProperties();
        System.out.println("Idempotence Enabled: " + config.get(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG));

        // To print everything:
        config.forEach((key, value) -> System.out.println(key + " = " + value));
    }
}
