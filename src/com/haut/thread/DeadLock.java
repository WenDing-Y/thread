package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 模拟死锁的情况，通过jstack得到堆栈的情况
 */
public class DeadLock extends Thread {

    protected Object tool;
    static Object fork1 = new Object();
    static Object fork2 = new Object();

    public DeadLock(Object tool) {
        this.tool = tool;
        if (tool == fork1) {
            this.setName("A");
        }
        if (tool == fork2) {
            this.setName("B");
        }
    }

    @Override
    public void run() {
        if (tool == fork1) {
            synchronized (fork1) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork2) {
                    System.out.println("A start eating");
                }
            }
        }
        if (tool == fork2) {
            synchronized (fork2) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (fork1) {
                    System.out.println("B start eating");
                }
            }
        }
    }

    public static void main(String[] args) {
        new DeadLock(fork1).start();
        new DeadLock(fork2).start();
    }
}
