package com.study.io;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.charset.Charset;

public class SocketClient {

	public static void main(String[] args) {
		Socket socket = null;
		try {
			socket = new Socket("localhost", 9010);
			
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
