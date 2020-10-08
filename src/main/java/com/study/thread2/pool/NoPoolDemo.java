package com.study.thread2.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NoPoolDemo {

	public static void main(String[] args) throws InterruptedException {
	    Long start = System.currentTimeMillis();
	    final Random radom = new Random();
	    
	    final List<Integer> list = new ArrayList<Integer>();
	    
	    for (int i = 0; i < 1000; i++) {
	        Thread t = new Thread(new Runnable() {
	            @Override
	            public void run() {
	                synchronized (list) {
	                    list.add(radom.nextInt());
	                }
	            }
	        });
	        
	        t.start();
	        t.join();
	    }
	    
	    System.out.println("时间： " + (System.currentTimeMillis() - start));
	    System.out.println("大小： " + list.size());
	}
}
