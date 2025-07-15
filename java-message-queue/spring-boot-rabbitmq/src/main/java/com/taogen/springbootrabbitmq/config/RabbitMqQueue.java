package com.taogen.springbootrabbitmq.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author taogen
 */
@Configuration
public class RabbitMqQueue {

    public static final String QUEUE_1_NAME = "spring-amqp-queue.1";
    public static final String QUEUE_2_NAME = "spring-amqp-queue.2";
    public static final String QUEUE_3_NAME = "spring-amqp-queue.3";

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

}
