package com.haut.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author xxx_xx
 * @date 2017/12/17
 * hashmap 线程不安全，用concurrenthashmap 代替
 */
public class HashMapMultiThread {

    //static Map<String, String> map = new HashMap<String, String>();
    static Map<String, String> map = new ConcurrentHashMap<>();

    public static class AddThread implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                map.put(String.valueOf(i), String.valueOf(i));
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
        System.out.println(HashMapMultiThread.map.size());
    }
}
