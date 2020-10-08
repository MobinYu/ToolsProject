package com.study.io.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatSend {

	public static void main(String[] args) {
		DatagramSocket ds = null;
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		try {
			//1，创建udp服务。通过DatagramSocket对象。
			ds = new DatagramSocket();
			String line = null;
			while((line = br.readLine()) != null) {
				byte[] buf = line.getBytes();
				//2，确定数据，并封装成数据包。DatagramPacket(byte[] buf,int length, InetAddress address, int port) 
				DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), 9001);
				//3，通过socket服务，将已有的数据包发送出去。通过send方法 
				ds.send(dp);
				
				if("over".equals(line)) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(ds != null) {
				//4，关闭资源
				ds.close();
			}
		}
	}
}
