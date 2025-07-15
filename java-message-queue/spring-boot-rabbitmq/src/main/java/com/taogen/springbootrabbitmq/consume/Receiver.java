package com.taogen.springbootrabbitmq.consume;

import org.springframework.stereotype.Component;

/**
 * @author taogen
 */
@Component
public class Receiver {
    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}
