package com.haut.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class Counsumer implements Runnable {

    private BlockingQueue<PCData> queue;

    public Counsumer(BlockingQueue<PCData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            try {
                PCData pcData = queue.take();
                if (pcData != null) {
                    System.out.println(Thread.currentThread().getName() + "从队列中得到的值为" + pcData.getIntData());
                }
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
