package com.haut.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 重入锁的使用，可以加两次锁，比同步关键字使用灵活
 */
public class ReenterLock implements  Runnable {
    public  static ReentrantLock lock = new ReentrantLock();
    public  static  int i=0;
    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            lock.lock();
           // lock.lock();
            i++;
            //lock.unlock();
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new ReenterLock());
        Thread thread1 = new Thread(new ReenterLock());
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(ReenterLock.i);
    }
}
