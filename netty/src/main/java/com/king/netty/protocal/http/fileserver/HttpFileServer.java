package com.king.netty.protocal.http.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpFileServer {

    private void bind(String host,int port,String url) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        try{
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workerGroup)
                    .option(ChannelOption.SO_BACKLOG,2000)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            // 请求消息解码器
                            channel.pipeline().addLast("http-decoder",new HttpRequestDecoder());
                            // 目的是将多个消息转换为单一的request或者response对象
                            channel.pipeline().addLast("http-aggregator",new HttpObjectAggregator(65536));

                            //响应解码器
                            channel.pipeline().addLast("http-encoder",new HttpResponseEncoder());
                            //目的是支持异步大文件传输（）
                            channel.pipeline().addLast("http-chunked",new ChunkedWriteHandler());
                            channel.pipeline().addLast("fileServerHandler",new HttpFileServerHandler(url));
                        }
                    });

            ChannelFuture channelFuture = serverBootstrap.bind(host,port).sync();
            System.out.println("HTTP文件目录服务器启动，网址是 : " + "http://"+host+":"+ port + url);
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }


    public static void main(String[] args) throws InterruptedException {
        HttpFileServer httpFileServer = new HttpFileServer();
        httpFileServer.bind("127.0.0.1",8080,"/filedir");
    }
}
