package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 给对象加锁
 */
public class AccountingSync implements Runnable {
    static AccountingSync accountingSync = new AccountingSync();
    static int i = 0;

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            synchronized (accountingSync) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(accountingSync);
        Thread thread1 = new Thread(accountingSync);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(accountingSync.i);
    }
}
