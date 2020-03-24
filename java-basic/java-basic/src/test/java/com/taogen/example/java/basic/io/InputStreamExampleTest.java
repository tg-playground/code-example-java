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
 * StringInputStream @Deprecated
 * SequenceInputStream
 * FilterInputStream
 * - BufferedInputStream
 * - PushbackInputStream
 * - DataInputStream
 * - LineNumberInputStream @Deprecated
 */
public class InputStreamExampleTest {
    private static final Logger logger = LogManager.getLogger();

    @Test
    public void byteArrayInputStream() {
        try (
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(new byte[]{1, 2, 3});
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
                FileInputStream fileInputStream = new FileInputStream("src/main/resources/tmp.txt");
        ) {
            String path = getClass().getClassLoader().getResource("tmp.txt").toURI().getPath();
            logger.debug("path: {}", path);
            StringBuilder result = new StringBuilder();
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fileInputStream.read(buffer)) != -1){
                result.append(new String(buffer));
            }
            logger.debug("read result: \n{}", result.toString());
        } catch (IOException | URISyntaxException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void dataInputStream() {
        try (
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(new byte[]{0x00, 0x00, 0x1, 0x1}));
        ) {
            logger.debug("read int (4 bytes) result: {}", dataInputStream.readInt());
        }catch (IOException e){
           logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }

    }

    @Test
    public void pushBackInputStream() throws IOException {
        PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream(new byte[]{1, 2, 3}));
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
    }

    @Test
    public void stringBufferInputStream() {
        StringBufferInputStream stringBufferInputStream = new StringBufferInputStream("hello你好");
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
        try {
            stringBufferInputStream.read(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logger.debug("buffer: {}", new String(buffer));
    }

}
