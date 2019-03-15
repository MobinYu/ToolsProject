package com.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Scanner;

public class SocketClient {
	
	private int port;
	
	private String host;
	
	private Charset charset = Charset.forName("UTF-8");
	
	public SocketClient(String host, int port){
		super();
		this.host = host;
		this.port = port;
	}
	
	public void run(){
		try {
			Socket s = new Socket(host, port);
			OutputStream out = s.getOutputStream();
			
			Scanner scanner = new Scanner(System.in);
			System.out.println("请输入：");
			String mess = scanner.nextLine();
			
			out.write(mess.getBytes(charset));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public static void main(String[] args) {
//		SocketClient client = new SocketClient("192.168.253.147", 9010);
//		client.run();
//	}

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("192.168.253.147", 9010);
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			
            System.out.print("请输入：");
            
            String str = new BufferedReader(new InputStreamReader(System.in, "UTF-8")).readLine();
            
            //out.writeUTF(str); //直接写字符串
            
            out.write(str.getBytes(Charset.forName("UTF-8")));
			
            out.close();
            
		} catch (Exception e) { //UnknownHostException,IOException
			e.printStackTrace();
		}
	}
	
}
