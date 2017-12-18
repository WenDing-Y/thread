package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class RealData implements Data {
    String result;

    public RealData(String para) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            buffer.append(para);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        result = buffer.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
