package com.study.io.netty.demo;

import io.netty.channel.Channel;

public class TestClientMain {

	public static void main(String[] args) {
		String host = "127.0.0.1";
	    int port = 9999;
	    Channel channel = new ImClient().connect(host, port);
	    channel.writeAndFlush("yinjihuan");
	}
}
