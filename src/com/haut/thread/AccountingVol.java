package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * volatile 不能保证复合操作的原子操作
 */
public class AccountingVol implements Runnable {

    static AccountingVol accountingVol = new AccountingVol();
    static volatile int i = 0;

    public static void increase() {
        i++;
    }


    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(accountingVol);
        Thread thread1 = new Thread(accountingVol);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(i);
    }
}
