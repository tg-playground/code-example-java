package com.taogen.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.taogen.common.AbstractPublisher;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Use case: The back office wants to be able to send information messages to all taxis at once.
 * @author taogen
 */
public class FanoutPublisher extends AbstractPublisher {

    public FanoutPublisher() {
        this.exchangeType = BuiltinExchangeType.FANOUT;
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        FanoutPublisher fanoutPublisher = new FanoutPublisher();
        String exchangeName = "taxi_fanout";
        fanoutPublisher.declareExchange(exchangeName);
        List<String> queueNames = List.of("taxi.1", "taxi.2", "taxi.3");
        fanoutPublisher.declareQueues(queueNames, null);
        fanoutPublisher.bindQueuesToExchange(exchangeName, queueNames, List.of("", "", ""));
        while (true) {
            // publish a message per second
            fanoutPublisher.publish(exchangeName, "", "Hello, world!" + new Date());
            Thread.sleep(1000);
        }
    }
}
