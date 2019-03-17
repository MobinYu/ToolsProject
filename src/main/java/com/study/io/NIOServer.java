package com.study.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * NIO同步非阻塞IO
 * 
 * @author Super Yu
 *
 */
public class NIOServer {
	private static Charset charset = Charset.forName("UTF-8");
	private static CharsetDecoder decoder = charset.newDecoder();
	
	public static void main(String[] args) throws IOException {
		//1、创建一个selector
		Selector selector = Selector.open();
		
		ServerSocketChannel ssc = ServerSocketChannel.open();
		int port = 9011;
		ssc.bind(new InetSocketAddress(port));
		
		//2、注册到selector
		ssc.configureBlocking(false); //非阻塞
		
		//ssc向selector注册，监听连接到来
		ssc.register(selector, SelectionKey.OP_ACCEPT);
		
		//连接计数
		int connectionCount = 0;
		//极少量线程
		int threads = 3;
		
		//创建线程池
		ExecutorService tpool = Executors.newFixedThreadPool(threads);
		
		while (true) {
			//3、通过Selector来选择就绪的Channel
			int readyChannelCount = selector.select(); //阻塞等待就绪的事件
			
			if (readyChannelCount == 0) {
				continue;
			}
			
			//得到就绪Channel的key
			Set<SelectionKey> selectionKeys = selector.selectedKeys();
			
			Iterator<SelectionKey> keyIterators = selectionKeys.iterator();
			
			while (keyIterators.hasNext()) {
				SelectionKey key = keyIterators.next();
				
				if (key.isAcceptable()) {
					//a connection was accepted by a ServerSocketChannel.
					ServerSocketChannel ssChannle = (ServerSocketChannel) key.channel();
					//接受连接
					SocketChannel cc = ssChannle.accept();
					
					//请selector帮忙监测数据到来没有
					//设置非阻塞
					cc.configureBlocking(false);
					//向selector注册
					cc.register(selector, SelectionKey.OP_READ, ++connectionCount);
					
				} else if (key.isConnectable()) {
					//a connection was established with a remot server.
				} else if (key.isReadable()) {
					//a channel is ready for reading.
					//交给线程池去处理数据读
					tpool.execute(new SocketProcess(key));
					
					//取消Selector注册，防止线程池处理不及时，重复选择
					key.cancel();
					
				} else if (key.isWritable()) {
					//a channel is ready for writing.
				}
				
				//处理了，一定要存selectionKey中移除
				keyIterators.remove();
			}
		}
	}
	
	static class SocketProcess implements Runnable {

		SelectionKey key;
		
		public SocketProcess(SelectionKey key) {
			super();
			this.key = key;
		}

		@Override
		public void run() {
			try {
				
				String mess = readFromChannel();
				
				//attachment就是channel注册时附加的信息
				System.out.println("连接 " + key.attachment() + " 发来了：" + mess);
				
				//如果连接不需要了，就关闭
				key.channel().close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		private String readFromChannel() throws IOException{
			SocketChannel sc = (SocketChannel) key.channel();
			
			int bfsize = 1024;
			ByteBuffer rbf = ByteBuffer.allocateDirect(bfsize); //直接分配内存
			
			//定义一个更大的buffer
			ByteBuffer bigBf = null;
			
			//读的次数计数
			int count = 0;
			while (sc.read(rbf) != -1) {
				count ++;
				
				ByteBuffer temp = ByteBuffer.allocateDirect(bfsize * (count + 1));
				
				if (null != bigBf) {
					//将buffer又写转为读模式
					bigBf.flip();
					temp.put(bigBf);
				}
				
				bigBf = temp;
				
				//将这次读到的数据放入大buffer
				rbf.flip();
				bigBf.put(rbf);
				//为下次读，清理buffer
				rbf.clear();
			}
			
			if (bigBf != null) {
				bigBf.flip();
				try {
					//将字节转为字符，返回接收到的字符串
					return decoder.decode(bigBf).toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return null;
		}
	}

}
