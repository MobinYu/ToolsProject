package com.study.thread2;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class RunAfterAllReady {
	
	private static void runAfterAllReady() {
		final Random random = new Random();
		int worker = 3;
		CyclicBarrier cyclicBarrier = new CyclicBarrier(worker);

		for (char threadName = 'A'; threadName <= 'C'; threadName++) {
			final String tN = String.valueOf(threadName);
			new Thread(new Runnable() {
				@Override
				public void run() {
					long prepareTime = random.nextInt(10000) + 100;
					System.out.println(tN + " 线程准备时间 " + prepareTime);

					try {
						Thread.sleep(prepareTime);
					} catch (Exception e) {
						e.printStackTrace();
					}

					try {
						System.out.println(tN + " 线程已经准备好，正在等待其他线程");
						cyclicBarrier.await(); // 准备好的线程已经到达栅栏
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (BrokenBarrierException e) {
						e.printStackTrace();
					}

					// 所以线程已经准备好，同时开始执行
					System.out.println(tN + " 线程开始执行");
				}
			}).start();
		}
	}

	public static void main(String[] args) {
		runAfterAllReady();
	}
}
