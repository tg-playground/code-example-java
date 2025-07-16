package com.taogen.springbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author taogen
 */
@Configuration
public class MyRabbitMqQueue {

    public static final String QUEUE_1_NAME = "spring-amqp-queue.1";
    public static final String QUEUE_2_NAME = "spring-amqp-queue.2";
    public static final String QUEUE_3_NAME = "spring-amqp-queue.3";
    public static final String QUEUE_TTL_NAME = "spring-amqp-queue.ttl";
    public static final String QUEUE_DLX_NAME = "spring-amqp-queue.dlx";

    @Bean
    Queue queue1() {
        return new Queue(QUEUE_1_NAME, true);
    }

    @Bean
    Queue queue2() {
        return new Queue(QUEUE_2_NAME, true);
    }

    @Bean
    Queue queue3() {
        return new Queue(QUEUE_3_NAME, true);
    }

    @Bean
    Queue queueTtl() {
        Map<String, Object> arguments = Map.of(
                "x-message-ttl", 10000, // 10 seconds
                "x-dead-letter-exchange", MyRabbitMqExchange.DLX_EXCHANGE);
        return new Queue(QUEUE_TTL_NAME, true, false, false, arguments);
    }

    @Bean
    Queue queueDlx() {
        return new Queue(QUEUE_DLX_NAME, true);
    }
}
