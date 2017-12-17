package com.haut.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * trylock方法不带参数运行，如果申请锁失败，则返回false
 */
public class TryLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public TryLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        if (lock == 1) {
            while (true) {
                if (lock1.tryLock()) {
                    try {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock2.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "get two lock ,task done");
                            lock2.unlock();
                            return;
                        }
                    } finally {
                        lock1.unlock();
                    }
                }
            }
        } else {
            while (true) {
                if (lock2.tryLock()) {
                    try {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        if (lock1.tryLock()) {
                            System.out.println(Thread.currentThread().getName() + "get two lock,task done");
                            lock1.unlock();
                            return;
                        }
                    } finally {
                        lock2.unlock();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new TryLock(1), "thread1").start();
        new Thread(new TryLock(2), "thread2").start();
    }
}
