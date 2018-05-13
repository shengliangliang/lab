package com.king.netty.serializable.messagepack;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;


public class EchoClientHandler extends ChannelHandlerAdapter {
    private final int sendNumber;

    public EchoClientHandler(int sendNumber){
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] infos = UserInfo();
        for (UserInfo infoE:infos){
            ctx.write(infoE);
        }
        ctx.flush();

        /*byte[] req = "hello".getBytes();
        ByteBuf msg = Unpooled.buffer(req.length);
        msg.writeBytes(req);
        ctx.writeAndFlush(msg);*/

    }

    private UserInfo[] UserInfo(){
        UserInfo[] userInfos = new UserInfo[sendNumber];
        UserInfo userInfo = null;

        for(int i=0;i<sendNumber;i++){
            userInfo = new UserInfo();
            userInfo.setUserId(i);
            userInfo.setUserName("ABCDEFG--->"+i);
            userInfos[i] = userInfo;
        }
        return userInfos;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        /*System.out.println("Client receive the msgpack message :" + msg);
        ctx.write(msg);*/

        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println("client : "+body);

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
