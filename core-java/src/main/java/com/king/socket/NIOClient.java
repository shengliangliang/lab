package com.king.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOClient {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8889;

    public static void main(String[] args) {
        try (Selector selector = Selector.open()) {
            // 创建多个 SocketChannel 并连接到服务器
            for (int i = 0; i < 2; i++) {
                SocketChannel socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(false);
                socketChannel.connect(new InetSocketAddress(SERVER_HOST, SERVER_PORT));
                // 注册连接事件
                socketChannel.register(selector, SelectionKey.OP_CONNECT);
            }

            while (true) {
                // 阻塞等待事件发生
                selector.select();
                // 获取所有已就绪的选择键
                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                while (keyIterator.hasNext()) {
                    SelectionKey key = keyIterator.next();

                    if (key.isConnectable()) {
                        // 处理连接完成事件
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        if (socketChannel.finishConnect()) {
                            System.out.println("Connected to server: " + socketChannel.getRemoteAddress());
                            // 注册读事件和写事件
                            key.interestOps(SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                            // 发送消息到服务器
                            String message = "message for test\n";
                            ByteBuffer buffer = ByteBuffer.wrap(message.getBytes());
                            socketChannel.write(buffer);
                        }
                    } else if (key.isReadable()) {
                        // 处理读事件
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int bytesRead = socketChannel.read(buffer);
                        if (bytesRead > 0) {
                            buffer.flip();
                            byte[] data = new byte[buffer.remaining()];
                            buffer.get(data);
                            String response = new String(data);
                            System.out.println("Received response from server: " + response);
                        }
                    } else if (key.isWritable()) {
                        // 处理写事件（这里可以根据需要添加更多逻辑）
                        // 例如可以循环发送消息

                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        for(int i=0;i<10;i++){
                            String newMessage = "Another message from client.\n";
                            ByteBuffer buffer = ByteBuffer.wrap(newMessage.getBytes());
                            socketChannel.write(buffer);
                        }

                        // 可以修改感兴趣的事件，避免一直触发写事件
                        key.interestOps(SelectionKey.OP_READ);
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
