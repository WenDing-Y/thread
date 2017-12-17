package com.haut.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 重入锁可以实现公平锁
 */
public class FairLock implements Runnable {
    public static ReentrantLock reentrantLock = new ReentrantLock(true);

    @Override
    public void run() {
        while (true) {
            try {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "得到锁");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new FairLock()).start();
        new Thread(new FairLock()).start();
    }
}

