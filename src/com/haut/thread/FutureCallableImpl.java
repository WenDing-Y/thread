package com.haut.thread;

import java.util.concurrent.Callable;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class FutureCallableImpl implements Callable<String> {
    private String para;

    public FutureCallableImpl(String para) {
        this.para = para;
    }

    @Override
    public String call() throws Exception {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(para);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return buffer.toString();
    }
}
