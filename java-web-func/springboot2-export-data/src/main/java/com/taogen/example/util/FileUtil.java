package com.taogen.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author Taogen
 */
public class FileUtil {
    public static String addDatetimeToFileName(String fileName, String suffix) {
        return new StringBuffer()
                .append(fileName)
                .append("_")
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(suffix)
                .toString();
    }

    public static String addTimestampToFileName(String fileName, String suffix) {
        return new StringBuffer()
                .append(fileName)
                .append("_")
                .append(System.currentTimeMillis())
                .append(suffix)
                .toString();
    }

    public static String addUuidToFileName(String fileName, String suffix) {
        return new StringBuffer()
                .append(fileName)
                .append("_")
                .append(UUID.randomUUID().toString().replace("-", ""))
                .append(suffix)
                .toString();
    }

    public static void main(String[] args) {
        System.out.println(addDatetimeToFileName("test", ".txt"));
        System.out.println(addTimestampToFileName("test", ".txt"));
        System.out.println(addUuidToFileName("test", ".txt"));
    }
}
