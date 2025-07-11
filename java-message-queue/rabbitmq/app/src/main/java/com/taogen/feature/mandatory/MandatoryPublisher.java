package com.taogen.feature.mandatory;

import com.taogen.common.AbstractPublisher;

import java.io.IOException;

/**
 * Use case: Sending messages to individual drivers from the back office service. Furthermore, if possible, CC would like drivers who do not have an inbox queue set up on RabbitMQ to get the message emailed to them immediately.
 *
 * @author taogen
 */
public class MandatoryPublisher extends AbstractPublisher {

    public static void main(String[] args) throws IOException {
        MandatoryPublisher mandatoryPublisher = new MandatoryPublisher();
        mandatoryPublisher.channel.addReturnListener(new MyReturnListener());
        // Using the default exchange (an empty string "") to send a message with mandatory to a non-existing queue
        // RabbitMQ Response: Reply Code: 312, Reply Text: NO_ROUTE
        mandatoryPublisher.channel.basicPublish("", "random-key", true, null, "Hello, world!".getBytes());
    }
}
