package com.taogen.springbootrabbitmq.consume.messagelisteneradapter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author taogen
 */
@Component
@Slf4j
public class Receiver {
    public void receiveMessage(String message) {
        // This method will be called when a message is received
        log.info("Received <" + message + ">");
    }
}
