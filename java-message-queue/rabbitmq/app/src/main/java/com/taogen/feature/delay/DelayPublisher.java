package com.taogen.feature.delay;

import com.taogen.common.AbstractPublisher;

import java.io.IOException;
import java.util.Map;

/**
 * Use case: A survey sent out to customers 5 minutes after a finished ride.
 *
 * @author taogen
 */
public class DelayPublisher extends AbstractPublisher {
    public static void main(String[] args) throws IOException, InterruptedException {
        DelayPublisher delayPublisher = new DelayPublisher();
        Map<String, Object> arguments = Map.of(
                "x-message-ttl", 10000, // 10 seconds
                "x-dead-letter-exchange", "",
                "x-dead-letter-routing-key", "work.now"
        );
        delayPublisher.declareQueue("work.later", arguments);
        delayPublisher.declareQueue("work.now", null);
        // Using the default exchange (an empty string ""), the routing key is the queue name
        while (true) {
            // publish a message per second
            delayPublisher.publish("", "work.later", "Hello, world!");
            Thread.sleep(1000);
        }
    }
}
