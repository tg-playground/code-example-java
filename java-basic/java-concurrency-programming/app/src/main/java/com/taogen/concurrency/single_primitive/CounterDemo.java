package com.taogen.concurrency.single_primitive;

import lombok.Data;

/**
 *
 * @author taogen
 */
public class CounterDemo {
    static void main() {
        System.out.println("Test beginning...");
        Counter counter = new Counter(1);
        for (int i = 0; i < 10; i++) {
            new Thread(new MyTask(counter)).start();
        }
    }

    @Data
    static class Counter {
        private Integer value;

        public Counter(Integer value) {
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
                counter.setValue(counter.getValue() + 1);
                System.out.println(Thread.currentThread().getName() + ": " + counter.value);
            }
        }
    }
}
