package com.taogen.example.java.basic.concurrency;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
public class PrintThreadPoolInformation {
    private static int corePoolSize = 50;
    private static ScheduledExecutorService service = new ScheduledThreadPoolExecutor(corePoolSize,
            new BasicThreadFactory.Builder().namingPattern("schedule-pool-%d").daemon(true).build());

    public static void main(String[] args) {
        service.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("executing task...");
            }
        }, 1, TimeUnit.MILLISECONDS);
        // 最大线程数，核心线程数，历史最大线程数，
        // 当前线程数，当前活跃线程数，当前排队线程数，
        // 线程存活时间，执行任务数，完成任务数
        ScheduledThreadPoolExecutor scheduledExecutorService = (ScheduledThreadPoolExecutor) service;
        int maxPoolSize = 0;
        int corePoolSize = 0;
        int largestPoolSize = 0;
        int currentThreadNumber = 0;
        int activeThreadNumber = 0;
        int queueThreadNumber = 0;
        long keepAliveTime = 0;
        long taskCount = 0;
        long completedTaskCount = 0;
        if (scheduledExecutorService != null) {
            maxPoolSize = scheduledExecutorService.getMaximumPoolSize();
            corePoolSize = scheduledExecutorService.getCorePoolSize();
            largestPoolSize = scheduledExecutorService.getLargestPoolSize();
            currentThreadNumber = scheduledExecutorService.getPoolSize();
            activeThreadNumber = scheduledExecutorService.getActiveCount();
            queueThreadNumber = scheduledExecutorService.getQueue().size();
            keepAliveTime = scheduledExecutorService.getKeepAliveTime(TimeUnit.MILLISECONDS);
            taskCount = scheduledExecutorService.getTaskCount();
            completedTaskCount = scheduledExecutorService.getCompletedTaskCount();
        }
        String result = String.format(
                "最大线程数: %s, 核心线程数: %s, 历史最大线程数: %s, \n" +
                        "当前线程数: %s, 当前活动线程数: %s, 当前排队线程数: %s, \n" +
                        "线程存活时间(毫秒): %s, 执行任务数: %s, 完成任务数: %s",
                maxPoolSize, corePoolSize, largestPoolSize,
                currentThreadNumber, activeThreadNumber, queueThreadNumber,
                keepAliveTime, taskCount, completedTaskCount);
        System.out.println(result);
    }
}
