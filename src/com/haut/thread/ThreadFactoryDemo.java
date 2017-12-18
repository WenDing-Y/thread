package com.haut.thread;

import java.util.concurrent.*;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 自定义线程创建
 */
public class ThreadFactoryDemo {
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


    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0, TimeUnit.MILLISECONDS, new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread();
                thread.setDaemon(true);
                System.out.println("thread is create");
                return thread;
            }
        });

        for (int i = 0; i < 15; i++) {
            executorService.submit(myTask);
            Thread.sleep(100);
        }

    }

}
