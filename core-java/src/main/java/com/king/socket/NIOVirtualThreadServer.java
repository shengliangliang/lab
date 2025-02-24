package com.king.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NIOVirtualThreadServer {

    private static final int PORT = 8889;

    public static void main(String[] args) {
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            try (var serverChannel = ServerSocketChannel.open()) {
                serverChannel.bind(new InetSocketAddress(PORT));
                System.out.println("Server started on port " + PORT);

                while (true) {
                    SocketChannel clientChannel = serverChannel.accept();
                    executor.submit(() -> handleClient(clientChannel));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(SocketChannel clientChannel) {
        try (clientChannel) {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead;
            while ((bytesRead = clientChannel.read(buffer)) > 0) {
                buffer.flip();
                byte[] data = new byte[buffer.remaining()];
                buffer.get(data);
                String message = new String(data);
                System.out.println("Received message: " + message);
                // 简单地将收到的消息原样返回给客户端
                ByteBuffer responseBuffer = ByteBuffer.wrap(message.getBytes());
                clientChannel.write(responseBuffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
