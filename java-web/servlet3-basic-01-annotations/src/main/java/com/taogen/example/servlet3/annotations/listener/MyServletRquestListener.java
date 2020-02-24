package com.taogen.example.servlet3.annotations.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class MyServletRquestListener implements ServletRequestListener {

    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        LOGGER.debug("Request {} end.", httpServletRequest.getRequestURI());
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        ServletRequest servletRequest = servletRequestEvent.getServletRequest();
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        LOGGER.debug("Request {} start processing...", httpServletRequest.getRequestURI());
        LOGGER.debug("Source={}, Destination={}", servletRequest.getRemoteAddr(), httpServletRequest.getRequestURL());
    }
}
