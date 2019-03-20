package com.study.locks;

public class SellTicketTest {

	public static void main(String[] args) {
//		SellTicket st = new SellTicket();
		
//		SellTicket_Sync st = new SellTicket_Sync();
//		SellTicket_Lock st = new SellTicket_Lock();
		SellTicket_Redisson st = new SellTicket_Redisson();
		
		//三个线程模拟三个售票窗口
		for (int i = 1; i <= 3; i++) {
			new Thread(st, "窗口" + i).start();
		}
	}
}
