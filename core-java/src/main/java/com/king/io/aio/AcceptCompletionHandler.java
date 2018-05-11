package com.king.io.aio;

import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public class AcceptCompletionHandler implements CompletionHandler<AsynchronousSocketChannel, AsyncTimeServerHandler> {
    @Override
    public void completed(AsynchronousSocketChannel asynchronousSocketChannel, AsyncTimeServerHandler asyncTimeServerHandler) {
        asyncTimeServerHandler.asynchronousServerSocketChannel.accept(asyncTimeServerHandler, this);

        ByteBuffer buffer = ByteBuffer.allocate(1024);

        asynchronousSocketChannel.read(buffer, buffer, new ReadCompletionHandler(asynchronousSocketChannel));

    }

    @Override
    public void failed(Throwable throwable, AsyncTimeServerHandler asyncTimeServerHandler) {
        throwable.printStackTrace();

        asyncTimeServerHandler.latch.countDown();

    }
}
