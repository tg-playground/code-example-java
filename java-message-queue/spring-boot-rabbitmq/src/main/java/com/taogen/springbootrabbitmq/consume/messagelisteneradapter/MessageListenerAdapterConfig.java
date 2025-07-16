package com.taogen.springbootrabbitmq.consume.messagelisteneradapter;

import com.taogen.springbootrabbitmq.config.MyRabbitMqQueue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author taogen
 */
@Configuration
public class MessageListenerAdapterConfig {
    private String queueName = MyRabbitMqQueue.QUEUE_1_NAME;

    @Bean
    MessageListenerAdapter listenerAdapter(Receiver receiver) {
        return new MessageListenerAdapter(receiver, "receiveMessage");
    }

    @Bean
    SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,
                                             MessageListenerAdapter listenerAdapter) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(this.queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
}
