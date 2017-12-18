package com.haut.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 无锁的对象引用 atomicReference,重点在于对象的引用
 * 记不住对象的状态，容易造成多充的情况
 */
public class AtomicReferenceDemo {

    static AtomicReference<Integer> money = new AtomicReference<>(19);
    static ChargeThread chargeThread = new ChargeThread("lei");
    static ConsumeThread consumeThread = new ConsumeThread();

    public static class ChargeThread implements Runnable {
        private String name;

        public ChargeThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                while (true) {
                    Integer m = money.get();
                    if (m < 20) {
                        if (money.compareAndSet(m, m + 20)) {
                            System.out.println(Thread.currentThread().getName() + name + "充值成功" + "还有" + money.get());
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public static class ConsumeThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                while (true) {
                    Integer m = money.get();
                    if (m > 10) {
                        if (money.compareAndSet(m, m - 10)) {
                            System.out.println("成功消费10,还有" + money.get());
                            break;
                        }
                    } else {
                        System.out.println("金额不够");
                        break;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 3; i++) {
            executorService.submit(chargeThread);
        }
        for (int i = 0; i < 3; i++) {
            executorService.submit(consumeThread);
        }
        executorService.shutdown();
    }


}
