package com.haut.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 读写锁分离，写线程串行，读线程并行，大概运行时间2秒多
 * 直接采用重入锁，所有的操作都要进行加锁，时间大概大12多
 */
public class ReadWriteLock {

    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    private static Lock readlock = reentrantReadWriteLock.readLock();
    private static Lock writelock = reentrantReadWriteLock.writeLock();
    private int value;
    private final static ReadWriteLock readWriteLock = new ReadWriteLock();
    private static ReadThread readThread = new ReadThread();
    private static WriteThread writeThread = new WriteThread();

    public Object handleRead(Lock lock) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(Lock lock, int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1000);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public static class ReadThread implements Runnable {

        @Override
        public void run() {
            try {
                //System.out.println("read value is " + readWriteLock.handleRead(readlock));
                System.out.println("read value is " + readWriteLock.handleRead(lock));

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class WriteThread implements Runnable {

        @Override
        public void run() {
            try {
                // readWriteLock.handleWrite(writelock, new Random().nextInt());
                readWriteLock.handleWrite(lock, new Random().nextInt());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(20);
        System.out.println("start time " + System.currentTimeMillis());
        for (int i = 0; i < 10; i++) {
            executorService.submit(readThread);
        }
        for (int i = 0; i < 2; i++) {
            executorService.submit(writeThread);
        }
        //退出线程池
        executorService.shutdown();
    }
}
