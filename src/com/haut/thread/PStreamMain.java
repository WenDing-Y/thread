package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 流水线处理，利用多核的优势
 */
public class PStreamMain {

    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Dive()).start();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                Msg msg = new Msg(i, j);
                Plus.queue.add(msg);
            }
        }
    }
}
