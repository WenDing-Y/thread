package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 直接作用于静态方法，相当于对当前类加锁，进入同步代码前获得当前类的锁
 */
public class AccountingSynStaticMethod implements Runnable {

    static int i = 0;

    public static synchronized void increase() {
        i++;
    }

    @Override
    public void run() {
        for (int j = 0; j < 1000; j++) {
            increase();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new AccountingSynStaticMethod());
        Thread thread1 = new Thread(new AccountingSynStaticMethod());
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(AccountingSynStaticMethod.i);
    }
}
