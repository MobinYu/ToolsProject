package com.study.thread;

public class TestThread {

	public static void main(String[] args) {
		
		for (int i = 1; i <= 5; i++) {
			final int a = i;
			new Thread(() -> System.out.println("t"+a+"-->"+a)).start(); //lambda表达式启动线程
		}
	}
}
