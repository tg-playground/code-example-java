package com.taogen.example.springframework.ioc.annotations;

import lombok.Data;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

/**
 * @author Taogen
 */
@Component
@Data
public class MyAnnotationBean {
    private String name;
    private Logger logger = LogManager.getLogger();

    public void sayHello() {
        logger.debug("hello by {}", name);
    }
}
