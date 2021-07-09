package com.taogen.example.java.basic.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Taogen
 */
public class ReentrantLockExample {
    private static final Lock lock1 = new ReentrantLock();
    private static final Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(() -> {
            Boolean myLock = false;
            Boolean otherLock = false;
            myLock = lock1.tryLock();
            otherLock = lock2.tryLock();
            if (!(myLock && otherLock)) {
                if (myLock) {
                    lock1.unlock();
                }
                if (otherLock) {
                    lock2.unlock();
                }
            }
            System.out.println("hello Thread 1");
        }).start();
        new Thread(() -> {
            Boolean myLock = false;
            Boolean otherLock = false;
            myLock = lock2.tryLock();
            otherLock = lock1.tryLock();
            System.out.println("hello Thread 2");
        }).start();
    }
}
