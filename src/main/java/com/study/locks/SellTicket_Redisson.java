package com.study.locks;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.config.Config;

public class SellTicket_Redisson implements Runnable {
	
	private int tickets = 100;
	
	RLock lock = getRedissonLock();
	
	private RLock getRedissonLock() {
		Config config = new Config();
		//指定使用单节点部署方式
		config.useSingleServer().setAddress("redis://localhost:6379");
		config.useSingleServer().setPassword("sckj");
		config.useSingleServer().setDatabase(2);
		
		Redisson redisson = (Redisson) Redisson.create(config);
		
		//获得锁对象实例
		RLock lock = redisson.getLock("keyLock");
		
		return lock;
	}

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

}
