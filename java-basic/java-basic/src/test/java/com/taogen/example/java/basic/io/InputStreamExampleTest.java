package com.taogen.example.java.basic.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;

/**
 * ByteArrayInputStream
 * FileInputStream
 * ObjectInputStream
 * PipedInputStream
 * StringBufferInputStream @Deprecated
 * SequenceInputStream
 * FilterInputStream
 * - BufferedInputStream
 * - PushbackInputStream
 * - DataInputStream
 * - LineNumberInputStream @Deprecated
 */
public class InputStreamExampleTest implements Serializable {

    private static final Logger logger = LogManager.getLogger();
    private String name;

    public InputStreamExampleTest() {
    }

    private InputStreamExampleTest(String name) {
        this.name = name;
    }

    @Test
    public void byteArrayInputStream() {
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[]{1, 2, 3})
        ) {
            int inputStreamSize = byteArrayInputStream.available();
            logger.debug("the input stream size: {}", inputStreamSize);
            logger.debug("available size: {}", byteArrayInputStream.available());
            logger.debug("read: {}", byteArrayInputStream.read());
            if (byteArrayInputStream.markSupported()) {
                // readAheadLimit for this class mark method has no meaning
                byteArrayInputStream.mark(0);
                logger.debug("mark at current position");
            }
            logger.debug("available size: {}", byteArrayInputStream.available());
            byteArrayInputStream.reset();
            logger.debug("input stream reset to mark.");
            logger.debug("available size: {}", byteArrayInputStream.available());
            logger.debug("read: {}", byteArrayInputStream.read());
            logger.debug("skip size: {}", byteArrayInputStream.skip(1));
            logger.debug("read: {}", byteArrayInputStream.read());
            logger.debug("read: {}", byteArrayInputStream.read());
            logger.debug("read: {}", byteArrayInputStream.read());

        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void fileInputStream() {
        try (
                FileInputStream fileInputStream = new FileInputStream("src/main/resources/tmp.txt")
        ) {
            String path = getClass().getClassLoader().getResource("tmp.txt").toURI().getPath();
            logger.debug("path: {}", path);
            StringBuilder result = new StringBuilder();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) != -1) {
                result.append(new String(buffer));
            }
            logger.debug("read result: \n{}", result.toString());
        } catch (IOException | URISyntaxException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void objectInputStream() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("tmp.txt");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(new InputStreamExampleTest("objectInputStreamTest"));
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("tmp.txt"));
            InputStreamExampleTest readObject = (InputStreamExampleTest) objectInputStream.readObject();
            logger.debug("readObject name field: {}", readObject.name);
        } catch (IOException | ClassNotFoundException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void pipedInputStream() {
        try (
                PipedInputStream pipedInputStream = new PipedInputStream();
                PipedOutputStream pipedOutputStream = new PipedOutputStream()
        ) {
            byte asciiA = 65;
            byte asciiF = 70;
            pipedInputStream.connect(pipedOutputStream);
            Thread pipeWriter = new Thread(() -> {
                logger.debug("pipedOutputStream write begin.");
                for (int i = asciiA; i <= asciiF; i++) {
                    try {
                        pipedOutputStream.write(i);
                        logger.debug("{}-pipe write: {}", Thread.currentThread().getName(), (char)i);
                    } catch (IOException e) {
                        logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
                    }
                }
                logger.debug("pipedOutputStream write end.");
            });
            Thread pipeReader = new Thread(() -> {
                logger.debug("pipedInputStream read begin.");
                for (int i = asciiA; i <= asciiF; i++) {
                    try {
                        logger.debug("{}-pipe read {}", Thread.currentThread().getName(), (char) pipedInputStream.read());
                    } catch (IOException e) {
                        logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
                    }
                }
                logger.debug("pipedInputStream read end.");
            });
            pipeWriter.start();
            pipeReader.start();
            logger.debug("current thread waiting for pipeWriter thread to terminate");
            pipeWriter.join();
            logger.debug("after pipeWrite join");
            logger.debug("current thread waiting for pipeReader thread to terminate");
            pipeReader.join();
            logger.debug("after pipeReader join");
            logger.debug("test end.");
        } catch (IOException | InterruptedException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void stringBufferInputStream() {
        try (
                StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("hello你好");
        ) {
            int inputStreamSize = stringBufferInputStream.available();
            logger.debug("the input stream size: {}", inputStreamSize);
            logger.debug("available size: {}", stringBufferInputStream.available());
            logger.debug("read: {}", stringBufferInputStream.read());
            logger.debug("read: {}", stringBufferInputStream.read());
            logger.debug("read: {}", stringBufferInputStream.read());
            logger.debug("read: {}", stringBufferInputStream.read());
            logger.debug("read: {}", stringBufferInputStream.read());
            logger.debug("read: {}", stringBufferInputStream.read());
            logger.debug("read: {}", stringBufferInputStream.read());
            stringBufferInputStream.reset();
            byte[] buffer = new byte[1024];

            stringBufferInputStream.read(buffer);
            logger.debug("buffer: {}", new String(buffer));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void sequenceInputStream() {
        try (
                ByteArrayInputStream inputStream1 = new ByteArrayInputStream(new byte[]{1, 2, 3});
                ByteArrayInputStream inputStream2 = new ByteArrayInputStream(new byte[]{4, 5, 6});
                SequenceInputStream sequenceInputStream = new SequenceInputStream(inputStream1, inputStream2);
        ) {
            byte data = -1;
            while ((data = (byte) sequenceInputStream.read()) != -1) {
                logger.debug("read: {}", data);
            }
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void BufferedInputStream() {
        try (
                BufferedInputStream bufferedInputStream = new BufferedInputStream(new ByteArrayInputStream(new byte[]{0, 0, 1, 1}));
        ) {
            int data = -1;
            while ((data = bufferedInputStream.read()) != -1) {
                logger.debug("read: {}", data);
            }
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void pushBackInputStream() throws IOException {
        try (
                PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream(new byte[]{1, 2, 3}));
        ) {
            int availableSize = pushbackInputStream.available();
            logger.debug("available size: {}", availableSize);
            int readValue = pushbackInputStream.read();
            logger.debug("read: {}", readValue);
            pushbackInputStream.unread(readValue);
            logger.debug("push back {}", readValue);
            logger.debug("read: {}", pushbackInputStream.read());
            logger.debug("read: {}", pushbackInputStream.read());
            logger.debug("read: {}", pushbackInputStream.read());
            logger.debug("read: {}", pushbackInputStream.read());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void dataInputStream() {
        try (
                DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(new byte[]{0x00, 0x00, 0x1, 0x1}))
        ) {
            logger.debug("read int (4 bytes) result: {}", dataInputStream.readInt());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void lineNumberInputStream() {
        try (
                LineNumberInputStream lineNumberInputStream = new LineNumberInputStream(new FileInputStream("src/main/resources/tmp.txt"))
        ) {
            byte data = -1;
            while ((data = (byte) lineNumberInputStream.read()) != -1) {
                logger.debug("read: {}", data);
                logger.debug("line number: {}", lineNumberInputStream.getLineNumber());
            }
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

}
