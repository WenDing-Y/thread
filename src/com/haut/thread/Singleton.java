package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 不能控制singleton实例化的时间
 */
public class Singleton {

    public static int STAUS = 1;

    private Singleton() {
        System.out.println("create");
    }

    private static Singleton singleton = new Singleton();

    public static Singleton getInstance() {
        return singleton;
    }

    public static void main(String[] args) {
        System.out.println(Singleton.STAUS);
    }
}
