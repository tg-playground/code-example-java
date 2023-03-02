package com.taogen.example.servlet.events.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.debug("AppContextListener initialized!");
        // Connecting to services, save connection Object to ServletContext
        // ...
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute("connectionName", "connectionObject");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        logger.debug("AppContextListener destroyed!");
        // Closing services connections
        // ...
    }
}
