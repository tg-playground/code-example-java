package com.taogen.example.jdbc.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Taogen
 */
public class PropertyUtils {

    private static final Logger logger = LogManager.getLogger();

    private PropertyUtils(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * @param filepath: relative to classes path
     * @return
     * @throws IOException
     */
    public static Properties getProperitesByFilePath(String filepath) throws IOException {
        Properties properties = new Properties();
        InputStream inputStream = ClassLoader.getSystemResourceAsStream(filepath);
        try {
            properties.load(inputStream);
        } catch (IOException | NullPointerException e) {
            logger.error("{}: {}", e.getClass().getName(), e.getMessage(), e);
            throw e;
        }
        return properties;
    }

}
