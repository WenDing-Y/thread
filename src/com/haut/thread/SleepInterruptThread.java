package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * InterruptException 异常指的是当程序 sleep时，这个异常就会产生，当前实例中
 * 在中断处理中，我们再次中断自己，保证程序安全退出
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
