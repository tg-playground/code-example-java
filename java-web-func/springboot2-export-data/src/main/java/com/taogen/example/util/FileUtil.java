package com.taogen.example.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Taogen
 */
public class FileUtil {
    public static String getFileNameWithSuffix(String fileName, String suffix) {
        return new StringBuffer()
                .append(fileName)
                .append("_")
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(suffix)
                .toString();
    }
}
