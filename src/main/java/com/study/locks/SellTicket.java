package com.study.locks;

/**
 * 售票
 * 模拟线程下资源竞争
 * 
 * @author Super Yu
 * @Date 2019-03-17
 */
public class SellTicket implements Runnable {

	private int tickets = 100;
	
	@Override
	public void run() {
		while (tickets > 0) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + "正在出售第 " + tickets-- + " 张票");
		}
		
	}
	
	/* 会有超卖和重复卖的情况*/

}
