package com.taogen.example.java.advanced.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Taogen
 */
public class ThreadExample {

    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {
//        Thread thread1 = new Thread(()->{
//            int count = 10;
//            for (int i=0; i < count; i++){
//                System.out.println(i);
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("interrupted!");
//                    e.printStackTrace();
//                    return;
//                }
//            }
//        });
//        thread1.start();
//        Thread.sleep(2000);
//        thread1.join(1000);
//        thread1.interrupt();
//        System.out.println(thread1.isInterrupted());
//        new ThreadExample().test();
        AtomicInteger atomicInteger = new AtomicInteger(0);
        atomicInteger.accumulateAndGet(10, (x, y) -> {
            return x + y;
        });
        System.out.println(atomicInteger.get());
    }

    public void test() throws InterruptedException {

        synchronized (lock) {
            Thread thread1 = new Thread(() -> {
                int i = 0;
                System.out.println("before get the lock...");
                synchronized (lock) {
                    System.out.println("got the lock.");
                    notifyAll();
                }
                while (true) {
                    if (Thread.interrupted()) {
                        try {
                            throw new InterruptedException();
                        } catch (InterruptedException e) {
                            System.out.println("Thread interrupted");
                            e.printStackTrace();
                        }
                        break;
                    }
                    System.out.println("aaaaaaaaaabbbbbbbbbbbbbcccccccccccccccccccc" + (i++));
                }
            });
            thread1.start();
            Thread.sleep(100);
            lock.wait();
//            thread1.join();

            Thread.sleep(1);
            System.out.println("sleep over-----------------------------------------------------------------------");
            thread1.interrupt();
            System.out.println("main over-----------------------------------------------------------------------");
            Thread.sleep(1);
            System.exit(-1);
        }
    }
}
