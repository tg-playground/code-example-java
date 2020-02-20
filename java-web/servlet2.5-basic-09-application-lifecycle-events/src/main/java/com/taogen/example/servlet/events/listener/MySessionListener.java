package com.taogen.example.servlet.events.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug("Session created::ID={}", httpSessionEvent.getSession().getId());
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        LOGGER.debug("Session destroyed::ID={}", httpSessionEvent.getSession().getId());
    }
}
