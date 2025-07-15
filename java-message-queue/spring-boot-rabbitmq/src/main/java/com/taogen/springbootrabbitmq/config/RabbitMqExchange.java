package com.taogen.springbootrabbitmq.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author taogen
 */
@Configuration
public class RabbitMqExchange {

    public static final String DIRECT_EXCHANGE_NAME = "spring-amqp-direct-exchange";
    public static final String TOPIC_EXCHANGE_NAME = "spring-amqp-topic-exchange";
    public static final String FANOUT_EXCHANGE_NAME = "spring-amqp-fanout-exchange";

    @Bean
    DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE_NAME);
    }

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME);
    }

    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME);
    }
}
