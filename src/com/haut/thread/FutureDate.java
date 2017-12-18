package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * future 模式的简单实现
 */
public class FutureDate implements Data {
    RealData realData = null;
    boolean isReady = false;

    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                System.out.println("start wait" + System.currentTimeMillis());
                wait();
                System.out.println("finish wait" + System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.result;
    }
}
