package com.yu;

import java.util.concurrent.TimeUnit;

public class TestVolatile implements Runnable{
	
	private static boolean is = true;
	
	@Override
	public void run() {
		int i = 0;
		while (TestVolatile.is) {
//			synchronized (TestVolatile.class) {
				i ++;
//			}
		}
		System.out.println(i);
		
	}
	
	public static void main(String[] args) {
		TestVolatile testVolatile = new TestVolatile();
//		for (int i = 0; i < 3; i++) {
//		}
		new Thread(testVolatile).start();
		
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		TestVolatile.is = false;
		System.out.println("被至为false了");
	}

}
