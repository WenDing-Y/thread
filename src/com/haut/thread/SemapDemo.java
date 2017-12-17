package com.haut.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 信号量机制demo
 */
public class SemapDemo implements Runnable {
    Semaphore semaphore = new Semaphore(5);
    @Override
    public void run() {
        try {
            semaphore.acquire();
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + "done");
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        final SemapDemo semapDemo = new SemapDemo();
        for (int i = 0; i < 10; i++) {
            exec.submit(semapDemo);
        }

    }

}
