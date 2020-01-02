package com.study.locks;

/**
 * synchronized锁，实现售票
 * 模拟线程下资源竞争
 * 
 * @author Super Yu
 * @Date 2019-03-17
 */
public class SellTicket_Sync implements Runnable {

	private int tickets = 100;
	
	@Override
	public void run() {
		while (tickets > 0) {
			synchronized (this) {
				if (tickets > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "正在出售第 " + tickets-- + " 张票");
				}
			}
		}
	}
	
	/* 1、不会有超卖和重复卖的情况 */
	/* 2、会变慢（每个线程sleep 100 毫秒） */
}
