package com.haut.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 寻找堆栈
 */
public class DivTask implements Runnable {
    int a, b;

    public DivTask(int a, int b) {
        this.a = a;
        this.b = b;
    }

    @Override
    public void run() {
        double re = a / b;
        System.out.println(re);
    }

    public static void main(String[] args) {
        ThreadPoolExecutor pools = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 0L, TimeUnit.MILLISECONDS, new SynchronousQueue<>());
        for (int i = 0; i < 5; i++) {
            //pools.execute(new DivTask(100, i));
            pools.submit(new DivTask(100, i));
        }
    }
}
