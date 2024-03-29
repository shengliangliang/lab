package com.king.io.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyTimeClient {

    public void connect(int port,String host){

        System.out.println("配置客户端NIO线程组");
        EventLoopGroup group = new NioEventLoopGroup();
        try{
            Bootstrap b = new Bootstrap();
            b.group(group).channel(NioSocketChannel.class)
                    .option(ChannelOption.TCP_NODELAY,true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new TimeClientHandler());
                        }
                    });
            System.out.println("发起异步连接操作");
            ChannelFuture f = b.connect(host,port).sync();

            System.out.println("等待客户端链路关闭");
            f.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println("客户端退出");
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new NettyTimeClient().connect(8080,"127.0.0.1");
    }
}
