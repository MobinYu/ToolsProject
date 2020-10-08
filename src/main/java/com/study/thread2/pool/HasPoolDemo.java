package com.study.thread2.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HasPoolDemo {

    public static void main(String[] args) throws InterruptedException {
        Long start = System.currentTimeMillis();
        final Random radom = new Random();
        
        final List<Integer> list = new ArrayList<Integer>();
        
        ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
        
        for (int i = 0; i < 1000; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    synchronized (list) {
                        list.add(radom.nextInt());
                    }
                }
            });
            
//            singleExecutorService.execute(t);s
            singleExecutorService.submit(t);
        }
        
        singleExecutorService.shutdown();
        singleExecutorService.awaitTermination(1, TimeUnit.DAYS);
        
        System.out.println("时间： " + (System.currentTimeMillis() - start));
        System.out.println("大小： " + list.size());
    }
}
