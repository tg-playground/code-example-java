package com.taogen.example.java.advanced.concurrency;

import java.util.concurrent.FutureTask;

/**
 * @author Taogen
 */
public class BadThreads {

    static volatile String message;

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new CorrectorThread();
        thread.start();
        message = "mares do not eat oats.";
        thread.join();
        System.out.println(message);
        Thread thread1 = new Thread();
        FutureTask<String> task = new FutureTask(() -> {
            return "a";
        });

    }

    private static class CorrectorThread extends Thread {
        @Override
        public void run() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            message = "Mares do eat oats.";
        }
    }

}
