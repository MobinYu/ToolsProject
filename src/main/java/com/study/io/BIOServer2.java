package com.study.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class BIOServer2 {
	
	private static Charset charset = Charset.forName("UTF-8");
	
	public static void main(String[] args) {
		int port = 9010;
		try {
			ServerSocket ss = new ServerSocket(port);
			
			while(true) {
				//接收链接
				Socket s = ss.accept();
				
				//新开一个线程去处理这个连接
				new Thread(new ScoketProcess(s)).start();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	static class ScoketProcess implements Runnable{
		Socket s;
		
		public ScoketProcess(Socket s){
			super();
			this.s = s;
		}
		
		public void run() {
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(), charset));
				
				String mess = null;
				//接收数据
				while ((mess = reader.readLine()) != null) {
					System.out.println(mess);
				}
				
				s.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
