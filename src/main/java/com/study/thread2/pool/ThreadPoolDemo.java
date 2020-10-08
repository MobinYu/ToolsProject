package com.study.thread2.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo {

	public static void main(String[] args) {
		
		ExecutorService cachedExecutorService = Executors.newCachedThreadPool();
		
		ExecutorService fixedExecutorService = Executors.newFixedThreadPool(1);
		
		ExecutorService singleExecutorService = Executors.newSingleThreadExecutor();
		
		ExecutorService schedExecutorService = Executors.newScheduledThreadPool(1);
	}
}
