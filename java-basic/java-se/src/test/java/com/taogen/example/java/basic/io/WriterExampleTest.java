package com.taogen.example.java.basic.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class WriterExampleTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    public void bufferedWriter() {
        try (
                StringWriter stringWriter = new StringWriter();
                BufferedWriter bufferedWriter = new BufferedWriter(stringWriter)
        ) {
            String s = "hello";
            String s2 = "world";
            bufferedWriter.write(s);
            bufferedWriter.newLine();
            bufferedWriter.write(s2);
            bufferedWriter.flush();
            StringBuffer buf = stringWriter.getBuffer();
            logger.debug("write: {}", buf.toString());
            assertEquals(s + "\r\n" + s2, buf.toString());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    @Test
    public void outputStreamWriter() {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        ) {
            String s = "hello world, 你好";
            outputStreamWriter.write(s);
            outputStreamWriter.flush();
            assertEquals(s, byteArrayOutputStream.toString());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    @Test
    public void fileWriter() {
        try (
                FileWriter fileWriter = new FileWriter("tmp.txt");
        ) {
            fileWriter.write("hello");
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    @Test
    public void stringWriter() {
        try (
                StringWriter stringWriter = new StringWriter()
        ) {
            String s = "hello, 你好";
            stringWriter.write(s);
            assertEquals(s, stringWriter.getBuffer().toString());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    @Test
    public void charArrayWriter() {

        try (
                CharArrayWriter charArrayWriter = new CharArrayWriter()
        ) {
            String s = "hello";
            charArrayWriter.write(s);
            charArrayWriter.flush();
            assertEquals(s, charArrayWriter.toString());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }

    @Test
    public void pipedWriter() {
        new ReaderExampleTest().pipedReader();
    }

    @Test
    public void printWriter() {
        try (
                StringWriter stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter)
        ) {
            String s1 = "hello";
            String s2 = "world";
            printWriter.print(s1);
            printWriter.print(s2);
            printWriter.flush();
            assertEquals(s1 + s2, stringWriter.getBuffer().toString());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage());
        }
    }
}