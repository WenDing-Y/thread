package com.haut.thread;

import java.util.ArrayList;
import java.util.Vector;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * arrarylist 线程不安全，可以使用vector来代替
 */
public class ArrayListMultiThread {

    //static ArrayList<Integer> arrayList = new ArrayList<>();
    static Vector<Integer> arrayList = new Vector<>();

    public static class AddThread implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                arrayList.add(i);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new AddThread());
        Thread thread1 = new Thread(new AddThread());
        thread.start();
        thread1.start();
        thread.join();
        thread1.join();
        System.out.println(ArrayListMultiThread.arrayList.size());
    }


}
