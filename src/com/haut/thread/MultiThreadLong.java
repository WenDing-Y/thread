package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/16
 * 原子性demo 64位jvm下long 是原子操作，如果是32位的话，就不是原子操作
 */
public class MultiThreadLong {

    public static long t = 0;

    public static class ChangeT implements Runnable {
        private long to;

        public ChangeT(long to) {
            this.to = to;
        }

        @Override
        public void run() {
            while (true) {
                MultiThreadLong.t = to;
                Thread.yield();
            }
        }
    }

    public static class ReadT implements Runnable {

        @Override
        public void run() {
            while (true) {
                long tmp = MultiThreadLong.t;
                System.out.println(tmp);
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new ChangeT(111L)).start();
        new Thread(new ChangeT(-999L)).start();
        new Thread(new ChangeT(200L)).start();
        new Thread(new ReadT()).start();
    }
}
