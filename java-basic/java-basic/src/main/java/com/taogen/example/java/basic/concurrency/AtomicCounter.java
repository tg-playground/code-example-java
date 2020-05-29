package com.taogen.example.java.basic.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Taogen
 */
public class AtomicCounter {
    private AtomicInteger c = new AtomicInteger(0);

    public void increment() {
        c.incrementAndGet();
    }

    public void decrement() {
        c.decrementAndGet();
    }

    public int value() {
        return c.get();
    }
}
