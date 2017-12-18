package com.haut.thread;

import java.util.Random;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class Producer implements Runnable {
    private volatile boolean isRunning = true;
    private BlockingQueue<PCData> queue;
    private static AtomicInteger count = new AtomicInteger();

    public Producer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (isRunning) {
            try {
                Thread.sleep(random.nextInt(1000));
                PCData pcData = new PCData(count.incrementAndGet());
                if (queue.offer(pcData, 2, TimeUnit.SECONDS)) {
                    StringBuffer buffer = new StringBuffer(Thread.currentThread().getName());
                    buffer.append(pcData.getIntData()).append(" put data to queue success,queue size si ").append(queue.size());
                    System.out.println(buffer.toString());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop() {
        isRunning = false;
    }
}
