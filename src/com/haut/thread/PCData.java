package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 */
public class PCData {
    private int intData = 0;

    public PCData(int d) {
        intData = d;
    }

    public int getIntData() {
        return intData;
    }

    @Override
    public String toString() {
        return "PCData{" +
                "intData=" + intData +
                '}';
    }
}
