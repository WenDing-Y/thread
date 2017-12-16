package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 */
public class SleepInterruptThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    System.out.println("current thread living");
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("current thread interrupt,safe exit");
                        break;
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("interrupted when sleep");
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                    Thread.yield();
                }
            }
        };
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }
}
