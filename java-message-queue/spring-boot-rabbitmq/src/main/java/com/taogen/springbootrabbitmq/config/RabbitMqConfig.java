package com.taogen.springbootrabbitmq.config;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;

/**
 * Spring Boot will automatically create a RabbitTemplate bean
 *
 * @author taogen
 */
//@Configuration
public class RabbitMqConfig {
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory("localhost");
        connectionFactory.setUsername("guest123");
        connectionFactory.setPassword("guest123");
        return connectionFactory;
    }

//    @Bean
//    public RabbitTemplate rabbitTemplate() {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory());
//        return template;
//    }
}
