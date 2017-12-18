package com.haut.thread;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author xxx_xx
 * @date 2017/12/18
 * SimpleDateFormat.parse方法不是线程安全的
 */
public class ThreadLocalThrad {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static class ParseDate implements Runnable {
        @Override
        public void run() {
            try {
                Date date = sdf.parse("2015-09-12 02:10:12");
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 5; i++) {
           // executorService.submit(new ParseDate());
            //这种提交方法可以打印出堆栈
            executorService.execute(new ParseDate());
        }
        executorService.shutdown();
    }
}
