package com.yu;

import java.util.concurrent.TimeUnit;

public class TestVolatile2 {
	
	private static boolean is = true;
//	private static volatile boolean is = true;
	
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				int i = 0;
				while (TestVolatile2.is) {
					synchronized (TestVolatile2.class) {
						i ++;
					}
				}
				System.out.println(i);
			}
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		TestVolatile2.is = false;
		System.out.println("被至为false了");
	}

}
