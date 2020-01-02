package com.study.io.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * https://www.jianshu.com/p/a4e03835921a
 * https://blog.51cto.com/13732225/2311676
 * 
 * @author Administrator
 * @date 2019年6月20日 下午1:51:56
 */
public class NettyServer {
	public static void main(String[] args) {
		
		//定义一对线程组
		//boos为主线程，用于接受客户端的连接，但是不做任何处理，跟老板一样，不做事
		//worker为从线程组，老板线程组会把任务丢给他，让手下线程组去做任务
		NioEventLoopGroup boos = new NioEventLoopGroup();
		NioEventLoopGroup worker = new NioEventLoopGroup();
		
		//netty服务器的创建，ServerBootstrap是一个启动类
		ServerBootstrap serverBootstrap = new ServerBootstrap();
		
		serverBootstrap.group(boos, worker)  //设置主从线程组
			.channel(NioServerSocketChannel.class)  //设置nio的双向通道
			.childHandler(new ChannelInitializer<NioSocketChannel>() {  //子处理器,用于处理workerGroup
				protected void initChannel(NioSocketChannel ch) {
					ch.pipeline().addLast(new StringDecoder());
					ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
						@Override
						protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
							System.out.println(msg);
						}
					});
				}
			}).bind(9000); //启动server,并且设置8088为启动的端口号，可通过.sync()设置为同步方式
		
		//监听关闭的channel，设置为同步方式
		//channelFuture.channel().closeFuture().sync();
	}
}
