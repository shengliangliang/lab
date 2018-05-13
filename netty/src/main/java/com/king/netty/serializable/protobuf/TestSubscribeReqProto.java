package com.king.netty.serializable.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class TestSubscribeReqProto {
    private static byte[] encode(SubscribeReqProto.SubscribeReq req){
        return req.toByteArray();
    }

    private static SubscribeReqProto.SubscribeReq decode(byte[] body) throws InvalidProtocolBufferException {
        return SubscribeReqProto.SubscribeReq.parseFrom(body);
    }

    private static SubscribeReqProto.SubscribeReq createSubscribeReq(){
        SubscribeReqProto.SubscribeReq.Builder builder = SubscribeReqProto.SubscribeReq.newBuilder();
        builder.setSubReqId(1);
        builder.setUserName("shengliangliang");
        builder.setProductName("Netty book");
        List<String> address = new ArrayList<>();

        address.add("Nanjing YuHuaTai");
        address.add("Beijing liulichang");
        address.add("Shenzhen hongshulin");

        builder.addAllAdress(address);

        return builder.build();
    }

    public static void main(String[] args) throws InvalidProtocolBufferException {
        SubscribeReqProto.SubscribeReq req = createSubscribeReq();
        System.out.println(req.toString());
        SubscribeReqProto.SubscribeReq req2 = decode(encode(req));

        System.out.println(req.toString());
        System.out.println(req2.equals(req));
    }
}
