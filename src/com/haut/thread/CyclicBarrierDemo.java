package com.haut.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * cyclicbarrier 循环计数器 await的数目达到计数器值时，
 * 执行BarrierRun 线程，然后再执行work方法
 */
public class CyclicBarrierDemo {

    public static class Solider implements Runnable {
        private String solider;
        private final CyclicBarrier cyclicBarrier;

        public Solider(String solider, CyclicBarrier cyclicBarrier) {
            this.solider = solider;
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            try {
                System.out.println(solider + " start wait");
                cyclicBarrier.await();
                doWork();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
        }

        private void doWork() {
            try {
                Thread.sleep(1000);
                System.out.println(solider + "task done");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static class BarrierRun implements Runnable {
        @Override
        public void run() {
            System.out.println("士兵全部集合");
        }
    }


    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, new BarrierRun());
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Solider("士兵" + i, cyclicBarrier));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        executorService.shutdown();
    }
}
