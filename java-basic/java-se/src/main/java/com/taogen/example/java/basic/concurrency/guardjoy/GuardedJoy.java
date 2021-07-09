package com.taogen.example.java.basic.concurrency.guardjoy;

/**
 * @author Taogen
 */
public class GuardedJoy {
    private boolean joy;

    public static void main(String[] args) throws InterruptedException {
        GuardedJoy guardedJoy = new GuardedJoy();
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            guardedJoy.joy = true;
        });
        thread.start();
        guardedJoy.guardedJoy();
        System.out.println("joy is " + guardedJoy.joy);
    }

    public void guardedJoy() {
        while (!joy) {
            System.out.println("wait!");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Joy has been achieved!");
    }
}
