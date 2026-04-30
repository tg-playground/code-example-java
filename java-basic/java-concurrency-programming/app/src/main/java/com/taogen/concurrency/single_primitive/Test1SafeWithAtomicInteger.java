package com.taogen.concurrency.single_primitive;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author taogen
 */
public class Test1SafeWithAtomicInteger {
    static void main() {
        System.out.println("Test beginning...");
        Counter counter = new Counter(new AtomicInteger(1));
        for (int i = 0; i < 10; i++) {
            new Thread(new MyTask(counter)).start();
        }
    }

    @Data
    static class Counter {
        private AtomicInteger value;

        public Counter(AtomicInteger value) {
            this.value = value;
        }
    }

    static class MyTask implements Runnable {
        private Counter counter;

        public MyTask(Counter counter) {
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                System.out.println(Thread.currentThread().getName() + ": " + counter.value.incrementAndGet());
            }
        }
    }
}
