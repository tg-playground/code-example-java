package com.taogen.example.java.basic.io;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

/**
 * ByteArrayOutputStream
 * FileOutputStream
 * ObjectOutputStream
 * PipedOutputStream
 * FilterOutputStream
 * - BufferedOutputStream
 * - DataOutputStream
 * - PrintStream
 */
public class OutputStreamExampleTest {

    private static final Logger logger = LogManager.getLogger();

    @Test
    public void byteArrayOutputStream() {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(3)
        ) {
            byteArrayOutputStream.write(1);
            byteArrayOutputStream.write(2);
            byteArrayOutputStream.write(3);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            for (int i = 0; i < 3; i++) {
                assertEquals(i + 1, byteArray[i]);
            }
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void fileOutputStream() {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("tmp.txt")
        ) {
            fileOutputStream.write("hello".getBytes());
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void objectOutputStream() {
        try (
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("src/main/resources/tmp.txt"))
        ) {
            objectOutputStream.writeObject("hello");
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("src/main/resources/tmp.txt"));
            assertEquals("hello", objectInputStream.readObject());
        } catch (IOException | ClassNotFoundException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void pipedOutputStream() {
        // see
        new InputStreamExampleTest().pipedInputStream();
    }

    @Test
    public void bufferedOutputStream() {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(3);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream)
        ) {
            bufferedOutputStream.write(1);
            bufferedOutputStream.write(2);
            bufferedOutputStream.write(3);
            bufferedOutputStream.flush();
            byte[] bytes = byteArrayOutputStream.toByteArray();
            assertEquals(1, bytes[0]);
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void dataOutputStream() {
        try (
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(4);
                DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream)
        ) {
            dataOutputStream.writeInt(1);
            dataOutputStream.flush();
            byte[] result = byteArrayOutputStream.toByteArray();
            assertEquals(1, result[4 - 1]);
        } catch (IOException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
        }
    }

    @Test
    public void printStream() {
        try (
                PrintStream printStream = new PrintStream(System.out)
        ) {
            printStream.println("hello");
        }
    }

}