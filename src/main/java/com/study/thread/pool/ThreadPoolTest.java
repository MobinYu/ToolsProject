package com.study.thread.pool;

import java.util.ArrayList;
import java.util.List;

public class ThreadPoolTest {

	private static volatile Integer count = 1;
	
	public static void main(String[] args) {
        ThreadPool tPool = ThreadPoolManager.getThreadPool(6);
        List<Runnable> tasks = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
//            tasks.add(new Task());
            tasks.add(new Thread(() -> 
            	System.out.println("当前处理的线程是：" + Thread.currentThread().getName() + ",执行任务：" + (count++) + "完成")
            ));
        }
        System.out.println(tPool);
        
        tPool.execute(tasks);
        
        // 所有的线程执行完成才destroy
        tPool.destroy();
        
        System.out.println(tPool);
    }
}
