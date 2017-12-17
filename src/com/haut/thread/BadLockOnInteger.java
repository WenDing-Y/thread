package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/17
 */
public class BadLockOnInteger implements Runnable {

    public static Integer i = 0;
    static BadLockOnInteger badLockOnInteger = new BadLockOnInteger();

    @Override
    public void run() {
        for (int j = 0; j < 10000; j++) {
            synchronized (badLockOnInteger) {
                i++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(badLockOnInteger);
        Thread thread1 = new Thread(badLockOnInteger);
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(BadLockOnInteger.i);
    }
}
