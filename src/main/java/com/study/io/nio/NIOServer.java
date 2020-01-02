package com.study.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO
 * 
 * 1、NIO模型中通常会有两个线程，每个线程绑定一个轮询器selector，在我们这个例子中serverSelector负责轮询是否有新的连接，clientSelector负责轮询连接是否有数据可读
 * 2、服务端监测到新的连接之后，不再创建一个新的线程，而是直接将新连接绑定到clientSelector上，这样就不用像IO模型中1w个while循环在死等，参见(bio)
 * 3、clientSelector被一个while死循环包裹着，如果在某一时刻有多条连接有数据可读，那么通过 clientSelector.select(1)方法可以轮询出来，进而批量处理
 * 4、数据的读写以内存块为单位，参见(3)
 * 
 * @author Administrator
 * @date 2019年6月20日 上午11:25:32
 */
public class NIOServer {

	public static void main(String[] args) throws IOException {
		Selector serverSelector = Selector.open();
        Selector clientSelector = Selector.open();
        
        new Thread(() -> {
            try {
            	ServerSocketChannel listenerChannel = ServerSocketChannel.open();
            	listenerChannel.socket().bind(new InetSocketAddress(9000));
            	listenerChannel.configureBlocking(false);
            	listenerChannel.register(serverSelector, SelectionKey.OP_ACCEPT);
            	while (true) {
            		if (serverSelector.select(1) > 0) {
            			Set<SelectionKey> set = serverSelector.selectedKeys();
                        Iterator<SelectionKey> keyIterator = set.iterator();
                        
                        while (keyIterator.hasNext()) {
                            SelectionKey key = keyIterator.next();
                            if (key.isAcceptable()) {
                            	try {
                            		SocketChannel clientChannel = ((ServerSocketChannel) key.channel()).accept();
									clientChannel.configureBlocking(false);
									clientChannel.register(clientSelector, SelectionKey.OP_READ);
								} finally {
                                    keyIterator.remove();
                                }
                            }
                        }
            		}
            	}
			} catch (Exception e) {
				e.printStackTrace();
			}
        }).start();
        
        
        new Thread(() -> {
			try {
				while (true) {
					// (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为1ms
					if (clientSelector.select(1) > 0) {
						Set<SelectionKey> set = clientSelector.selectedKeys();
						Iterator<SelectionKey> keyIterator = set.iterator();
						while (keyIterator.hasNext()) {
							SelectionKey key = keyIterator.next();
							if (key.isReadable()) {
								try {
									SocketChannel clientChannel = (SocketChannel) key.channel();
									ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
									// (3) 读取数据以块为单位批量读取
									clientChannel.read(byteBuffer);
									byteBuffer.flip();
									System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer).toString());
								} finally {
									keyIterator.remove();
									key.interestOps(SelectionKey.OP_READ);
								}
							}
						}
					}
				}
			} catch (IOException ignored) {
			}
		}).start();
        
	}
}
