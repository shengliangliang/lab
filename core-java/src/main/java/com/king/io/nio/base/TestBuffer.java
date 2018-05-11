package com.king.io.nio.base;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestBuffer {
    public static void main(String[] args) {
        RandomAccessFile aFile = null;
        try {
            aFile = new RandomAccessFile(".\\src\\main\\resources\\nio-data.txt", "rw");

            FileChannel inChannel = aFile.getChannel();

            //create buffer with capacity of 48 bytes
            ByteBuffer buf = ByteBuffer.allocate(48);

            int bytesRead = inChannel.read(buf); //read into buffer.
            while (bytesRead != -1) {

                buf.flip();  //make buffer ready for read
                while(buf.hasRemaining()){
                    System.out.print((char) buf.get()); // read 1 byte at a time
                }

                buf.clear(); //make buffer ready for writing
                bytesRead = inChannel.read(buf);
            }
            aFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
