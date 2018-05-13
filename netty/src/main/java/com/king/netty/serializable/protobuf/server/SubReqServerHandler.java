package com.king.netty.serializable.protobuf.server;

import com.king.netty.serializable.protobuf.SubscribeReqProto;
import com.king.netty.serializable.protobuf.SubscribeRespProto;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class SubReqServerHandler extends ChannelHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        SubscribeReqProto.SubscribeReq req = (SubscribeReqProto.SubscribeReq)msg;
        if("".equalsIgnoreCase(req.getUserName())){
            System.out.println("req:"+req.toString());
            ctx.writeAndFlush(resp(req.getSubReqId()));
        }

    }

    private SubscribeRespProto.SubscribeResp resp(int subReqId) {
        SubscribeRespProto.SubscribeResp.Builder builder = SubscribeRespProto.SubscribeResp.newBuilder();

        builder.setReqReqId(subReqId);
        builder.setRespCode(10);
        builder.setDesc("Netty book order succeed,3 days later,sent to the designated address");
        return builder.build();
    }
}
