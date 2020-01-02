package com.study.io.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class IOServer {
	public static void main(String[] args) throws Exception {
		ServerSocket serverSocket = new ServerSocket(9000);
//		// (0) 接收新连接线程，为每个客户端创建一个连接（不为每个客户端创建连接，只会为第一个连接的客户端服务）
		new Thread(() -> {
			while (true) {
				try {
					// (1) 阻塞方法获取新的连接
					Socket socket = serverSocket.accept();
					// (2) 每一个新的连接都创建一个线程，负责读取数据
					new Thread(() -> {
						try {
							byte[] data = new byte[1024];
							InputStream inputStream = socket.getInputStream();
							while (true) {	
								int len;
								// (3) 按字节流方式读取数据
								while ((len = inputStream.read(data)) != -1) {
//									System.out.println(new String(data, 0, len));
									System.out.println("你好，我是服务端，收到消息： " + new String(data, 0, len));
								}
								
							}
						} catch (IOException e) {
							e.printStackTrace();
						}
					}).start();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
