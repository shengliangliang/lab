package com.king.netty.linebasedframedecoder.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.io.UnsupportedEncodingException;


public class TimeServerHandler extends ChannelHandlerAdapter {


    private int counter;
    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws UnsupportedEncodingException {
        /*ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8").substring(0,req.length-System.getProperty("line.separator").length());
        System.out.println("The time server receive order:"+body+";the counter is :"+ ++counter);

        String currentTime = "Query Time Order".equalsIgnoreCase(body)?new java.util.Date(System.currentTimeMillis()).toString():"Bad Order";

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(resp);*/

        String body = (String)msg;
        System.out.println("The time server receive order:"+body+";the counter is :"+ ++counter);
        String currentTime = "Query Time Order".equalsIgnoreCase(body)?new java.util.Date(System.currentTimeMillis()).toString():"Bad Order";
        currentTime = currentTime + System.getProperty("line.separator");

        ByteBuf resp = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resp);
    }
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx){
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
