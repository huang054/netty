package com.netty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.netty.handler.DeviceInboundHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Discards any incoming data.
 */
@Component
public class DeviceServer extends Thread implements InitializingBean {
	
	private static final Logger logger = LoggerFactory.getLogger(DeviceServer.class);
    @Value("${netty.server.port}")
	private int port;
    

    
    @Override
    public void run() {
    		try {
				this.excute();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    }
    
    public void excute() throws InterruptedException {
    		
        EventLoopGroup bossGroup   = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();   // (2)
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) 		// (3)
             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
                     ch.pipeline().addLast(new DeviceInboundHandler());
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 2000)          // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true)
             .childOption(ChannelOption.SO_BACKLOG, 2000);  // (6)
    
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
            logger.info("启动DeviceServer!!");
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    

	@Override
	public void afterPropertiesSet() throws Exception {
		
		this.start();
		
	}
}