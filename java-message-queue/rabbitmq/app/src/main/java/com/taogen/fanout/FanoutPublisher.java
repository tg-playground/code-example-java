package com.taogen.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.taogen.common.AbstractPublisher;

import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author taogen
 */
public class FanoutPublisher extends AbstractPublisher {

    public static void main(String[] args) throws IOException {
        FanoutPublisher fanoutPublisher = new FanoutPublisher();
        String exchangeName = "taxi_fanout";
        fanoutPublisher.declareExchange(exchangeName, BuiltinExchangeType.FANOUT);
        List<String> queueNames = List.of("taxi.1", "taxi.2", "taxi.3");
        fanoutPublisher.declareQueues(queueNames);
        fanoutPublisher.bindQueuesToExchange(exchangeName, queueNames, List.of("", "", ""));
        fanoutPublisher.publish(exchangeName, "", "Hello, world!" + new Date());
    }
}
