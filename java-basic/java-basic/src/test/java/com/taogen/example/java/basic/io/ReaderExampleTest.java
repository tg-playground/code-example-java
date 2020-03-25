package com.taogen.example.java.basic.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ReaderExampleTest {
    private static final Logger logger = LogManager.getLogger();

    @Test
    public void bufferedReader() {
        String s = "hello";
        try (
                StringReader stringReader = new StringReader(s);
                BufferedReader bufferedReader = new BufferedReader(stringReader)
        ) {
            assertEquals(s, bufferedReader.readLine());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void lineNumberReader() {
        String s = "hello\r\nworld";
        try (
                StringReader stringReader = new StringReader(s);
                LineNumberReader lineNumberReader = new LineNumberReader(stringReader)
        ) {
            String line;
            while ((line = lineNumberReader.readLine()) != null) {
                logger.debug("line number: {}", lineNumberReader.getLineNumber());
                logger.debug("read line: {}", line);
            }
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void charArrayReader() {
        char[] chars = new char[]{'a', 'b', 'c'};
        try (
                CharArrayReader charArrayReader = new CharArrayReader(chars)
        ) {
            assertEquals('a', charArrayReader.read());
            assertEquals('b', charArrayReader.read());
            assertEquals('c', charArrayReader.read());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void pushbackReader() {
        String s = "hello";
        try (
                StringReader stringReader = new StringReader(s);
                PushbackReader pushbackReader = new PushbackReader(stringReader)
        ) {
            assertEquals('h', pushbackReader.read());
            pushbackReader.unread('h');
            assertEquals('h', pushbackReader.read());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void inputStreamReader(){
        String s = "hello world";
        try (
            InputStreamReader inputStreamReader = new InputStreamReader(new ByteArrayInputStream(s.getBytes(StandardCharsets.UTF_8)))
        ) {
            StringBuilder stringBuilder = new StringBuilder();
            int buf;
            while ((buf = inputStreamReader.read()) != -1){
                logger.debug("read: {}", (char)buf);
                stringBuilder.append((char)buf);
            }
            assertEquals(s, stringBuilder.toString());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void fileReader() {
        try (
                FileReader fileReader = new FileReader("tmp.txt");
        ) {
            logger.debug("read: {}", (char) fileReader.read());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void pipedReader() {
        try (
                PipedWriter pipedWriter = new PipedWriter();
                PipedReader pipedReader = new PipedReader();
        ) {
            pipedReader.connect(pipedWriter);
            Thread t1 = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    try {
                        String s = "hello" + i;
                        pipedWriter.write(s);
                        logger.debug("pipe write: {}", s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            Thread t2 = new Thread(() -> {
                try {
                    int c = pipedReader.read();
                    while (c != -1) {
                        logger.debug("pipe read: {}", (char) c);
                        c = pipedReader.read();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            t1.start();
            t2.start();
            logger.debug("waiting for pipe writer thread terminate");
            t1.join();
            logger.debug("waiting for pipe reader thread terminate");
            t2.join();

        } catch (IOException | InterruptedException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void stringReader(){
        String s = "hello";
        try (
                StringReader stringReader = new StringReader(s)
        ) {
            char[] buf = new char[1024];
            int len = stringReader.read(buf);
            logger.debug("read: -{}-", new String(buf, 0, len));
            assertEquals(s, new String(buf, 0, len));
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }
}