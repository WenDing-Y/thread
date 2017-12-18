package com.haut.thread;


import java.util.concurrent.*;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 自定义线程池和拒绝策略
 */
public class RejectThreadDemo {

    public static class MyTask implements Runnable {
        @Override
        public void run() {
            System.out.println(System.currentTimeMillis() + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        MyTask myTask = new MyTask();
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10), new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println(r.toString() + "is discard");
            }
        });

        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            executorService.submit(myTask);
            Thread.sleep(100);
        }
    }


}
