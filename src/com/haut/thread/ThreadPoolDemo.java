package com.haut.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 固定大小的线程池
 */
public class ThreadPoolDemo {

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        MyTask myTask = new MyTask();
        for (int i = 0; i < 10; i++) {
            executorService.submit(myTask);
        }
        executorService.shutdown();
    }
}
