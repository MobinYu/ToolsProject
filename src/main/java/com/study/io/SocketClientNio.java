package com.study.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SocketClientNio {
	
	private static Charset charset = Charset.forName("UTF-8");
	
	public static void main(String[] args) {
		try (SocketChannel sc = SocketChannel.open();){
			//连接 会阻塞
			boolean isConnect = sc.connect(new InetSocketAddress("localhost",9011));
			System.out.println("是否连接：" + isConnect);
			
			//客户端发数据
			Scanner sn = new Scanner(System.in);
			System.out.println("输入内容：");
			
			String msg = sn.nextLine();
			ByteBuffer bf = ByteBuffer.wrap(msg.getBytes(charset));
			
			while (bf.hasRemaining()) {
				sc.write(bf);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
