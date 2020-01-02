package com.study.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOServer3 {
	
	private static Charset charset = Charset.forName("UTF-8");
	
	public static void main(String[] args) {
		int port = 9010;
		int threads = 100;
		ExecutorService tpool = Executors.newFixedThreadPool(threads);
		try {
			ServerSocket ss = new ServerSocket(port);
			
			while(true) {
				//接收链接
				Socket s = ss.accept();
				
				//丢到线程池去跑
				tpool.execute(new ScoketProcess(s));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		tpool.shutdown();
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
