package com.taogen.feature.delay;

import com.rabbitmq.client.BuiltinExchangeType;
import com.taogen.common.AbstractPublisher;

import java.io.IOException;
import java.util.Map;

/**
 * Use case: A survey sent out to customers 5 minutes after a finished ride.
 *
 * @author taogen
 */
public class DelayPublisher extends AbstractPublisher {
    public static void main(String[] args) throws IOException {
        DelayPublisher delayPublisher = new DelayPublisher();
        Map<String, Object> arguments = Map.of(
                "x-message-ttl", 10000, // 10 seconds
                "x-dead-letter-exchange", "",
                "x-dead-letter-routing-key", "work.now"
        );
        delayPublisher.declareQueue("work.later", arguments);
        delayPublisher.declareQueue("work.now", null);
        String exchangeName = "work-delay";
        delayPublisher.declareExchange(exchangeName, BuiltinExchangeType.DIRECT);
        delayPublisher.bindQueueToExchange(exchangeName, "work.later", "work.later");
        delayPublisher.publish(exchangeName, "work.later", "Hello, world!");
    }
}
