package com.study.io.netty.demo;

public class TestServerMain {

	public static void main(String[] args) {
		int port = 9999;
	    new Thread(() -> {
	        new ImServer().run(port);
	    }).start();
	}
}
