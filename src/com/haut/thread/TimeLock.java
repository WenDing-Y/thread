package com.haut.thread;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 限时等待锁
 */
public class TimeLock implements Runnable {

    public static ReentrantLock reentrantLock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (reentrantLock.tryLock(5, TimeUnit.SECONDS)) {
                Thread.sleep(6000);
            } else {
                System.out.println(Thread.currentThread().getName() + "get lock failed");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new TimeLock(), "thread1");
        Thread thread1 = new Thread(new TimeLock(), "thread2");
        thread.start();
        thread1.start();

    }
}
