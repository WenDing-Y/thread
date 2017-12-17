package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * 线程优先级数字越大，等级越高
 */
public class PriorityDemo {

    public static class HighPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (HighPriority.class) {
                    count++;
                    if (count > 10000) {
                        System.out.println("high task is complete");
                        break;
                    }
                }
            }
        }
    }

    public static class LowPriority extends Thread {
        static int count = 0;

        @Override
        public void run() {
            while (true) {
                synchronized (LowPriority.class) {
                    count++;
                    if (count > 10000) {
                        System.out.println("low task is complete");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        HighPriority highPriority = new HighPriority();
        highPriority.setPriority(5);
        LowPriority lowPriority = new LowPriority();
        lowPriority.setPriority(2);
        highPriority.start();
        lowPriority.start();
    }


}
