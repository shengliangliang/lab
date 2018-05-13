package com.king.netty.serializable.messagepack.test;

import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;

public class Main1 {
    @Message // Annotation
    public static class MyMessage {
        // public fields are serialized.
        public String name;
        public double version;
    }

    public static void main(String[] args) throws Exception {
        MyMessage src = new MyMessage();
        src.name = "msgPack";
        src.version = 0.6;

        MessagePack msgpack = new MessagePack();
        // Serialize
        byte[] bytes = msgpack.write(src);
        // Deserialize
        MyMessage dst = msgpack.read(msgpack.write(src), MyMessage.class);

        System.out.println(src.equals(dst));
        System.out.println(dst.name);
        System.out.println(dst.version);


    }
}
