package com.study.io.chat;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChatReceive {
	public static void main(String[] args) {
		DatagramSocket ds = null;
		DatagramPacket dp = null;
		try {
			// 1,创建udp socket，建立端点。
			ds = new DatagramSocket(9001);
			while (true) { // 2,定义数据包。用于存储数据
				byte[] buf = new byte[1024];
				dp = new DatagramPacket(buf, buf.length);
				// 3，通过服务的receive方法将收到数据存入数据包中。
				ds.receive(dp);// 阻塞式方法。
				// 4，通过数据包的方法获取其中的数据。
				String ip = dp.getAddress().getHostAddress();
				String data = new String(dp.getData(), 0, dp.getLength());
				System.out.println(ip + "......" + data);
				if (data.equals("over"))
					break;
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			if (ds != null)
				// 5，关闭资源
				ds.close();
		}
	}
}
