package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 在多线程下可能会受影响
 */
public class LazySingleton {

    private LazySingleton() {
        System.out.println("create");
    }

    private static LazySingleton instance = null;

    public static synchronized LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }

    public static void main(String[] args) {
        LazySingleton.getInstance();
    }
}
