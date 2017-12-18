package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class StaticSingleton {

    private StaticSingleton() {
        System.out.println("create");
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
