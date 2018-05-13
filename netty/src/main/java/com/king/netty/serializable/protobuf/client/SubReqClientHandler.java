package com.king.netty.serializable.protobuf.client;

import com.king.netty.serializable.protobuf.SubscribeReqProto;
import com.king.netty.serializable.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.ArrayList;
import java.util.List;


public class SubReqClientHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for (int i=0;i<10;i++){
            ctx.write(subReq(i));
        }
        ctx.flush();
    }

    private SubscribeReqProto.SubscribeReq subReq(int i) {
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(i);
        builder.setUserName("shengll");
        builder.setProductName("Netty");

        List<String> address = new ArrayList<>();

        address.add("NanJing");
        address.add("beijing");

        builder.addAllAdress(address);
        return builder.build();

    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
