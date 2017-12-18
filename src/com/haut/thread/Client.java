package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class Client {

    public Data request(String str) {
        FutureDate futureDate = new FutureDate();
        new Thread() {
            @Override
            public void run() {
                RealData realData = new RealData(str);
                futureDate.setRealData(realData);
            }
        }.start();
        return futureDate;
    };

}
