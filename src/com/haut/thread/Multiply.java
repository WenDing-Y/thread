package com.haut.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class Multiply implements Runnable {
    public static BlockingQueue<Msg> queue = new LinkedBlockingQueue<>();

    @Override
    public void run() {
        while (true) {
            Msg msg = null;
            try {
                msg = queue.take();
                msg.i = msg.i * msg.j;
                Dive.queue.add(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
