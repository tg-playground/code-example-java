package com.taogen.example.java.basic.concurrency.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * @author Taogen
 */
public class Main {
    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        forkJoinPool.invoke(new MyRecursiveAction(100));
    }
}
