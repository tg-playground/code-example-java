package com.taogen.feature.mandatory;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * @author taogen
 */
@Slf4j
public class MyReturnListener implements ReturnListener {
    @Override
    public void handleReturn(int replyCode, String replyText, String exchange, String routingKey, AMQP.BasicProperties properties, byte[] body) throws IOException {
        log.info("Returned message:");
        log.info("  Reply Code: " + replyCode);
        log.info("  Reply Text: " + replyText);
        log.info("  Exchange: " + exchange);
        log.info("  Routing Key: " + routingKey);
        log.info("  Properties: " + properties);
        log.info("  Body: " + new String(body));
    }
}
