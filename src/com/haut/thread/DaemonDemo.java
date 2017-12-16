package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * 守护线程会在工作线程结束后，退出任务，demo中如果不把线程设置为守护
 * 线程中的话，主线程结束 后，print会一直进行
 */
public class DaemonDemo {

    public static class DaemonThread extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("current thread is alive");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        DaemonThread daemonThread = new DaemonThread();
        daemonThread.setDaemon(true);
        daemonThread.start();
        Thread.sleep(5000);
    }
}
