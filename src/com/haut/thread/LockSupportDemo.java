package com.haut.thread;

import java.util.concurrent.locks.LockSupport;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * locksupport 线程阻塞,阻塞期间可以立即响应中断
 */
public class LockSupportDemo {

    public static Object u = new Object();
    static ChangeObjectThread changeObjectThread = new ChangeObjectThread("thread1");
    static ChangeObjectThread changeObjectThread1 = new ChangeObjectThread("thread2");

    public static class ChangeObjectThread extends Thread {
        public ChangeObjectThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "阻塞" + "time" + System.currentTimeMillis());
            LockSupport.park();
            if (Thread.interrupted()) {
                System.out.println(Thread.currentThread().getName() + "中断" + "time" + System.currentTimeMillis());
            }
            System.out.println(Thread.currentThread().getName() + "going" + "time" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        changeObjectThread.start();
        changeObjectThread1.start();
        Thread.sleep(2000);
        changeObjectThread.interrupt();
        Thread.sleep(1000);
        LockSupport.unpark(changeObjectThread);
        LockSupport.unpark(changeObjectThread1);
    }
}
