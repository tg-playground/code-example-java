package com.taogen.example.jdbc.utils;

import org.apache.logging.log4j.Logger;

public class LoggerUtil {

    private LoggerUtil(){
        throw new IllegalStateException("Utility class");
    }

    public static void loggerError(Logger logger, Throwable t){
        logger.error("{}: {}", t.getClass().getName(), t.getMessage(), t);
    }
}
