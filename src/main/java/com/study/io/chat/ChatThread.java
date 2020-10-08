package com.study.io.chat;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ChatThread {
    /**
     * 发送方
     */
    class UDPSend implements Runnable {
        //1、创建udp服务。通过DatagramSocket对象。
        private DatagramSocket ds;
        public UDPSend(DatagramSocket ds) {
            this.ds = ds;
        }

        @Override
        public void run() {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                ds = new DatagramSocket();
                String line = null;
                while ((line = br.readLine()) != null) {
                    byte[] buf = line.getBytes();
                    // 2、确定数据，并封装成数据包
                    DatagramPacket dp = new DatagramPacket(buf, buf.length, InetAddress.getByName("127.0.0.1"), 9001);
                    // 3、通过socket服务，将已有的数据包发送出去。通过send方法
                    ds.send(dp);

                    if ("over".equals(line)) {
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (ds != null) {
                    // 4、关闭资源
                    ds.close();
                }
            }
        }
    }

    class UDPReceive implements Runnable {
        //1、创建udp socket，建立端点
        DatagramSocket ds = null;
        public UDPReceive(DatagramSocket ds) {
            this.ds = ds;
        }
        
        @Override
        public void run() {
            try {
                while (true) {
                    // 2、定义数据包。用于存储数据
                    byte[] buf = new byte[1024];
                    DatagramPacket dp = new DatagramPacket(buf, buf.length);
                    // 3、通过服务的receive方法将收到数据存入数据包中。
                    ds.receive(dp);// 阻塞式方法。
                    // 4、通过数据包的方法获取其中的数据。
                    String ip = dp.getAddress().getHostAddress();
                    String data = new String(dp.getData(), 0, dp.getLength());
                    System.out.println(ip + ">> " + data);
                    
                    if (data.equals("over")) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                if (ds != null)
                    // 5、关闭资源
                    ds.close();
            }
        }
    }
}
