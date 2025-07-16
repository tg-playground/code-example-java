package com.taogen.springbootrabbitmq.consume.messagelistener;

import com.taogen.springbootrabbitmq.config.MyRabbitMqQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author taogen
 */
@Configuration
@Slf4j
public class MessageListenerConfig {

    private String queueName = MyRabbitMqQueue.QUEUE_2_NAME;

    @Bean
    public MessageListener messageListener() {
        return message -> {
            // Process the received message
            log.info("Received message: " + new String(message.getBody()));
        };
    }

    @Bean
    public SimpleMessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(this.queueName);
        container.setMessageListener(messageListener());
        return container;
    }
}
