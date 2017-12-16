package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * 当在一个对象上执行wait方法后，当前线程暂停执行，释放资源，等待唤醒，
 * notify随机唤醒一个线程，而notifyall方法，会唤醒所有的线程
 */
public class SimpleWN {

    final static Object object = new Object();

    public static class WaitThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + "wait");
                try {
                    object.wait();
                    System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + "going ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class NotifyThread extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("notifythread name " + Thread.currentThread().getName());
                object.notify();     //只能唤醒一个线程
                //object.notifyAll();  //可以唤醒所有线程
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public static void main(String[] args) {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        WaitThread waitThread1 = new WaitThread();
        waitThread1.start();
        NotifyThread notifyThread = new NotifyThread();
        notifyThread.start();
    }
}
