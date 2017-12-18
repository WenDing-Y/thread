package com.haut.thread;


import java.util.Random;
import java.util.concurrent.*;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 对性能的影响，取决于具体的业务逻辑
 */
public class ThreadPerformance {

    public static final int GEN_COUN = 10000000;
    public static final int THREAD_COUNT = 4;
    static ExecutorService executorService = Executors.newFixedThreadPool(THREAD_COUNT);
    public static Random random = new Random(123);
    public static ThreadLocal<Random> trandom = new ThreadLocal<Random>() {
        @Override
        protected Random initialValue() {
            return new Random(123);
        }
    };

    public static class RndTask implements Callable {
        private int mode = 0;

        public RndTask(int mode) {
            this.mode = mode;
        }

        public Random getRandom() {
            if (mode == 0) {
                return random;
            } else if (mode == 1) {
                return trandom.get();
            } else {
                return null;
            }
        }

        @Override
        public Object call() throws Exception {
            long b = System.currentTimeMillis();
            for (int i = 0; i < GEN_COUN; i++) {
                getRandom().nextInt();
            }
            long e = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + "spend" + (e - b));
            return e - b;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        long totalTime = 0;
        Future<Long>[] futures = new Future[THREAD_COUNT];
        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = (Future<Long>) executorService.submit(new RndTask(0));
            totalTime += futures[i].get();
        }
        System.out.println("多线程访问同一个Random实例" + totalTime);

        for (int i = 0; i < THREAD_COUNT; i++) {
            futures[i] = (Future<Long>) executorService.submit(new RndTask(1));
            totalTime += futures[i].get();
        }
        System.out.println("每个线程使用一个Random实例" + totalTime);
        executorService.shutdown();
    }


}
