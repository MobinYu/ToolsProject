package com.study.io;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * BIO同步阻塞IO
 * （1是串行版本，2、3是多线程版本）
 * @author Super Yu
 *
 */
public class BIOServer1 {
    
    private static Charset charset = Charset.forName("UTF-8");
    
    public static void main(String[] args) {
        int port = 9010;
        try {
            ServerSocket ss = new ServerSocket(port);
            
            while(true) {
                //接收连接（等待客户端连接时，阻塞）
                Socket s = ss.accept();
                
                BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream(), charset));
                
                String mess = null;
                //接收数据（等待客户端发送数据时，阻塞）
                while ((mess = reader.readLine()) != null) {
                    System.out.println(mess);
                    
                    // 响应结果 200（支持浏览器访问）
                    OutputStream outputStream = s.getOutputStream();
                    outputStream.write("HTTP/1.1 200 OK\r\n".getBytes());
                    outputStream.write("Content-Length: 11\r\n\r\n".getBytes());
                    outputStream.write("Hello World".getBytes());
                    outputStream.flush();
                }
                
                s.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
