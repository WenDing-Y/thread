package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 加锁直接作用于方法
 */
public class AccountingSyncMethod implements Runnable {
    static AccountingSyncMethod accountingSyncMethod = new AccountingSyncMethod();
    static int i = 0;

    public synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(accountingSyncMethod);
        Thread thread1 = new Thread(accountingSyncMethod);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(accountingSyncMethod.i);
    }
}
