package com.taogen.example.java.basic.concurrency.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * @author Taogen
 */
public class MyRecursiveAction extends RecursiveAction {
    private static final long THRESHOLD = 16;
    private long workload = 0;

    public MyRecursiveAction(long workload) {
        this.workload = workload;
    }

    @Override
    protected void compute() {
        if (this.workload <= THRESHOLD) {
            System.out.println("Doing workload myself : " + this.workload);
        } else {
            System.out.println("Splitting workload : " + this.workload);
            List<MyRecursiveAction> subtasks = new ArrayList<>();
            subtasks.addAll(createSubtasks());
            for (RecursiveAction subtask : subtasks) {
                subtask.fork();
            }
        }
    }

    private List<MyRecursiveAction> createSubtasks() {
        List<MyRecursiveAction> subtasks = new ArrayList<>();
        MyRecursiveAction subtask1 = new MyRecursiveAction(this.workload / 2);
        MyRecursiveAction subtask2 = new MyRecursiveAction(this.workload / 2);
        subtasks.add(subtask1);
        subtasks.add(subtask2);
        return subtasks;
    }
}
