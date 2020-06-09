package com.taogen.example.java.basic.concurrency.guardjoy;

/**
 * @author Taogen
 */
public class GuardedJoyByWait {

    Object lock = new Object();
    private boolean joy;

    public static void main(String[] args) throws InterruptedException {
        GuardedJoyByWait guardedJoyByWait = new GuardedJoyByWait();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
            }
            guardedJoyByWait.joy = true;
            guardedJoyByWait.myNotify();
        });
        thread.start();
        guardedJoyByWait.guardedJoy();
        System.out.println("joy is " + guardedJoyByWait.joy);
    }

    public synchronized void guardedJoy() {
        while (!joy) {
            System.out.println("wait!");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Joy has been achieved!");
    }

    public synchronized void myNotify() {
        notifyAll();
    }
}
