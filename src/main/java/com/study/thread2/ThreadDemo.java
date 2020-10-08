package com.study.thread2;
/**
 * 线程终止
 */
public class ThreadDemo {

	public volatile static boolean flag = true;
	
	public static void main(String[] args) throws InterruptedException {
//		flagRunAndStop();
		
		testInterrupt();
	}

	/**
	 * 标志位终止线程
	 */
	private static void flagRunAndStop() throws InterruptedException {
		new Thread(() -> {
			try {
				while (flag) {
					System.out.println("运行中。。。");
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}).start();
		
		Thread.sleep(3000);
		flag = false;
		System.out.println("结束运行！");
	}
	
	/**
	 * Interrupt终止线程
	 * @throws InterruptedException 
	 */
	public static void testInterrupt() throws InterruptedException {
		Thread t = new Thread(() -> {
			for (int i = 0; i <= 20000; i++) {
				System.out.println("i=" + i);
				
				if (Thread.currentThread().isInterrupted()) {
					break;
				}
			}
		});
		t.start();
		t.sleep(100);
		t.interrupt();
		System.out.println("结束运行！");
	}
}
