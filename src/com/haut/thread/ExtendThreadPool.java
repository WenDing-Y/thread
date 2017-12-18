package com.haut.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 扩展线程池，监控线程开始前，结束后，线程池关闭
 */
public class ExtendThreadPool {

    public static class MyTask implements Runnable {
        String name;

        public MyTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "is running " + name);
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>()) {
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println(((MyTask) r).name + "start");
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println(((MyTask) r).name + "end");
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出");
            }
        };

        for (int i = 0; i < 5; i++) {
            MyTask myTask = new MyTask(String.valueOf(i));
            executorService.submit(myTask);
        }
        executorService.shutdown();
    }
}
