package com.haut.thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 重入锁提供了等待锁的过程可以进行中断响应，可以看到线程2中断后，程序
 * 进行了中断响应释放锁资源，然后线程1得到锁完成任务，两个线程退出
 */
public class IntLock implements Runnable {

    public static ReentrantLock lock1 = new ReentrantLock();
    public static ReentrantLock lock2 = new ReentrantLock();
    int lock;

    public IntLock(int lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            if (lock == 1) {
                try {
                    lock1.lockInterruptibly();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock2.lockInterruptibly();
            } else {
                try {
                    lock2.lockInterruptibly();
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock1.lockInterruptibly();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (lock1.isHeldByCurrentThread()) {
                lock1.unlock();
            }
            if (lock2.isHeldByCurrentThread()) {
                lock2.unlock();
            }
            System.out.println(Thread.currentThread().getName() + "exit");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        IntLock intLock = new IntLock(1);
        IntLock intLock1 = new IntLock(2);
        Thread thread = new Thread(intLock, "thread1");
        Thread thread1 = new Thread(intLock1, "thread2");
        thread.start();
        thread1.start();
        Thread.sleep(3000);
        thread1.interrupt();
    }
}
