package com.taogen.example.jdbc.utils;

import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    public static void loggerError(Logger logger, Throwable t){
        logger.error("{}: {}", t.getClass().getName(), t.getMessage(), t);
    }
}
