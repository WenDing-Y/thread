package com.haut.thread;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * 在执行其它任务过程中，真实数据构造完成
 */
public class FutureMain {

    public static void main(String[] args) {
        Client client = new Client();
        Data data = client.request("abc");
        try {
            //模拟做其它的操作
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(data.getResult());
    }
}
