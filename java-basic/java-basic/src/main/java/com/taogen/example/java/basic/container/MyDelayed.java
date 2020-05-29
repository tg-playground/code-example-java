package com.taogen.example.java.basic.container;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author Taogen
 */
public class MyDelayed implements Delayed {
    private String name;
    private long time;

    public MyDelayed(String name, long delayMillisecond) {
        this.name = name;
        this.time = System.currentTimeMillis() + delayMillisecond;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(time - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "MyDelayed{" + "name='" + name + '\'' + ", time=" + time + '}';
    }
}
