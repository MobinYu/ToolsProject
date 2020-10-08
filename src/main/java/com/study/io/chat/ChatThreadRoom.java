package com.study.io.chat;

import java.net.DatagramSocket;

public class ChatThreadRoom {
    public static void main(String[] args) throws Exception {
        //定义两个DatagramSocket服务
        DatagramSocket send = new DatagramSocket();
        DatagramSocket receive = new DatagramSocket(9001);
        //开启两个线程
        new Thread(new ChatThread().new UDPSend(send)).start();
        new Thread(new ChatThread().new UDPReceive(receive)).start();
    }
}
