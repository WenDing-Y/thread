package com.haut.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * jdk中的future模式
 */
public class JdkFutureMain {

    public static void main(String[] args) {
        FutureTask<String> futureTask = new FutureTask<String>(new FutureCallableImpl("abc"));
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        //提交后立即返回
        executorService.submit(futureTask);
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
