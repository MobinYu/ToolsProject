package com.study.io;

import java.util.concurrent.CountDownLatch;

public class ThreadMMDemo {

	public static void main(String[] args) {
		final CountDownLatch cd1 = new CountDownLatch(1);
		try {
			Thread.sleep(20000L); //20秒休眠后 再创建线程
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (int i = 0; i < 5000; i++) {
			new Thread(new Runnable() {
				
				public void run() {
					try {
						cd1.await(); //阻塞
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
			}).start();
			
			System.out.println("i=" + i);
		}
	}

}
