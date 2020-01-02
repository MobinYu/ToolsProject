package com.study.io.bio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class IOClient {
	public static void main(String[] args) {
		new Thread(() -> {
			try {
				Socket socket = new Socket("127.0.0.1", 9000);
				while (true) {
					try {
						Scanner sc = new Scanner(System.in);
						String str = sc.next();
						socket.getOutputStream().write((new Date() + ": " + str).getBytes());
						socket.getOutputStream().flush();
//						Thread.sleep(2000);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}

}
