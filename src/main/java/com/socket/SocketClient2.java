package com.socket;

import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SocketClient2 implements Runnable{
	
	private int port;
	
	private String host;
	
	private Charset charset = Charset.forName("UTF-8");
	
	public SocketClient2(String host, int port){
		super();
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		//try(资源)，自动资源释放（java7新特性），不然得显示调用资源得close()方法
		try (Socket s = new Socket(host, port);OutputStream out = s.getOutputStream();) {
//		try {
//			Socket s = new Socket(host, port);OutputStream out = s.getOutputStream();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入：");
			String mess = scanner.nextLine();
			
			out.write(mess.getBytes(charset));
			
//			out.close();
//			s.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		SocketClient2 client = new SocketClient2("localhost", 9011);
		client.run();
	}
	
}
