package com.haut.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 得用condition让线程在合适的时间等待，或者在具体的时刻得到通知
 */
public class ReenterLockCondition implements  Runnable {
    public  static ReentrantLock reentrantLock =new ReentrantLock();
    public  static Condition condition =reentrantLock.newCondition();
    @Override
    public void run() {
        try {
            reentrantLock.lock();
            condition.await();
            System.out.println("thread going");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
            new Thread(new ReenterLockCondition()).start();
            Thread.sleep(2000);
            reentrantLock.lock();
            condition.signal();
            reentrantLock.unlock();
    }
}
