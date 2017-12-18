package com.haut.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * AtomicStampedReference可以很好解决对象被
 * 反复修改后导致线程无法正确判断对象状态的问题
 */
public class AtomicStampedReferenceDemo {

    static final AtomicStampedReference<Integer> money = new AtomicStampedReference<Integer>(19, 0);
    static ChargeThread chargeThread = new ChargeThread("lei");
    static ConsumeThread consumeThread = new ConsumeThread();
    final static int timestamp = money.getStamp();

    public static class ChargeThread implements Runnable {
        private String name;

        public ChargeThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            while (true) {
                while (true) {
                    Integer m = money.getReference();
                    if (m < 20) {
                        if (money.compareAndSet(m, m + 20, timestamp, timestamp + 1)) {
                            System.out.println(Thread.currentThread().getName() + name + "充值成功" + "还有" + money.getReference());
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
                    int timestamp = money.getStamp();
                    Integer m = money.getReference();
                    if (m > 10) {
                        if (money.compareAndSet(m, m - 10, timestamp, timestamp + 1)) {
                            System.out.println("成功消费10,还有" + money.getReference());
                            break;
                        }
                    } else {
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
