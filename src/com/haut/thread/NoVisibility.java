package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * volatitle 保证数据可见性
 */
public class NoVisibility {

    private static volatile boolean ready;
    private static int num;

    public static class ReaderThread extends Thread {
        @Override
        public void run() {

            while (!ready) ;
            System.out.println(num);

        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReaderThread().start();
        Thread.sleep(2000);
        num = 42;
        ready = true;
    }
}
