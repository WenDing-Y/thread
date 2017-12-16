package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * 执行线程中断后，如果不进行处理，那么中断是没有任何作用的,
 * 可以判断线程是否中断来执行，后续步骤
 */
public class InterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        Thread.yield();
                        System.out.println("current thread interrupted");
                        break;
                    } else {
                        System.out.println("hello world");
                    }
                }
            }
        };

        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
