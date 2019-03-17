package com.study.locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lock锁，实现售票
 * 模拟线程下资源竞争
 * 
 * @author Super Yu
 * @Date 2019-03-17
 */
public class SellTicket_Lock implements Runnable {

	private int tickets = 100;
	
	Lock lock = new ReentrantLock(); //申请一个锁
	
	@Override
	public void run() {
		while (tickets > 0) {
			lock.lock();
			try {
				if (tickets > 0) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + "正在出售第 " + tickets-- + " 张票");
				}
			} finally {
				lock.unlock();
			}
		}
	}
	
	/* 1、不会有超卖和重复卖的情况 */
	/* 2、会变慢（每个线程sleep 100 毫秒） */
	/* 3、比synchronized锁要多些操作（申请锁，释放锁） */

}
