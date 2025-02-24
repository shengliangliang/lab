package com.king.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        try (Selector selector = Selector.open();
             ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            // 配置服务器套接字通道为非阻塞模式
            serverSocketChannel.configureBlocking(false);
            // 绑定服务器端口
            serverSocketChannel.socket().bind(new InetSocketAddress(PORT));
            // 将服务器套接字通道注册到选择器上，监听连接事件
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            System.out.println("Server started on port " + PORT);

            while (true) {
                // 阻塞等待有事件发生
                selector.select();
                // 获取所有已就绪的选择键
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isAcceptable()) {
                        // 处理新的连接
                        ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                        SocketChannel socketChannel = serverChannel.accept();
                        socketChannel.configureBlocking(false);
                        // 将新的套接字通道注册到选择器上，监听读事件
                        socketChannel.register(selector, SelectionKey.OP_READ);
                        System.out.println("New connection accepted: " + socketChannel.getRemoteAddress());
                    } else if (key.isReadable()) {
                        // 处理读事件
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = socketChannel.read(buffer);
                        if (bytesRead > 0) {
                            buffer.flip();
                            byte[] data = new byte[buffer.remaining()];
                            buffer.get(data);
                            String message = new String(data);
                            System.out.println("Received message: " + message);
                            // 简单地将收到的消息原样返回给客户端
                            ByteBuffer responseBuffer = ByteBuffer.wrap(message.getBytes());
                            socketChannel.write(responseBuffer);
                        } else if (bytesRead == -1) {
                            // 客户端关闭连接
                            socketChannel.close();
                            System.out.println("Connection closed");
                        }
                    }
                    // 处理完一个选择键后，将其从集合中移除
                    keyIterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
