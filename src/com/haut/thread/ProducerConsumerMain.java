package com.haut.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class ProducerConsumerMain {

    public static void main(String[] args) {
        BlockingQueue<PCData> queue = new LinkedBlockingQueue<PCData>();
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        for (int i = 0; i < 5; i++) {
            executorService.submit(new Producer(queue));
        }
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Counsumer(queue));
        }
    }
}
