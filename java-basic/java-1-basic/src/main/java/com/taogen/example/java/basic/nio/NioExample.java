package com.taogen.example.java.basic.nio;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.*;
import java.util.Date;

/**
 * @author Taogen
 */
public class NioExample {
    public static void main(String[] args) throws IOException {
        test7();
    }

    public static void test7() throws IOException {
        final int BUFSIZE = 10;
        final int LIMIT = 3;
        final Pipe pipe = Pipe.open();
        Thread sender = new Thread(() -> {
            WritableByteChannel src = pipe.sink();
            ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
            for (int i = 0; i < LIMIT; i++) {
                buffer.clear();
                for (int j = 0; j < BUFSIZE; j++) {
                    double random = Math.random();
                    buffer.put((byte) (random * 256));
                    System.out.println("CLIENT Send: " + (byte) (random * 256));
                }
                buffer.flip();
                try {
                    while (src.write(buffer) > 0) ;
                } catch (IOException ioe) {
                    System.err.println(ioe.getMessage());
                }
            }
            try {
                src.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        });
        Thread receiver = new Thread(() -> {
            ReadableByteChannel dst = pipe.source();
            ByteBuffer buffer = ByteBuffer.allocate(BUFSIZE);
            try {
                while (dst.read(buffer) >= 0) {
                    buffer.flip();
                    while (buffer.remaining() > 0) {
                        System.out.println("SERVER Receive: " + (buffer.get() & 255));
                    }
                    buffer.clear();
                }
            } catch (IOException ioe) {
                System.err.println(ioe.getMessage());
            }
        });
        sender.start();
        receiver.start();
    }

    public static void test6() throws IOException {
        SocketChannel sc = SocketChannel.open();
        sc.connect(new InetSocketAddress("localhost", 9999));
        while (!sc.finishConnect()) {
            System.out.println("waiting to finish connection");
        }
        ByteBuffer buffer = ByteBuffer.allocate(200);
        while (true) {
            buffer.clear();
            buffer.asCharBuffer().put("hello at " + new Date());
            // send
            sc.write(buffer);
            // receive
            while (sc.read(buffer) >= 0) {
                buffer.flip();
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                buffer.clear();
            }
        }
//        sc.close();
    }


    public static void test5() throws IOException {
        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.socket().bind(new InetSocketAddress(9999));
        ssc.configureBlocking(false);
        String msg = "Local address: " + ssc.socket().getLocalSocketAddress();
        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
        while (true) {
            System.out.println("...");
            SocketChannel sc = ssc.accept();
            if (sc != null) {
                System.out.println();
                System.out.println("Server: Received connection from " +
                        sc.socket().getRemoteSocketAddress());
                buffer.rewind();
                sc.write(buffer);
                sc.close();
            } else {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ie) {
                    assert false; // shouldn't happen
                }
            }
        }
    }

    public static void test4() {
        try (FileInputStream fis = new FileInputStream("temp.txt")) {
            FileChannel inChannel = fis.getChannel();
            WritableByteChannel outChannel = Channels.newChannel(System.out);
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } catch (IOException ioe) {
            System.out.println("I/O error: " + ioe.getMessage());
        }
    }

    public static void test3() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("temp.txt", "rw");
        FileChannel fc = raf.getChannel();
        long size = fc.size();
        System.out.println("Size: " + size);
        MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_WRITE, 0,
                size);
        mbb.clear();
        while (mbb.remaining() > 0) {
            System.out.print((char) mbb.get());
        }
        System.out.println();
        System.out.println();

        String msg = "hello";
        mbb.clear();
        mbb.asCharBuffer().put(msg);
        while (mbb.hasRemaining()) {
            fc.write(mbb);
        }
        fc.close();
    }

    public static void test2() throws IOException {
        RandomAccessFile raf = new RandomAccessFile("temp.txt", "rw");
        FileChannel fc = raf.getChannel();
        long pos = fc.position();
        System.out.println("Position: " + pos);
        System.out.println("Size: " + fc.size());
        String msg = "This is a test message.";
        ByteBuffer buffer = ByteBuffer.allocateDirect(msg.length() * 2);
        buffer.asCharBuffer().put(msg);
        fc.write(buffer);
        fc.force(true);
        System.out.println("Position: " + fc.position());
        System.out.println("Size: " + fc.size());
        buffer.clear();
        fc.position(pos);
        fc.read(buffer);
        buffer.flip();
        while (buffer.hasRemaining()) {
            System.out.print(buffer.getChar());
        }
    }

    public static void test1() throws IOException {
        ReadableByteChannel src = Channels.newChannel(System.in);
        WritableByteChannel dest = Channels.newChannel(System.out);
        ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
        while (src.read(buffer) != -1) {
            buffer.flip();
            dest.write(buffer);
            buffer.compact();
        }
        buffer.flip();
        while (buffer.hasRemaining()) {
            dest.write(buffer);
        }
    }
}
