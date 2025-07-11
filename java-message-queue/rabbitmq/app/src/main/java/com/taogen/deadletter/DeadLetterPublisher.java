package com.taogen.deadletter;

import com.rabbitmq.client.BuiltinExchangeType;
import com.taogen.common.AbstractPublisher;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author taogen
 */
public class DeadLetterPublisher extends AbstractPublisher {
    public DeadLetterPublisher() {
        this.exchangeType = BuiltinExchangeType.FANOUT;
    }

    public static void main(String[] args) throws IOException {
        String fanoutExchangeName = "taxi-fanout";
        String deadLetterExchangeName = "taxi-dlx";
        DeadLetterPublisher deadLetterPublisher = new DeadLetterPublisher();
        /*
         Declare the fanout exchange and queues
         */
        deadLetterPublisher.declareExchange(fanoutExchangeName);
        List<String> queueNames = List.of("taxi-inbox.1", "taxi-inbox.2");
        Map<String, Object> arguments = Map.of(
                "x-message-ttl", 10000, // 10 seconds
                "x-dead-letter-exchange", deadLetterExchangeName
                // "x-dead-letter-routing-key", "" // taxi-dlx is a fanout exchange does not use a routing key
        );
        deadLetterPublisher.declareQueues(queueNames, List.of(arguments, arguments));
        deadLetterPublisher.bindQueuesToExchange(fanoutExchangeName, queueNames, List.of("", ""));
        /*
         Declare the dead letter exchange and queue
         */
        deadLetterPublisher.declareExchange(deadLetterExchangeName);
        deadLetterPublisher.declareQueue("taxi-dlq", null);
        deadLetterPublisher.bindQueueToExchange(deadLetterExchangeName, "taxi-dlq", "");
        /*
         Publish a message to the fanout exchange
         After 10 seconds, the message will be moved to the dead letter exchange
         */
        deadLetterPublisher.publish(fanoutExchangeName, "", "Hello, world!");
    }
}
