package com.taogen.springbootrabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author taogen
 */
@Configuration
public class RabbitMqBinding {

    public static final String DIRECT_ROUTING_KEY = "spring-amqp-queue.1";
    public static final String TOPIC_ROUTING_KEY = "spring-amqp-queue.*";

    @Bean
    Binding directQueue1Binding(Queue queue1, DirectExchange directExchange) {
        return BindingBuilder.bind(queue1).to(directExchange).with(DIRECT_ROUTING_KEY);
    }

    @Bean
    Binding topicQueue1Binding(Queue queue1, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue1).to(topicExchange).with(TOPIC_ROUTING_KEY);
    }

    @Bean
    Binding topicQueue2Binding(Queue queue2, TopicExchange topicExchange) {
        return BindingBuilder.bind(queue2).to(topicExchange).with(TOPIC_ROUTING_KEY);
    }

    @Bean
    Binding fanoutQueue1Binding(Queue queue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue1).to(fanoutExchange);
    }

    @Bean
    Binding fanoutQueue2Binding(Queue queue2, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue2).to(fanoutExchange);
    }

    @Bean
    Binding fanoutQueue3Binding(Queue queue3, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queue3).to(fanoutExchange);
    }
}
